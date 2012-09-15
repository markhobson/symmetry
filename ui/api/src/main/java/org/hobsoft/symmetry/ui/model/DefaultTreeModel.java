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

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultTreeModel extends AbstractTreeModel
{
	// fields -----------------------------------------------------------------
	
	private final TreeNode root;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTreeModel()
	{
		this(null);
	}
	
	public DefaultTreeModel(TreeNode root)
	{
		this.root = root;
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRoot()
	{
		return root;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getChild(Object parent, int index)
	{
		return ((TreeNode) parent).getChildAt(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount(Object parent)
	{
		return ((TreeNode) parent).getChildCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildIndex(Object parent, Object child)
	{
		return ((TreeNode) parent).getIndex((TreeNode) child);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf(Object node)
	{
		return (getChildCount(node) == 0);
	}
}
