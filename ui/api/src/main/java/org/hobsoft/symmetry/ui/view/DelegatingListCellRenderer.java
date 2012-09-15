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

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the item type
 */
public abstract class DelegatingListCellRenderer<T> implements ListCellRenderer<T>
{
	// fields -----------------------------------------------------------------
	
	private final ListCellRenderer<T> delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingListCellRenderer(ListCellRenderer<T> delegate)
	{
		if (delegate == null)
		{
			throw new NullPointerException("delegate cannot be null");
		}
		
		this.delegate = delegate;
	}
	
	// ListCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return delegate.getListCellComponent(comboBox, index);
	}
	
	// protected methods ------------------------------------------------------
	
	protected ListCellRenderer<T> getDelegate()
	{
		return delegate;
	}
}