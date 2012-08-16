/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingTextBoxPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.swing;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.swing.SwingTextBoxPeer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingTextBoxPeerTest.java 97403 2011-12-30 13:27:56Z mark@IIZUKA.CO.UK $
 */
@RunWith(JMock.class)
public class SwingTextBoxPeerTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private TextBox component;
	
	private SwingTextBoxPeer listener;
	
	private JTextField peer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		component = new TextBox();
		listener = new SwingTextBoxPeer(peerManager);
		peer = (JTextField) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(JTextField.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventColumns()
	{
		listener.propertyChange(new PropertyChangeEvent(component, TextBox.COLUMNS_PROPERTY, null, 100));
		assertEquals(100, peer.getColumns());
	}
	
	@Test
	public void propertyChangeEventText()
	{
		listener.propertyChange(new PropertyChangeEvent(component, TextBox.TEXT_PROPERTY, "", "test"));
		assertEquals("test", peer.getText());
	}
	
	@Test
	public void propertyChangeEventTextEqual()
	{
		peer.getDocument().addDocumentListener(mockery.mock(DocumentListener.class));
		listener.propertyChange(new PropertyChangeEvent(component, TextBox.TEXT_PROPERTY, "", ""));
	}
	
	@Test
	public void documentListenerInsert()
	{
		peer.setText("test");
		assertEquals("test", component.getText());
	}
	
	@Test
	public void documentListenerRemove()
	{
		peer.setText("test");
		peer.setText("");
		assertEquals("", component.getText());
	}
	
	@Test
	public void propertyChangeEventUnknown()
	{
		listener.propertyChange(new PropertyChangeEvent(component, "unknown", "", "test"));
	}
}
