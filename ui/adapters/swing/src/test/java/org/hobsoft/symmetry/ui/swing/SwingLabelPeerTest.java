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
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;

import org.hobsoft.symmetry.ui.Label;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
		StubPeerManager peerManager = new StubPeerManager();
		
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
