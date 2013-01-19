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

import javax.swing.JButton;

import org.hobsoft.symmetry.test.StubPeerManager;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

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
		StubPeerManager peerManager = new StubPeerManager();
		
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
