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

import org.hobsoft.symmetry.ui.event.TableModelListener;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingTableModel implements TableModel
{
	// fields -----------------------------------------------------------------
	
	private final TableModel delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingTableModel(TableModel delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate cannot be null");
	}
	
	// TableModel methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		delegate.addTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		delegate.removeTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return delegate.getRowCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return delegate.getColumnCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return delegate.getColumnName(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return delegate.getColumnClass(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return delegate.getValueAt(row, column);
	}
	
	// public methods ---------------------------------------------------------
	
	public TableModel getDelegate()
	{
		return delegate;
	}
}
