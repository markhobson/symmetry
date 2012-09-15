/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/TreeEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
