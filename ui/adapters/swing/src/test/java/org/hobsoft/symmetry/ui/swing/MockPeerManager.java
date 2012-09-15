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
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.AbstractPeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class MockPeerManager extends AbstractPeerManager
{
	// TODO: centralise
	
	// fields -----------------------------------------------------------------
	
	private final Map<Object, Object> peersByComponent;
	
	// constructors -----------------------------------------------------------
	
	public MockPeerManager()
	{
		peersByComponent = new HashMap<Object, Object>();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		return peersByComponent.get(component);
	}
	
	// AbstractPeerManager methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		return null;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		// no-op
	}
	
	// public methods ---------------------------------------------------------
	
	public void setPeer(Object component, Object peer)
	{
		peersByComponent.put(component, peer);
	}
}
