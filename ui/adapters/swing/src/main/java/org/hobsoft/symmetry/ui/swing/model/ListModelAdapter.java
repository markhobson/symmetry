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

import javax.swing.event.ListDataListener;

import org.hobsoft.symmetry.ui.model.ListModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ListModelAdapter implements javax.swing.ListModel
{
	// fields -----------------------------------------------------------------
	
	private final ListModel<?> model;
	
	// constructors -----------------------------------------------------------
	
	public ListModelAdapter(ListModel<?> model)
	{
		this.model = model;
	}
	
	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize()
	{
		return model.getItemCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getElementAt(int index)
	{
		return model.getItem(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListDataListener(ListDataListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListDataListener(ListDataListener listener)
	{
		// TODO: implement
	}
}
