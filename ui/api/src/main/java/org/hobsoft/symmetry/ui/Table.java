/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Table.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.EventSetDescriptor;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.ui.event.AbstractClosureEventListener;
import org.hobsoft.symmetry.ui.event.TableCellListener;
import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.event.TableHeaderListener;
import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.event.TableModelListener;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.model.TableModel;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import org.hobsoft.symmetry.ui.view.LabelTableCellRenderer;
import org.hobsoft.symmetry.ui.view.LabelTableHeaderRenderer;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.Visits.nullEndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Table extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String HEADER_EVENT = "header";
	
	public static final String CELL_EVENT = "cell";
	
	public static final String MODEL_PROPERTY = "model";
	
	public static final String VISIBLE_ROW_COUNT_PROPERTY = "visibleRowCount";
	
	public static final String FIRST_VISIBLE_ROW_INDEX_PROPERTY = "firstVisibleRowIndex";
	
	public static final String HEADER_RENDERER_PROPERTY = "headerRenderer";
	
	public static final String CELL_RENDERER_PROPERTY = "cellRenderer";
	
	public static final String HEADER_LISTENER_PROPERTY = "headerListener";
	
	public static final String CELL_LISTENER_PROPERTY = "cellListener";
	
	private static final EventSetDescriptor CELL_EVENT_SET = EventSets.getDescriptor(Table.class, CELL_EVENT);
	
	private static final int DEFAULT_VISIBLE_ROW_COUNT = 20;
	
	// types ------------------------------------------------------------------
	
	static class ClosureTableCellListener extends AbstractClosureEventListener<TableEvent>
		implements TableCellListener
	{
		// constructors -----------------------------------------------------------
		
		public ClosureTableCellListener(Closure<? super TableEvent> closure)
		{
			super(closure);
		}
		
		// TableCellListener methods ----------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void tableCellSelected(TableEvent event)
		{
			getClosure().apply(event);
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final List<TableColumn> columns;
	
	private TableModel model;
	
	private int visibleRows;
	
	private int firstVisibleRow;
	
	private TableCellRenderer headerRenderer;
	
	private TableCellRenderer cellRenderer;
	
	private Map<Class<?>, TableCellRenderer> cellRenderersByType;
	
	// constructors -----------------------------------------------------------
	
	public Table()
	{
		this(new DefaultTableModel());
	}
	
	public Table(TableModel model)
	{
		if (model == null)
		{
			model = new DefaultTableModel();
		}
		
		columns = new ArrayList<TableColumn>();
		
		this.model = model;
		
		visibleRows = DEFAULT_VISIBLE_ROW_COUNT;
		firstVisibleRow = 0;
		headerRenderer = new LabelTableHeaderRenderer();
		cellRenderer = new LabelTableCellRenderer();
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptTable(visitor, Table.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addHeaderListener(TableHeaderListener listener)
	{
		addListener(TableHeaderListener.class, listener);
		firePropertyChange(HEADER_LISTENER_PROPERTY, null, listener);
	}
	
	public void removeHeaderListener(TableHeaderListener listener)
	{
		removeListener(TableHeaderListener.class, listener);
		firePropertyChange(HEADER_LISTENER_PROPERTY, listener, null);
	}
	
	public TableHeaderListener[] getHeaderListeners()
	{
		return getListeners(TableHeaderListener.class);
	}
	
	public int getHeaderListenerCount()
	{
		return getListenerCount(TableHeaderListener.class);
	}
	
	public void addCellListener(TableCellListener listener)
	{
		addListener(TableCellListener.class, listener);
		firePropertyChange(CELL_LISTENER_PROPERTY, null, listener);
	}
	
	public void removeCellListener(TableCellListener listener)
	{
		removeListener(TableCellListener.class, listener);
		firePropertyChange(CELL_LISTENER_PROPERTY, listener, null);
	}
	
	public TableCellListener[] getCellListeners()
	{
		return getListeners(TableCellListener.class);
	}
	
	public int getCellListenerCount()
	{
		return getListenerCount(TableCellListener.class);
	}
	
	/**
	 * Adds the specified closure to those that will be executed when this table is clicked.
	 * 
	 * @param closure
	 *            the closure that will be executed
	 * @return this table
	 */
	public Table onSelect(Closure<? super TableEvent> closure)
	{
		addCellListener(new ClosureTableCellListener(closure));
		
		return this;
	}
	
	public void doSelect(int row, int column)
	{
		fireCellSelected(new TableEvent(this, row, column));
	}
	
	public Table onChange(final Closure<? super TableModelEvent> closure)
	{
		getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged(TableModelEvent event)
			{
				closure.apply(event);
			}
		});
		
		return this;
	}
	
	public TableModel getModel()
	{
		return model;
	}
	
	public void setModel(TableModel model)
	{
		if (model == null)
		{
			model = new DefaultTableModel();
		}
		
		TableModel oldModel = this.model;
		this.model = model;
		firePropertyChange(MODEL_PROPERTY, oldModel, model);
	}
	
	public int getFirstVisibleRowIndex()
	{
		return firstVisibleRow;
	}
	
	public void setFirstVisibleRowIndex(int firstVisibleRow)
	{
		// TODO: can we achieve this without hitting the model?
//		checkElementIndex(firstVisibleRow, getModel().getRowCount(), "firstVisibleRow");
		checkNonNegative(firstVisibleRow, "firstVisibleRow");
		
		int oldFirstVisibleRow = this.firstVisibleRow;
		this.firstVisibleRow = firstVisibleRow;
		firePropertyChange(FIRST_VISIBLE_ROW_INDEX_PROPERTY, oldFirstVisibleRow, firstVisibleRow);
	}
	
	public int getVisibleRowCount()
	{
		return visibleRows;
	}
	
	public void setVisibleRowCount(int visibleRows)
	{
		checkNonNegative(visibleRows, "visibleRows");
		
		int oldVisibleRows = this.visibleRows;
		this.visibleRows = visibleRows;
		firePropertyChange(VISIBLE_ROW_COUNT_PROPERTY, oldVisibleRows, visibleRows);
	}
	
	public TableCellRenderer getHeaderRenderer()
	{
		return headerRenderer;
	}
	
	public void setHeaderRenderer(TableCellRenderer headerRenderer)
	{
		checkNotNull(headerRenderer, "headerRenderer cannot be null");
		
		TableCellRenderer oldHeaderRenderer = this.headerRenderer;
		this.headerRenderer = headerRenderer;
		firePropertyChange(HEADER_RENDERER_PROPERTY, oldHeaderRenderer, headerRenderer);
	}
	
	public TableCellRenderer getCellRenderer(int row, int column)
	{
		// try column first
		TableCellRenderer renderer = getCellRendererForColumn(column);
		
		// failing that try column type
		if (renderer == null)
		{
			Class<?> type = model.getColumnClass(column);
			
			renderer = getCellRendererForType(type);
		}
		
		// failing that use default
		if (renderer == null)
		{
			renderer = cellRenderer;
		}
		
		return renderer;
	}
	
	public void setCellRenderer(TableCellRenderer cellRenderer)
	{
		TableCellRenderer oldCellRenderer = this.cellRenderer;
		this.cellRenderer = cellRenderer;
		firePropertyChange(CELL_RENDERER_PROPERTY, oldCellRenderer, cellRenderer);
	}
	
	public void setCellRenderer(Class<?> klass, TableCellRenderer cellRenderer)
	{
		if (cellRenderersByType == null)
		{
			cellRenderersByType = new HashMap<Class<?>, TableCellRenderer>();
		}
		
		TableCellRenderer oldCellRenderer = cellRenderersByType.put(klass, cellRenderer);
		// TODO: add class to event somehow?
		firePropertyChange(CELL_RENDERER_PROPERTY, oldCellRenderer, cellRenderer);
	}
	
	public List<TableColumn> getColumns()
	{
		return new AbstractList<TableColumn>()
		{
			@Override
			public int size()
			{
				return getModel().getColumnCount();
			}
			
			@Override
			public TableColumn get(int index)
			{
				return getColumn(index);
			}
		};
	}
	
	public TableColumn getColumn(int columnIndex)
	{
		checkColumnIndex(columnIndex);
		
		// not threadsafe
		
		TableColumn column = getColumnOrNull(columnIndex);
		
		if (column == null)
		{
			ensureListSize(columns, columnIndex + 1);
			
			column = new TableColumn(this, columnIndex);
			columns.set(columnIndex, column);
		}
		
		return column;
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Table, P, E extends Exception> EndVisit acceptTable(ComponentVisitor<P, E> visitor,
		Class<T> tableType, T table, P parameter) throws E
	{
		return acceptTable(visitor, Generic.get(tableType), table, parameter);
	}
	
	protected static <T extends Table, P, E extends Exception> EndVisit acceptTable(ComponentVisitor<P, E> visitor,
		Generic<T> tableType, T table, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(tableType, table, parameter);
		
		if (subvisitor == null || subvisitor.visit(table, parameter) != SKIP_CHILDREN)
		{
			if (acceptHeader(visitor, subvisitor, table, parameter) != SKIP_SIBLINGS)
			{
				acceptBody(visitor, subvisitor, table, parameter);
			}
		}
		
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(table, parameter));
	}

	protected final void fireCellSelected(TableEvent event)
	{
		fireEvent(CELL_EVENT_SET, event);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends Table, P, E extends Exception> EndVisit acceptHeader(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T table, P parameter) throws E
	{
		if (asTableVisitor(subvisitor).visitHeader(table, parameter) != SKIP_CHILDREN)
		{
			int columns = table.getModel().getColumnCount();
			
			for (int column = 0; column < columns; column++)
			{
				if (acceptHeaderCell(visitor, subvisitor, table, parameter, column) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asTableVisitor(subvisitor).endVisitHeader(table, parameter));
	}
	
	private static <T extends Table, P, E extends Exception> EndVisit acceptHeaderCell(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T table, P parameter, int column) throws E
	{
		if (asTableVisitor(subvisitor).visitHeaderCell(table, parameter, column) != SKIP_CHILDREN)
		{
			TableCellRenderer renderer = getEffectiveHeaderRenderer(table, column);
			Component headerComponent = renderer.getTableCellComponent(table, -1, column);
			
			if (headerComponent != null)
			{
				// TODO: should we use the return value?
				headerComponent.accept(visitor, parameter);
			}
		}
		
		return nullEndVisit(asTableVisitor(subvisitor).endVisitHeaderCell(table, parameter, column));
	}
	
	private static <T extends Table, P, E extends Exception> EndVisit acceptBody(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T table, P parameter) throws E
	{
		if (asTableVisitor(subvisitor).visitBody(table, parameter) != SKIP_CHILDREN)
		{
			int rows = table.getModel().getRowCount();
			
			for (int row = 0; row < rows; row++)
			{
				if (acceptRow(visitor, subvisitor, table, parameter, row) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asTableVisitor(subvisitor).endVisitBody(table, parameter));
	}
	
	private static <T extends Table, P, E extends Exception> EndVisit acceptRow(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T table, P parameter, int row) throws E
	{
		if (asTableVisitor(subvisitor).visitRow(table, parameter, row) != SKIP_CHILDREN)
		{
			int columns = table.getModel().getColumnCount();
			
			for (int column = 0; column < columns; column++)
			{
				if (acceptCell(visitor, subvisitor, table, parameter, row, column) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asTableVisitor(subvisitor).endVisitRow(table, parameter, row));
	}

	private static <T extends Table, P, E extends Exception> EndVisit acceptCell(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T table, P parameter, int row, int column) throws E
	{
		if (asTableVisitor(subvisitor).visitCell(table, parameter, row, column) != SKIP_CHILDREN)
		{
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component cellComponent = renderer.getTableCellComponent(table, row, column);
			
			if (cellComponent != null)
			{
				// TODO: should we use the return value?
				cellComponent.accept(visitor, parameter);
			}
		}
		
		return nullEndVisit(asTableVisitor(subvisitor).endVisitCell(table, parameter, row, column));
	}

	private static TableCellRenderer getEffectiveHeaderRenderer(Table table, int columnIndex)
	{
		TableCellRenderer renderer = table.getColumn(columnIndex).getHeaderRenderer();
		
		if (renderer == null)
		{
			renderer = table.getHeaderRenderer();
		}
		
		return renderer;
	}
	
	private TableCellRenderer getCellRendererForColumn(int columnIndex)
	{
		TableColumn column = getColumnOrNull(columnIndex);
		
		return (column != null) ? column.getCellRenderer() : null;
	}
	
	private TableCellRenderer getCellRendererForType(Class<?> type)
	{
		if (cellRenderersByType == null)
		{
			return null;
		}

		// try superclasses first
		
		for (Class<?> supertype = type; supertype != null; supertype = supertype.getSuperclass())
		{
			TableCellRenderer renderer = cellRenderersByType.get(supertype);
			
			if (renderer != null)
			{
				return renderer;
			}
		}
		
		// then try interfaces
		
		for (Class<?> supertype = type; supertype != null; supertype = supertype.getSuperclass())
		{
			for (Class<?> iface : supertype.getInterfaces())
			{
				TableCellRenderer renderer = cellRenderersByType.get(iface);
				
				if (renderer != null)
				{
					return renderer;
				}
			}
		}
		
		return null;
	}
	
	private TableColumn getColumnOrNull(int columnIndex)
	{
		TableColumn column;
		
		if (columnIndex < columns.size())
		{
			column = columns.get(columnIndex);
		}
		else
		{
			column = null;
		}
		
		return column;
	}
	
	// TODO: use ui.model.Utilities.setSize
	private static <T> void ensureListSize(List<T> list, int size)
	{
		ensureListSize(list, size, null);
	}
	
	private static <T> void ensureListSize(List<T> list, int size, T object)
	{
		int delta = size - list.size();
		
		if (delta > 0)
		{
			list.addAll(Collections.nCopies(delta, object));
		}
	}
	
	private void checkColumnIndex(int columnIndex)
	{
		checkElementIndex(columnIndex, getModel().getColumnCount(), "columnIndex");
	}
}
