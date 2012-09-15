/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingSpacerPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.Box;

import org.hobsoft.symmetry.ui.Spacer;
import org.hobsoft.symmetry.ui.swing.SwingSpacerPeer;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingSpacerPeerTest
{
	// fields -----------------------------------------------------------------
	
	private Spacer component;
	
	private SwingSpacerPeer listener;
	
	private Box.Filler peer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		component = new Spacer();
		listener = new SwingSpacerPeer(peerManager);
		peer = (Box.Filler) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(Box.Filler.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventUnknown()
	{
		listener.propertyChange(new PropertyChangeEvent(component, "unknown", "", "test"));
	}
}
