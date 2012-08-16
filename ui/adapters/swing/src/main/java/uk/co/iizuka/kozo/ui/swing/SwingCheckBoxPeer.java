/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingCheckBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import javax.swing.JCheckBox;

import uk.co.iizuka.kozo.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingCheckBoxPeer.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class SwingCheckBoxPeer extends SwingToggleButtonPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingCheckBoxPeer(PeerManager peerManager)
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
		return new JCheckBox();
	}
}
