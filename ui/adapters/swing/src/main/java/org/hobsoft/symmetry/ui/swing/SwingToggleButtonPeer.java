/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingToggleButtonPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.ToggleButton;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingToggleButtonPeer.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class SwingToggleButtonPeer extends SwingButtonPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingToggleButtonPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		return new JToggleButton();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Object component = event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		AbstractButton button = (AbstractButton) getPeerManager().getPeer(component);
		
		if (ToggleButton.SELECTED_PROPERTY.equals(name))
		{
			button.setSelected(((Boolean) newValue).booleanValue());
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
