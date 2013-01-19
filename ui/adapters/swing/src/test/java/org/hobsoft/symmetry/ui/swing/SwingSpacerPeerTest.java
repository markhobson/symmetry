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

import javax.swing.Box;

import org.hobsoft.symmetry.ui.Spacer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingSpacerPeerTest
{
	// fields -----------------------------------------------------------------
	
	private Spacer component;
	
	private SwingSpacerPeer listener;
	
	private Box.Filler peer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		StubPeerManager peerManager = new StubPeerManager();
		
		component = new Spacer();
		listener = new SwingSpacerPeer(peerManager);
		peer = (Box.Filler) listener.createPeer(component);
		
		peerManager.setPeer(component, peer);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPeer()
	{
		assertEquals(Box.Filler.class, peer.getClass());
	}
	
	@Test
	public void propertyChangeEventUnknown()
	{
		listener.propertyChange(new PropertyChangeEvent(component, "unknown", "", "test"));
	}
}
