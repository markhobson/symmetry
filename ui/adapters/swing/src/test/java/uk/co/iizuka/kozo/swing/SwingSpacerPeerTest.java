/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingSpacerPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.Box;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Spacer;
import uk.co.iizuka.kozo.ui.swing.SwingSpacerPeer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingSpacerPeerTest.java 97405 2011-12-30 13:36:34Z mark@IIZUKA.CO.UK $
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
