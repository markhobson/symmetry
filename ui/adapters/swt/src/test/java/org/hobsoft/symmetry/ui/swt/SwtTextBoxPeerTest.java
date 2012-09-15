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

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.hobsoft.symmetry.ui.TextBox;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
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
