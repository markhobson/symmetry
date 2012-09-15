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

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class TreeEvent extends ComponentEvent
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// fields -----------------------------------------------------------------
	
	private TreePath path;
	
	// constructors -----------------------------------------------------------
	
	public TreeEvent(Tree source, TreePath path)
	{
		super(source);
		this.path = path;
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tree getSource()
	{
		return (Tree) super.getSource();
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
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + path.hashCode();
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof TreeEvent))
		{
			return false;
		}
		
		TreeEvent event = (TreeEvent) object;
		
		return super.equals(event)
			&& path.equals(event.path);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ",path=" + path + "]";
	}
}
