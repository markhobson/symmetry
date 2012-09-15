/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/TableModels.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableModels.java 91222 2011-08-03 09:53:13Z mark@IIZUKA.CO.UK $
 */
public final class TableModels
{
	// TODO: avoid TableModel/BeanTableModel duplication when models reconsidered
	
	// constructors -----------------------------------------------------------
	
	private TableModels()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> TableModel cacheRowCount(TableModel delegate)
	{
		return new DelegatingTableModel(delegate)
		{
			private int rowCount = -1;
			
			@Override
			public int getRowCount()
			{
				if (rowCount == -1)
				{
					rowCount = super.getRowCount();
				}
				
				return rowCount;
			}
		};
	}
	
	public static <T> BeanTableModel<T> cacheRowCount(BeanTableModel<T> delegate)
	{
		return new DelegatingBeanTableModel<T>(delegate)
		{
			private int rowCount = -1;
			
			@Override
			public int getRowCount()
			{
				if (rowCount == -1)
				{
					rowCount = super.getRowCount();
				}
				
				return rowCount;
			}
		};
	}
	
	public static <T> TableModel addColumn(TableModel delegate, final String columnName, final Class<?> columnType,
		final Object value)
	{
		final int newColumn = delegate.getColumnCount();
		
		return new DelegatingTableModel(delegate)
		{
			@Override
			public int getColumnCount()
			{
				return super.getColumnCount() + 1;
			}
			
			@Override
			public String getColumnName(int column)
			{
				return (column == newColumn) ? columnName : super.getColumnName(column);
			}
			
			@Override
			public Class<?> getColumnClass(int column)
			{
				return (column == newColumn) ? columnType : super.getColumnClass(column);
			}
			
			@Override
			public Object getValueAt(int row, int column)
			{
				return (column == newColumn) ? value : super.getValueAt(row, column);
			}
		};
	}
	
	public static <T> BeanTableModel<T> addColumn(BeanTableModel<T> delegate, final String columnName,
		final Class<?> columnType, final Object value)
	{
		final int newColumn = delegate.getColumnCount();
		
		return new DelegatingBeanTableModel<T>(delegate)
		{
			@Override
			public int getColumnCount()
			{
				return super.getColumnCount() + 1;
			}
			
			@Override
			public String getColumnName(int column)
			{
				return (column == newColumn) ? columnName : super.getColumnName(column);
			}
			
			@Override
			public Class<?> getColumnClass(int column)
			{
				return (column == newColumn) ? columnType : super.getColumnClass(column);
			}
			
			@Override
			public Object getValueAt(int row, int column)
			{
				return (column == newColumn) ? value : super.getValueAt(row, column);
			}
		};
	}
}
