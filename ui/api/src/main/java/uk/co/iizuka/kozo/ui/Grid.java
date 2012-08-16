/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Grid.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asGridVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.Visits.nullEndVisit;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.primitives.Ints;
import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.layout.GridLayoutData;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * A container that lays its children out in columns.
 * 
 * @author Mark Hobson
 * @version $Id: Grid.java 100095 2012-03-30 14:47:33Z mark@IIZUKA.CO.UK $
 */
public class Grid extends Container
{
	// TODO: create GridLayoutData lazily like Table.column
	
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the grid's number of columns.
	 */
	public static final String COLUMN_COUNT_PROPERTY = "columnCount";
	
	private static final int DEFAULT_COLUMNS = 2;
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The number of columns used by this grid to layout its children.
	 */
	private int columnCount;
	
	private final List<GridColumn> columns;
	
	private final List<GridLayoutData> layoutDatas;
	
	private int[] rowStartIndexes;
	
	private final PropertyChangeListener columnSpanListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			layout();
		}
	};
	
	private final PropertyChangeListener layoutDataComponentListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			Component oldComponent = (Component) event.getOldValue();
			Component newComponent = (Component) event.getNewValue();
			int index = ((IndexedPropertyChangeEvent) event).getIndex();
			
			if (oldComponent == null && newComponent != null)
			{
				GridLayoutData layoutData = new GridLayoutData();
				layoutData.addPropertyChangeListener(GridLayoutData.COLUMN_SPAN_PROPERTY, columnSpanListener);
				
				layoutDatas.add(index, layoutData);
			}
			else if (oldComponent != null && newComponent == null)
			{
				GridLayoutData layoutData = layoutDatas.remove(index);
				layoutData.removePropertyChangeListener(GridLayoutData.COLUMN_SPAN_PROPERTY, columnSpanListener);
			}
			
			layout();
		}
	};
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a grid with two columns.
	 */
	public Grid()
	{
		this(DEFAULT_COLUMNS);
	}
	
	/**
	 * Creates a grid with the specified number of columns.
	 * 
	 * @param columnCount
	 *            the number of columns used to layout the grid's children
	 */
	public Grid(int columnCount)
	{
		this(columnCount, EMPTY_COMPONENT_ARRAY);
	}
	
	public Grid(Component... children)
	{
		this(DEFAULT_COLUMNS, children);
	}
	
	public Grid(int columnCount, Component... children)
	{
		columns = new ArrayList<GridColumn>();
		layoutDatas = new ArrayList<GridLayoutData>();
		rowStartIndexes = new int[0];
		
		addPropertyChangeListener(COLUMN_COUNT_PROPERTY, new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				ensureColumns();
			}
		});
		
		addPropertyChangeListener(COMPONENTS_PROPERTY, layoutDataComponentListener);
		
		setColumnCount(columnCount);
		
		// don't use super constructor to add children since we need to be initialised first
		add(children);
	}

	// Component methods ------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptGrid(visitor, Grid.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addSpacer()
	{
		add(new Spacer());
	}
	
	public void addSpacer(int n)
	{
		for (int i = 0; i < n; i++)
		{
			addSpacer();
		}
	}
	
	/**
	 * Gets the number of columns used by this grid to layout its children.
	 * 
	 * @return the number of columns used by this grid to layout its children
	 */
	public int getColumnCount()
	{
		return columnCount;
	}
	
	/**
	 * Sets the number of columns used by this grid to layout its children.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param columnCount
	 *            number of columns used by this grid to layout its children
	 */
	public void setColumnCount(int columnCount)
	{
		int oldColumnCount = this.columnCount;
		this.columnCount = columnCount;
		firePropertyChange(COLUMN_COUNT_PROPERTY, oldColumnCount, this.columnCount);
	}
	
	public int getRowCount()
	{
		return rowStartIndexes.length;
	}
	
	public int getRowIndex(Component child)
	{
		return getRowIndex(indexOf(child));
	}
	
	public int getRowIndex(int childIndex)
	{
		checkChildIndex(childIndex);
		
		int rowIndex = 0;
		
		while (rowIndex < rowStartIndexes.length && childIndex >= rowStartIndexes[rowIndex])
		{
			rowIndex++;
		}
		
		return rowIndex - 1;
	}
	
	public int getRowStartIndex(int rowIndex)
	{
		checkRowIndex(rowIndex);
		
		return rowStartIndexes[rowIndex];
	}
	
	public int getRowEndIndex(int rowIndex)
	{
		checkRowIndex(rowIndex);

		int nextRowIndex = rowIndex + 1;
		int nextRowStartIndex = (nextRowIndex < getRowCount()) ? getRowStartIndex(nextRowIndex) : getComponentCount();
		
		return nextRowStartIndex - 1;
	}
	
	public int getColumnIndex(Component child)
	{
		return getColumnIndex(indexOf(child));
	}
	
	public int getColumnIndex(int childIndex)
	{
		checkChildIndex(childIndex);
		
		int rowIndex = getRowIndex(childIndex);
		int rowStartIndex = getRowStartIndex(rowIndex);
		int columnIndex = 0;
		
		for (int i = rowStartIndex; i < childIndex; i++)
		{
			columnIndex += getLayoutData(i).getColumnSpan();
		}
		
		return columnIndex;
	}
	
	public List<Component> getRowComponents(int rowIndex)
	{
		checkRowIndex(rowIndex);

		int rowStartIndex = getRowStartIndex(rowIndex);
		int rowEndIndex = getRowEndIndex(rowIndex);
		List<Component> row = getComponents().subList(rowStartIndex, rowEndIndex + 1);
		
		return Collections.unmodifiableList(row);
	}
	
	public List<GridColumn> getColumns()
	{
		return Collections.unmodifiableList(columns);
	}
	
	public GridColumn getColumn(int columnIndex)
	{
		checkColumnIndex(columnIndex);
		
		return columns.get(columnIndex);
	}
	
	public GridLayoutData getLayoutData(Component child)
	{
		checkNotNull(child, "child cannot be null");
		
		int childIndex = indexOf(child);
		checkArgument(childIndex != -1, "child is not a child of this Grid: %s", child);
		
		return getLayoutData(childIndex);
	}

	public GridLayoutData getLayoutData(int childIndex)
	{
		checkChildIndex(childIndex);
		
		return layoutDatas.get(childIndex);
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Grid, P, E extends Exception> EndVisit acceptGrid(ComponentVisitor<P, E> visitor,
		Class<T> gridType, T grid, P parameter) throws E
	{
		return acceptGrid(visitor, Generic.get(gridType), grid, parameter);
	}
	
	protected static <T extends Grid, P, E extends Exception> EndVisit acceptGrid(ComponentVisitor<P, E> visitor,
		Generic<T> gridType, T grid, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(gridType, grid, parameter);
		
		if (subvisitor == null || subvisitor.visit(grid, parameter) != SKIP_CHILDREN)
		{
			if (acceptColumns(visitor, subvisitor, grid, parameter) != SKIP_SIBLINGS)
			{
				acceptRows(visitor, subvisitor, grid, parameter);
			}
		}
		
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(grid, parameter));
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends Grid, P, E extends Exception> EndVisit acceptColumns(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T grid, P parameter) throws E
	{
		if (asGridVisitor(subvisitor).visitColumns(grid, parameter) != SKIP_CHILDREN)
		{
			int columnCount = grid.getColumnCount();
		
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
			{
				if (acceptColumn(visitor, subvisitor, grid, parameter, columnIndex) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asGridVisitor(subvisitor).endVisitColumns(grid, parameter));
	}
	
	private static <T extends Grid, P, E extends Exception> EndVisit acceptColumn(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T grid, P parameter, int columnIndex) throws E
	{
		return asGridVisitor(subvisitor).visitColumn(grid, parameter, columnIndex);
	}
	
	private static <T extends Grid, P, E extends Exception> EndVisit acceptRows(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T grid, P parameter) throws E
	{
		if (asGridVisitor(subvisitor).visitRows(grid, parameter) != SKIP_CHILDREN)
		{
			int rowCount = grid.getRowCount();
		
			for (int rowIndex = 0; rowIndex < rowCount; rowIndex++)
			{
				if (acceptRow(visitor, subvisitor, grid, parameter, rowIndex) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asGridVisitor(subvisitor).endVisitRows(grid, parameter));
	}
	
	private static <T extends Grid, P, E extends Exception> EndVisit acceptRow(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T grid, P parameter, int rowIndex) throws E
	{
		if (asGridVisitor(subvisitor).visitRow(grid, parameter, rowIndex) != SKIP_CHILDREN)
		{
			int rowStartIndex = grid.getRowStartIndex(rowIndex);
			int rowEndIndex = grid.getRowEndIndex(rowIndex);
			
			for (int childIndex = rowStartIndex; childIndex <= rowEndIndex; childIndex++)
			{
				if (acceptChild(visitor, subvisitor, grid, parameter, childIndex) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}
		
		return nullEndVisit(asGridVisitor(subvisitor).endVisitRow(grid, parameter, rowIndex));
	}
	
	private void checkRowIndex(int rowIndex)
	{
		checkElementIndex(rowIndex, getRowCount(), "rowIndex");
	}
	
	private void checkColumnIndex(int columnIndex)
	{
		checkElementIndex(columnIndex, getColumnCount(), "columnIndex");
	}
	
	private void ensureColumns()
	{
		// TODO: improve
		
		int oldColumnCount = columns.size();
		List<GridColumn> newColumns = new ArrayList<GridColumn>(columnCount);
		
		for (int i = 0; i < oldColumnCount && i < columnCount; i++)
		{
			newColumns.add(columns.get(i));
		}
		
		for (int i = oldColumnCount; i < columnCount; i++)
		{
			newColumns.add(new GridColumn());
		}
		
		columns.clear();
		columns.addAll(newColumns);
	}
	
	private void layout()
	{
		// TODO: use int array
		List<Integer> rowStartIndexes = new ArrayList<Integer>();
		
		int rowIndex = 0;
		int columnIndex = 0;
		
		for (int childIndex = 0; childIndex < getComponentCount(); childIndex++)
		{
			if (columnIndex == 0)
			{
				rowStartIndexes.add(childIndex);
			}
			
			int columnSpan = getLayoutData(childIndex).getColumnSpan();
			columnIndex += columnSpan;
			
			checkState(columnIndex <= columnCount, "columnSpan would exceed columns: %s", get(childIndex));
			
			if (columnIndex == columnCount)
			{
				columnIndex = 0;
				rowIndex++;
			}
		}
		
		this.rowStartIndexes = Ints.toArray(rowStartIndexes);
	}
}
