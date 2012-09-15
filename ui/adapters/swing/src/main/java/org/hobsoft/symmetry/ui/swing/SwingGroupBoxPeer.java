/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingGroupBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.GroupBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingGroupBoxPeer.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class SwingGroupBoxPeer extends SwingBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingGroupBoxPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerFactory methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		JComponent peer = (JComponent) super.createPeer(component);
		peer.setBorder(BorderFactory.createTitledBorder(""));
		return peer;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		GroupBox component = (GroupBox) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JComponent peer = (JComponent) getPeerManager().getPeer(component);
		TitledBorder border = (TitledBorder) peer.getBorder();
		
		if (GroupBox.TITLE_PROPERTY.equals(name))
		{
			border.setTitle((String) newValue);
		}
		else
		{
			super.propertyChange(event);
		}
	}
}
