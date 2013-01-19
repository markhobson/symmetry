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

import org.hobsoft.symmetry.PeerManager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StubPeerManager implements PeerManager
{
	// TODO: centralise
	
	// fields -----------------------------------------------------------------
	
	private final BiMap<Object, Object> peersByComponent;
	
	// constructors -----------------------------------------------------------
	
	public StubPeerManager()
	{
		peersByComponent = HashBiMap.create();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		return peersByComponent.get(component);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getComponent(Object peer)
	{
		return peersByComponent.inverse().get(peer);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setPeer(Object component, Object peer)
	{
		peersByComponent.put(component, peer);
	}
}
