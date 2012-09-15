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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DelegatingTableCellRenderer implements TableCellRenderer
{
	// fields -----------------------------------------------------------------
	
	private final TableCellRenderer delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingTableCellRenderer(TableCellRenderer delegate)
	{
		if (delegate == null)
		{
			throw new NullPointerException("delegate cannot be null");
		}
		
		this.delegate = delegate;
	}

	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTableCellComponent(Table table, int row, int column)
	{
		return delegate.getTableCellComponent(table, row, column);
	}
	
	// protected methods ------------------------------------------------------
	
	protected TableCellRenderer getDelegate()
	{
		return delegate;
	}
}
