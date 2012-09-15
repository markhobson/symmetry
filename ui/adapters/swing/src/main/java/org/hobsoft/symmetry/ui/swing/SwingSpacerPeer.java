/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingSpacerPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.Box;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingSpacerPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwingSpacerPeer extends AbstractPeerHandler
{
	// constants --------------------------------------------------------------
	
	private static final Dimension SIZE = new Dimension();
	
	// constructors -----------------------------------------------------------
	
	public SwingSpacerPeer(PeerManager peerManager)
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
		return new Box.Filler(SIZE, SIZE, SIZE);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		// no-op
	}
}
