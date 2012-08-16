/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingLabelPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Label;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingLabelPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwingLabelPeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingLabelPeer(PeerManager peerManager)
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
		return new JLabel();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Label component = (Label) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JLabel label = (JLabel) getPeerManager().getPeer(component);
		
		if (Label.IMAGE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Label.TEXT_PROPERTY.equals(name))
		{
			label.setText((String) newValue);
		}
		else if (Label.TOOL_TIP_PROPERTY.equals(name))
		{
			label.setToolTipText((String) newValue);
		}
	}
}
