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
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class SelectionEvent extends ComponentEvent
{
	// fields -----------------------------------------------------------------
	
	private final int index;

	// constructors -----------------------------------------------------------
	
	// TODO: introduce Selectable or ItemSelectable interface instead of ComboBox for source?
	public SelectionEvent(ComboBox<?> source, int index)
	{
		super(source);
		
		if (index < 0)
		{
			throw new IllegalArgumentException("index must be non-negative: " + index);
		}
		
		this.index = index;
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComboBox<?> getSource()
	{
		return (ComboBox<?>) super.getSource();
	}
	
	// public methods ---------------------------------------------------------
	
	public int getIndex()
	{
		return index;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		
		hashCode = (hashCode * 31) + index;
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof SelectionEvent))
		{
			return false;
		}
		
		SelectionEvent event = (SelectionEvent) object;
		
		return getSource().equals(event.getSource())
			&& index == event.getIndex();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ", index=" + index + "]";
	}
}
