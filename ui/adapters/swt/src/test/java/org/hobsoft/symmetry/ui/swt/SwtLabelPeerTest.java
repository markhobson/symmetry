/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/test/java/uk/co/iizuka/kozo/swt/SwtLabelPeerTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.swt.SwtLabelPeer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtLabelPeerTest
{
	// fields -----------------------------------------------------------------
	
	private Label component;
	
	private SwtLabelPeer listener;
	
	private org.eclipse.swt.widgets.Label peer;
	
	private Shell shell;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		MockPeerManager peerManager = new MockPeerManager();
		
		shell = new Shell(Display.getDefault());
		peerManager.setPeer(null, shell);
		
		component = new Label();
		listener = new SwtLabelPeer(peerManager);
		peer = (org.eclipse.swt.widgets.Label) listener.createPeer(component);
		
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
		assertEquals(org.eclipse.swt.widgets.Label.class, peer.getClass());
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
