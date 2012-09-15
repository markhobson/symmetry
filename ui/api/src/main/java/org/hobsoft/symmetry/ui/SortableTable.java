/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/SortableTable.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.event.TableHeaderListener;
import org.hobsoft.symmetry.ui.model.DefaultSortableTableModel;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.model.SortableTableModel;
import org.hobsoft.symmetry.ui.model.TableModel;
import org.hobsoft.symmetry.ui.view.LabelTableHeaderRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SortableTable extends Table
{
	// constants --------------------------------------------------------------
	
	public static final String SORT_ORDER_PROPERTY = "sortOrder";
	
	public static final String SORT_COLUMN_PROPERTY = "sortColumn";

	// types ------------------------------------------------------------------
	
	private static class SortableTableHeaderListener implements TableHeaderListener
	{
		// fields -----------------------------------------------------------------
		
		public static final SortableTableHeaderListener INSTANCE = new SortableTableHeaderListener();
		
		// TableHeaderListener methods ----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void tableHeaderSelected(TableEvent event)
		{
			SortableTable table = (SortableTable) event.getSource();
			int column = event.getColumn();
			
			if (column == table.getSortColumn())
			{
				SortOrder order = table.getSortOrder();
				
				if (order == SortOrder.UNSORTED)
				{
					order = SortOrder.ASCENDING;
				}
				else if (order == SortOrder.ASCENDING)
				{
					order = SortOrder.DESCENDING;
				}
				else
				{
					order = table.isAlwaysSorted() ? SortOrder.ASCENDING : SortOrder.UNSORTED;
				}
				
				table.setSortOrder(order);
				
				if (order == SortOrder.UNSORTED)
				{
					table.setSortColumn(-1);
				}
			}
			else
			{
				table.setSortColumn(column);
				table.setSortOrder(SortOrder.ASCENDING);
			}
		}
	}
	
	private static class SortableTableHeaderRenderer extends LabelTableHeaderRenderer
	{
		// TODO: decorate existing table header renderer instead of extending basic table header renderer
		
		// TableCellRenderer methods ----------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Label getTableCellComponent(Table table, int row, int column)
		{
			super.getTableCellComponent(table, row, column);
			setToolTip("Order by " + getText());
			
			SortableTable sortableTable = (SortableTable) table;
			if (column == sortableTable.getSortColumn())
			{
				SortOrder order = sortableTable.getSortOrder();
				
				// TODO: set image instead when implemented
				if (order == SortOrder.ASCENDING)
				{
					setText(getText() + " ^");
				}
				else if (order == SortOrder.DESCENDING)
				{
					setText(getText() + " v");
				}
			}
			return this;
		}
	}
	
	private static class TablePropertyChangeListener implements PropertyChangeListener
	{
		// fields -----------------------------------------------------------------
		
		public static final TablePropertyChangeListener INSTANCE = new TablePropertyChangeListener();
		
		// PropertyChangeListener methods -----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			Table table = (Table) event.getSource();
			String name = event.getPropertyName();
			Object value = event.getNewValue();
			SortableTableModel model = (SortableTableModel) table.getModel();
			
			if (name == SORT_COLUMN_PROPERTY)
			{
				model.setSortColumn(((Integer) value).intValue());
			}
			else if (name == SORT_ORDER_PROPERTY)
			{
				model.setSortOrder((SortOrder) value);
			}
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private int sortColumn;
	
	private SortOrder sortOrder;
	
	private boolean alwaysSorted;
	
	// constructors -----------------------------------------------------------
	
	public SortableTable()
	{
		this(new DefaultTableModel());
	}
	
	public SortableTable(TableModel model)
	{
		super(decorateModel(model));
		
		addPropertyChangeListener(SORT_COLUMN_PROPERTY, TablePropertyChangeListener.INSTANCE);
		addPropertyChangeListener(SORT_ORDER_PROPERTY, TablePropertyChangeListener.INSTANCE);
		
		addHeaderListener(SortableTableHeaderListener.INSTANCE);
		setHeaderRenderer(new SortableTableHeaderRenderer());
		
		setSortColumn(-1);
		setSortOrder(SortOrder.UNSORTED);
		setAlwaysSorted(false);
	}
	
	// Table methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModel(TableModel model)
	{
		super.setModel(decorateModel(model));
	}
	
	// public methods ---------------------------------------------------------
	
	public int getSortColumn()
	{
		return sortColumn;
	}
	
	public void setSortColumn(int sortColumn)
	{
		int oldSortColumn = this.sortColumn;
		this.sortColumn = sortColumn;
		firePropertyChange(SORT_COLUMN_PROPERTY, oldSortColumn, sortColumn);
	}
	
	public SortOrder getSortOrder()
	{
		return sortOrder;
	}
	
	public void setSortOrder(SortOrder sortOrder)
	{
		SortOrder oldSortOrder = this.sortOrder;
		this.sortOrder = sortOrder;
		firePropertyChange(SORT_ORDER_PROPERTY, oldSortOrder, sortOrder);
	}
	
	public boolean isAlwaysSorted()
	{
		return alwaysSorted;
	}
	
	public void setAlwaysSorted(boolean alwaysSorted)
	{
		if (alwaysSorted)
		{
			if (getSortColumn() == -1)
			{
				setSortColumn(0);
			}
			
			if (getSortOrder() == SortOrder.UNSORTED)
			{
				setSortOrder(SortOrder.ASCENDING);
			}
		}
		
		this.alwaysSorted = alwaysSorted;
	}
	
	// private methods --------------------------------------------------------
	
	private static SortableTableModel decorateModel(TableModel model)
	{
		SortableTableModel sortableModel;
		
		if (model instanceof SortableTableModel)
		{
			sortableModel = (SortableTableModel) model;
		}
		else
		{
			sortableModel = new DefaultSortableTableModel(model);
		}
		
		return sortableModel;
	}
}
