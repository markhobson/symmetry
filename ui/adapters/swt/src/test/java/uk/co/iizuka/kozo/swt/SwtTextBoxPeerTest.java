/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/test/java/uk/co/iizuka/kozo/swt/SwtTextBoxPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.swt;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.swt.SwtTextBoxPeer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtTextBoxPeerTest.java 97402 2011-12-30 13:16:45Z mark@IIZUKA.CO.UK $
 */
@RunWith(JMock.class)
public class SwtTextBoxPeerTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private TextBox component;
	
	private SwtTextBoxPeer listener;
	
	private Text peer;
	
	private Shell shell;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		shell = new Shell(Display.getDefault());
		peerManager.setPeer(null, shell);
		
		component = new TextBox();
		listener = new SwtTextBoxPeer(peerManager);
		peer = (Text) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	@After
	public void tearDown()
	{
		shell.dispose();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(Text.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventColumns()
	{
		listener.propertyChange(new PropertyChangeEvent(component, TextBox.COLUMNS_PROPERTY, null, 100));
		assertEquals(100, peer.getTextLimit());
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
		peer.addModifyListener(mockery.mock(ModifyListener.class));
		listener.propertyChange(new PropertyChangeEvent(component, TextBox.TEXT_PROPERTY, "", ""));
	}
	
	@Test
	public void modifyListener()
	{
		peer.setText("test");
		assertEquals("test", component.getText());
	}
	
	@Test
	public void propertyChangeEventUnknown()
	{
		listener.propertyChange(new PropertyChangeEvent(component, "unknown", "", "test"));
	}
}
