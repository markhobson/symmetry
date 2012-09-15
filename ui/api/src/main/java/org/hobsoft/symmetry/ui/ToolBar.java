/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ToolBar.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ToolBar extends Box
{
	// constants --------------------------------------------------------------
	
	private static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;
	
	private static final Action[] EMPTY_ACTIONS = new Action[0];

	// constructors -----------------------------------------------------------
	
	public ToolBar()
	{
		this(DEFAULT_ORIENTATION);
	}
	
	public ToolBar(Orientation orientation)
	{
		this(orientation, EMPTY_ACTIONS);
	}
	
	public ToolBar(Component... children)
	{
		this(DEFAULT_ORIENTATION, children);
	}
	
	public ToolBar(Action... actions)
	{
		this(DEFAULT_ORIENTATION, actions);
	}
	
	public ToolBar(Orientation orientation, Component... children)
	{
		super(orientation, children);
	}
	
	public ToolBar(Orientation orientation, Action... actions)
	{
		super(orientation);
		
		add(actions);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, ToolBar.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void add(Action... actions)
	{
		for (Action action : actions)
		{
			add(new Button(action));
		}
	}
	
	public void remove(Action action)
	{
		// TODO: implement
	}
}
