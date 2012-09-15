/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingLabelPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.swing.SwingLabelPeer;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingLabelPeerTest
{
	// fields -----------------------------------------------------------------
	
	private Label component;
	
	private SwingLabelPeer listener;
	
	private JLabel peer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		component = new Label();
		listener = new SwingLabelPeer(peerManager);
		peer = (JLabel) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(JLabel.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventImage()
	{
		// TODO: implement
	}
	
	@Test
	public void propertyChangeEventText()
	{
		listener.propertyChange(new PropertyChangeEvent(component, Label.TEXT_PROPERTY, "", "test"));
		assertEquals("test", peer.getText());
	}
	
	@Test
	public void propertyChangeEventToolTip()
	{
		listener.propertyChange(new PropertyChangeEvent(component, Label.TOOL_TIP_PROPERTY, "", "test"));
		assertEquals("test", peer.getToolTipText());
	}
	
	@Test
	public void propertyChangeEventUnknown()
	{
		listener.propertyChange(new PropertyChangeEvent(component, "unknown", "", "test"));
	}
}
