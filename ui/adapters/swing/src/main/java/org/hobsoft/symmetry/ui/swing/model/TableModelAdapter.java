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
package org.hobsoft.symmetry.ui.swing.model;

import javax.swing.event.TableModelListener;

import org.hobsoft.symmetry.ui.model.TableModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TableModelAdapter implements javax.swing.table.TableModel
{
	// fields -----------------------------------------------------------------
	
	private final TableModel model;
	
	// constructors -----------------------------------------------------------
	
	public TableModelAdapter(TableModel model)
	{
		this.model = model;
	}
	
	// TableModel methods -----------------------------------------------------
	
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
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return model.getValueAt(row, column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int row, int column)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		// TODO: implement
	}
}
