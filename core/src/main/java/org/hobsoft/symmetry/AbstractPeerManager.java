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
package org.hobsoft.symmetry;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.bean.BeanUtils;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractPeerManager implements PeerManager, PropertyChangeListener
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, Object> peersByComponent;
	
	// constructors -----------------------------------------------------------
	
	public AbstractPeerManager()
	{
		peersByComponent = new HashMap<Object, Object>();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		BeanUtils.addPropertyChangeListener(component, this);
		
		Object peer = createPeer(component);
		peersByComponent.put(component, peer);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		return peersByComponent.get(component);
	}
	
	// public methods ---------------------------------------------------------
	
	public abstract Object createPeer(Object component);
}
