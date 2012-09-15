/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingRadioPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import javax.swing.JRadioButton;

import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingRadioPeer.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class SwingRadioPeer extends SwingToggleButtonPeer
{
	// constructors -----------------------------------------------------------
	
	public SwingRadioPeer(PeerManager peerManager)
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
		return new JRadioButton();
	}
}
