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

import javax.swing.event.EventListenerList;

import org.hobsoft.symmetry.ui.event.TreeModelEvent;
import org.hobsoft.symmetry.ui.event.TreeModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractTreeModel implements TreeModel
{
	// fields -----------------------------------------------------------------
	
	private final EventListenerList listeners;
	
	// constructors -----------------------------------------------------------
	
	public AbstractTreeModel()
	{
		listeners = new EventListenerList();
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTreeModelListener(TreeModelListener listener)
	{
		listeners.add(TreeModelListener.class, listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener listener)
	{
		listeners.remove(TreeModelListener.class, listener);
	}
	
	// protected methods ------------------------------------------------------
	
	protected void fireNodeDeletedEvent(TreePath path)
	{
		Object[] listenerArray = listeners.getListenerList();
		TreeModelEvent event = null;
		
		for (int i = 0; i < listenerArray.length; i += 2)
		{
			if (listenerArray[i] == TreeModelListener.class)
			{
				if (event == null)
				{
					event = new TreeModelEvent(this, path);
				}
				
				((TreeModelListener) listenerArray[i + 1]).nodeDeleted(event);
			}
		}
	}
}
