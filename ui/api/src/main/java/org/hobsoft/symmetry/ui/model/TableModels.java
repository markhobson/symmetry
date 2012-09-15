/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
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
