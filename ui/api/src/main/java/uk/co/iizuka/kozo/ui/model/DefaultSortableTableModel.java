/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DefaultSortableTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import java.util.Arrays;
import java.util.Comparator;

import uk.co.iizuka.kozo.ui.SortOrder;
import uk.co.iizuka.kozo.ui.event.TableModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultSortableTableModel.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class DefaultSortableTableModel implements SortableTableModel
{
	// fields -----------------------------------------------------------------
	
	private final Comparator<Integer> rowComparator;
	
	private TableModel model;
	
	private int sortColumn;
	
	private SortOrder sortOrder;
	
	private Integer[] viewToModel;

	// types ------------------------------------------------------------------
	
	private class RowComparator implements Comparator<Integer>
	{
		// Comparator methods -----------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Integer integer1, Integer integer2)
		{
			int row1 = integer1.intValue();
			int row2 = integer2.intValue();
			
			if (sortOrder == SortOrder.UNSORTED)
			{
				return row1 - row2;
			}
			
			Object value1 = model.getValueAt(row1, sortColumn);
			Object value2 = model.getValueAt(row2, sortColumn);
			
			int result = 0;
			
			if (value1 == null && value2 == null)
			{
				result = 0;
			}
			else if (value1 == null)
			{
				result = -1;
			}
			else if (value2 == null)
			{
				result = 1;
			}
			else if (value1 != value2)
			{
				// FIXME: assumes both comparable
				result = ((Comparable) value1).compareTo(value2);
			}
			
			if (sortOrder == SortOrder.DESCENDING)
			{
				result = -result;
			}
			
			return result;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public DefaultSortableTableModel(TableModel model)
	{
		rowComparator = new RowComparator();
		
		sortColumn = 0;
		sortOrder = SortOrder.UNSORTED;
		
		setModel(model);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		// TODO: wrap with adapter to map listener event rows
		model.addTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		// TODO: wrap with adapter to map listener event rows
		model.removeTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return model.getRowCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return model.getColumnCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return model.getColumnName(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return model.getColumnClass(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return model.getValueAt(viewToModel(row), column);
	}
	
	// SortableTableModel methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSortColumn(int sortColumn)
	{
		this.sortColumn = sortColumn;
		invalidate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSortOrder(SortOrder sortOrder)
	{
		this.sortOrder = sortOrder;
		invalidate();
	}
	
	// public methods ---------------------------------------------------------
	
	public int getSortColumn()
	{
		return sortColumn;
	}
	
	public SortOrder getSortOrder()
	{
		return sortOrder;
	}
	
	public TableModel getModel()
	{
		return model;
	}
	
	public void setModel(TableModel model)
	{
		this.model = model;
		
		invalidate();
	}
	
	// private methods --------------------------------------------------------
	
	private void invalidate()
	{
		viewToModel = null;
	}
	
	private int viewToModel(int row)
	{
		if (viewToModel == null)
		{
			int rows = model.getRowCount();
			
			viewToModel = new Integer[rows];
			
			for (int i = 0; i < rows; i++)
			{
				// FIXME: dislike
				viewToModel[i] = Integer.valueOf(i);
			}
			
			Arrays.sort(viewToModel, rowComparator);
		}
		
		return viewToModel[row].intValue();
	}
}
