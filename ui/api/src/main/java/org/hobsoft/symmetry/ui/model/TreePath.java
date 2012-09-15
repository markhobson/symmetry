/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/TreePath.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.io.Serializable;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreePath implements Serializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private static final TreePath[] EMPTY_TREEPATH_ARRAY = new TreePath[0];
	
	// fields -----------------------------------------------------------------
	
	private TreePath parent;
	
	private Object node;
	
	// constructors -----------------------------------------------------------
	
	public TreePath(Object node)
	{
		this(null, node);
	}
	
	public TreePath(TreePath parent, Object node)
	{
		this.parent = parent;
		this.node = node;
	}
	
	// public methods ---------------------------------------------------------
	
	public TreePath getParentPath()
	{
		return parent;
	}
	
	public Object getNode()
	{
		return node;
	}
	
	public int getLength()
	{
		return (parent == null) ? 1 : parent.getLength() + 1;
	}
	
	public TreePath[] getAncestors()
	{
		int length = getLength();
		
		if (length <= 1)
		{
			return EMPTY_TREEPATH_ARRAY;
		}
		
		TreePath[] paths = new TreePath[length - 1];
		int i = length - 2;
		
		for (TreePath path = getParentPath(); path != null; path = path.getParentPath())
		{
			paths[i--] = path;
		}
		
		return paths;
	}
	
	public TreePath[] getAncestorsAndSelf()
	{
		int length = getLength();
		
		if (length == 0)
		{
			return EMPTY_TREEPATH_ARRAY;
		}
		
		TreePath[] paths = new TreePath[length];
		int i = length - 1;
		
		for (TreePath path = this; path != null; path = path.getParentPath())
		{
			paths[i--] = path;
		}
		
		return paths;
	}
	
	public boolean isAncestorOrSelf(TreePath path)
	{
		while (path != null)
		{
			if (equals(path))
			{
				return true;
			}
			
			path = path.getParentPath();
		}
		
		return false;
	}
	
	public boolean isAncestor(TreePath path)
	{
		return !equals(path) && isAncestorOrSelf(path);
	}
	
	public boolean isDescendantOrSelf(TreePath path)
	{
		TreePath descendant = this;
		
		while (descendant != null)
		{
			if (path.equals(descendant))
			{
				return true;
			}
			
			descendant = descendant.getParentPath();
		}
		
		return false;
	}
	
	public boolean isDescendant(TreePath path)
	{
		return !equals(path) && isDescendantOrSelf(path);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return node.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof TreePath))
		{
			return false;
		}
		
		TreePath thatPath = (TreePath) object;
		
		if (thatPath.getLength() != getLength())
		{
			return false;
		}
		
		TreePath thisPath = this;
		
		while (thisPath != null)
		{
			if (!thisPath.getNode().equals(thatPath.getNode()))
			{
				return false;
			}
			
			thisPath = thisPath.getParentPath();
			thatPath = thatPath.getParentPath();
		}
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for (TreePath path = this; path != null; path = path.getParentPath())
		{
			if (builder.length() > 0)
			{
				builder.insert(0, ',');
			}
			
			builder.insert(0, path.getNode());
		}
		
		builder.insert(0, '[');
		builder.append(']');
		
		return builder.toString();
	}
}
