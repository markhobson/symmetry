/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingButtonPeerTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.swing.SwingButtonPeer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * 
 * @author Mark Hobson
 */
@RunWith(JMock.class)
public class SwingButtonPeerTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private Button component;
	
	private SwingButtonPeer listener;
	
	private JButton peer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		component = new Button();
		listener = new SwingButtonPeer(peerManager);
		peer = (JButton) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(JButton.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventActionListeners()
	{
		final ActionListener actionListener = mockery.mock(ActionListener.class);
		
		mockery.checking(new Expectations() { {
			oneOf(actionListener).actionPerformed(new ActionEvent(component));
		} });
		
		ActionListener[] oldListeners = new ActionListener[0];
		ActionListener[] newListeners = new ActionListener[] {actionListener};
		PropertyChangeEvent event = new PropertyChangeEvent(component, Button.ACTION_LISTENERS_PROPERTY, oldListeners,
			newListeners);
		listener.propertyChange(event);
		
		peer.doClick();
	}
	
	@Test
	public void propertyChangeEventText()
	{
		listener.propertyChange(new PropertyChangeEvent(component, Label.TEXT_PROPERTY, "", "test"));
		
		assertEquals("test", peer.getText());
	}
}
