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

import java.util.EventObject;

import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class TreeModelEvent extends EventObject
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// fields -----------------------------------------------------------------
	
	private TreePath path;
	
	// constructors -----------------------------------------------------------
	
	public TreeModelEvent(TreeModel source, TreePath path)
	{
		super(source);
		this.path = path;
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeModel getSource()
	{
		return (TreeModel) super.getSource();
	}
	
	// public methods ---------------------------------------------------------
	
	public TreePath getPath()
	{
		return path;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ",path=" + path + "]";
	}
}
