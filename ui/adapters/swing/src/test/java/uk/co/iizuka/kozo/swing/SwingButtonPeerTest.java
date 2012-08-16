/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingButtonPeerTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.event.ActionListener;
import uk.co.iizuka.kozo.ui.swing.SwingButtonPeer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingButtonPeerTest.java 97404 2011-12-30 13:32:15Z mark@IIZUKA.CO.UK $
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
