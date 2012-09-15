/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/TreeModelEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventObject;

import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeModelEvent.java 88175 2011-05-18 09:28:23Z mark@IIZUKA.CO.UK $
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
