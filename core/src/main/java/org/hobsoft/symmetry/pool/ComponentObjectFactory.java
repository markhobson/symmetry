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
package org.hobsoft.symmetry.pool;

import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ComponentObjectFactory extends InstantiatingPoolableObjectFactory
{
	// fields -----------------------------------------------------------------
	
	private final PeerManager peerManager;
	
	// constructors -----------------------------------------------------------
	
	public ComponentObjectFactory(PeerManager peerManager, Class<?> componentClass)
	{
		super(componentClass);
		
		this.peerManager = peerManager;
	}
	
	// PoolableObjectFactory methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object makeObject() throws Exception
	{
		Object component = super.makeObject();
		
		peerManager.registerComponent(component);
		
		return component;
	}
	
	// public methods ---------------------------------------------------------
	
	public PeerManager getPeerManager()
	{
		return peerManager;
	}
}
