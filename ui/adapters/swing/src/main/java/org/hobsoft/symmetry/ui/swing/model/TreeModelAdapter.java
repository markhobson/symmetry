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

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import org.hobsoft.symmetry.ui.model.TreeModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreeModelAdapter implements javax.swing.tree.TreeModel
{
	// fields -----------------------------------------------------------------
	
	private final TreeModel model;
	
	// constructors -----------------------------------------------------------
	
	public TreeModelAdapter(TreeModel model)
	{
		this.model = model;
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRoot()
	{
		return model.getRoot();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getChild(Object parent, int index)
	{
		return model.getChild(parent, index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount(Object parent)
	{
		return model.getChildCount(parent);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf(Object node)
	{
		// TODO: properly
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child)
	{
		return model.getChildIndex(parent, child);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTreeModelListener(TreeModelListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener listener)
	{
		// TODO: implement
	}
}
