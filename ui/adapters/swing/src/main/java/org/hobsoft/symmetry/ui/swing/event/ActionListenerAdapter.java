/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/event/ActionListenerAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.event;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ActionListenerAdapter extends AbstractAdapter implements java.awt.event.ActionListener
{
	// fields -----------------------------------------------------------------
	
	private ActionListener listener;
	
	// constructors -----------------------------------------------------------
	
	public ActionListenerAdapter(Component component, ActionListener listener)
	{
		super(component);
		
		this.listener = listener;
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(java.awt.event.ActionEvent event)
	{
		listener.actionPerformed(new ActionEvent(getComponent()));
	}
}
