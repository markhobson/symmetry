/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/event/ActionListenerAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing.event;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.event.ActionListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ActionListenerAdapter.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
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
