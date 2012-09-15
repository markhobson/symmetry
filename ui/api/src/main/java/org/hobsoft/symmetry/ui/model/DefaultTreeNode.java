/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DefaultTreeNode.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultTreeNode implements TreeNode, Serializable
{
	// constants --------------------------------------------------------------
	
	private static final TreeNode[] EMPTY_TREE_NODE_ARRAY = new TreeNode[0];
	
	private static final long serialVersionUID = 4381266864241026670L;
	
	// fields -----------------------------------------------------------------
	
	private final List<TreeNode> childList;
	
	private Object value;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTreeNode()
	{
		this(null);
	}
	
	public DefaultTreeNode(Object value)
	{
		this(value, EMPTY_TREE_NODE_ARRAY);
	}
	
	public DefaultTreeNode(Object value, TreeNode... children)
	{
		this.value = value;
		
		childList = new ArrayList<TreeNode>();
		
		add(children);
	}
	
	// public methods ---------------------------------------------------------
	
	public void add(TreeNode child)
	{
		childList.add(child);
	}
	
	public void add(TreeNode... children)
	{
		for (TreeNode child : children)
		{
			add(child);
		}
	}
	
	public void remove(TreeNode child)
	{
		childList.remove(child);
	}
	
	public void remove(TreeNode... children)
	{
		for (TreeNode child : children)
		{
			remove(child);
		}
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public void setValue(Object value)
	{
		this.value = value;
	}
	
	// TreeNode methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeNode getChildAt(int index)
	{
		return childList.get(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount()
	{
		return childList.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getIndex(TreeNode child)
	{
		return childList.indexOf(child);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return (value == null) ? "null" : value.toString();
	}
}
