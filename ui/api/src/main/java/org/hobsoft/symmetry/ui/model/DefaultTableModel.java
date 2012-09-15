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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;
import static org.hobsoft.symmetry.ui.model.Utilities.crop;
import static org.hobsoft.symmetry.ui.model.Utilities.grow;

import static com.google.common.base.Objects.equal;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultTableModel extends AbstractTableModel
{
	// TODO: insertRow(s), deleteRow(s), getRows, setRows
	// TODO: addColumn(s), insertColumn(s), deleteColumn(s), getColumns, setColumns
	// TODO: getData, setData
	
	// fields -----------------------------------------------------------------
	
	private final List<String> columnNames;
	
	private final List<Class<?>> columnClasses;
	
	private final List<List<Object>> rows;
	
	private int columns;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTableModel(Object[]... rows)
	{
		this(toLists(rows));
	}
	
	public DefaultTableModel(Collection<? extends Collection<?>> rows)
	{
		columnNames = new ArrayList<String>();
		columnClasses = new ArrayList<Class<?>>();
		this.rows = new ArrayList<List<Object>>();
		columns = 0;
		
		addRows(rows);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return rows.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return columns;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		checkColumn(column);
		
		return safeGet(columnNames, column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		checkColumn(column);
		
		return safeGet(columnClasses, column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		checkColumn(column);
		checkRow(row);
		
		return safeGet(rows.get(row), column);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setSize(int rows, int columns)
	{
		setRowCountInternal(rows);
		setColumnCountInternal(columns);
		
		fireTableChangedEvent();
	}
	
	public void setRowCount(int rows)
	{
		setRowCountInternal(rows);
		
		fireTableChangedEvent();
	}
	
	public void setColumnCount(int columns)
	{
		setColumnCountInternal(columns);
		
		fireTableChangedEvent();
	}
	
	public String[] getColumnNames()
	{
		// TODO: return List<String>?
		return columnNames.toArray(new String[columnNames.size()]);
	}
	
	public void setColumnNames(String... columnNames)
	{
		for (int column = 0; column < columnNames.length; column++)
		{
			setColumnNameInternal(column, columnNames[column]);
		}
		
		fireTableChangedEvent();
	}
	
	public void setColumnName(int column, String columnName)
	{
		setColumnNameInternal(column, columnName);
		
		fireTableChangedEvent();
	}
	
	public Class<?>[] getColumnClasses()
	{
		// TODO: return List<Class<?>>?
		return columnClasses.toArray(new Class[columnClasses.size()]);
	}
	
	public void setColumnClasses(Class<?>... columnClasses)
	{
		for (int column = 0; column < columnClasses.length; column++)
		{
			setColumnClassInternal(column, columnClasses[column]);
		}
		
		fireTableChangedEvent();
	}
	
	public void setColumnClass(int column, Class<?> columnClass)
	{
		setColumnClassInternal(column, columnClass);
		
		fireTableChangedEvent();
	}
	
	public void setValueAt(int row, int column, Object value)
	{
		checkNonNegative(row, "row");
		checkNonNegative(column, "column");
		growRows(row + 1);
		growColumns(column + 1);

		safeSet(rows.get(row), column, value);
		
		fireTableChangedEvent();
	}
	
	public void clear()
	{
		rows.clear();
		
		fireTableChangedEvent();
	}
	
	public void addRow(Object... row)
	{
		addRow(Arrays.asList(row));
	}
	
	public void addRow(Collection<?> row)
	{
		addRowInternal(row);
		
		fireTableChangedEvent();
	}
	
	public void addRows(Object[]... rows)
	{
		addRows(toLists(rows));
	}
	
	public void addRows(Collection<? extends Collection<?>> rows)
	{
		for (Collection<?> row : rows)
		{
			addRowInternal(row);
		}
		
		fireTableChangedEvent();
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = columnNames.hashCode();
		hashCode = (hashCode * 37) + columnClasses.hashCode();
		hashCode = (hashCode * 37) + rows.hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof TableModel))
		{
			return false;
		}
		
		TableModel model = (TableModel) object;
		
		if (getColumnCount() != model.getColumnCount())
		{
			return false;
		}
		
		if (getRowCount() != model.getRowCount())
		{
			return false;
		}
		
		for (int column = 0; column < getColumnCount(); column++)
		{
			if (!equal(getColumnName(column), model.getColumnName(column)))
			{
				return false;
			}
		}
		
		for (int column = 0; column < getColumnCount(); column++)
		{
			if (!equal(getColumnClass(column), model.getColumnClass(column)))
			{
				return false;
			}
		}

		for (int row = 0; row < getRowCount(); row++)
		{
			for (int column = 0; column < getColumnCount(); column++)
			{
				if (!equal(getValueAt(row, column), model.getValueAt(row, column)))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	// private methods --------------------------------------------------------
	
	private void setRowCountInternal(int rows)
	{
		checkNonNegative(rows, "rows");
		
		if (rows < getRowCount())
		{
			crop(this.rows, rows);
		}
		else
		{
			growRows(rows);
		}
	}
	
	private void setColumnCountInternal(int columns)
	{
		checkNonNegative(columns, "columns");

		// TODO: crop columnNames, columnClasses and rows
		this.columns = columns;
	}
	
	private void setColumnNameInternal(int column, String columnName)
	{
		checkNonNegative(column, "column");
		growColumns(column + 1);
		
		safeSet(columnNames, column, columnName);
	}
	
	private void setColumnClassInternal(int column, Class<?> columnClass)
	{
		checkNonNegative(column, "column");
		growColumns(column + 1);
		
		safeSet(columnClasses, column, columnClass);
	}
	
	private void addRowInternal(Collection<?> row)
	{
		rows.add(new ArrayList<Object>(row));
		
		growColumns(row.size());
	}
	
	private void growRows(int rows)
	{
		while (this.rows.size() < rows)
		{
			this.rows.add(new ArrayList<Object>());
		}
	}
	
	private void growColumns(int columns)
	{
		this.columns = Math.max(this.columns, columns);
	}
	
	private static <E> E safeGet(List<E> list, int index)
	{
		if (list == null || index >= list.size())
		{
			return null;
		}
		
		return list.get(index);
	}
	
	private static <E> E safeSet(List<E> list, int index, E element)
	{
		grow(list, index + 1);
		return list.set(index, element);
	}
	
	private static List<List<Object>> toLists(Object[][] arrays)
	{
		List<List<Object>> lists = new ArrayList<List<Object>>();
		
		for (Object[] array : arrays)
		{
			lists.add(Arrays.asList(array));
		}
		
		return lists;
	}
}
