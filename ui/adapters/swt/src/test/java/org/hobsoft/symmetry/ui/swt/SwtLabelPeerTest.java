/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.hobsoft.symmetry.test.StubPeerManager;
import org.hobsoft.symmetry.ui.Label;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
		StubPeerManager peerManager = new StubPeerManager();
		
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
