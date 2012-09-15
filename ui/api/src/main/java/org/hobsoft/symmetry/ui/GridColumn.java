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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.layout.Length;

import com.google.common.base.Objects;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class GridColumn
{
	// TODO: make iterable for column components
	// TODO: add getIndex
	
	// constants --------------------------------------------------------------
	
	private static final Length DEFAULT_WIDTH = null;
	
	// fields -----------------------------------------------------------------
	
	private Length width;
	
	// constructors -----------------------------------------------------------
	
	GridColumn()
	{
		setWidth(DEFAULT_WIDTH);
	}
	
	// public methods ---------------------------------------------------------
	
	public Length getWidth()
	{
		return width;
	}
	
	public void setWidth(Length width)
	{
		this.width = width;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(width);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof GridColumn))
		{
			return false;
		}
		
		GridColumn column = (GridColumn) object;
		
		return Objects.equal(width, column.width);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[width=%s]", getClass().getName(), width);
	}
}
