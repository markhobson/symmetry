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
package org.hobsoft.symmetry.http.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
class PeerManagerObjectFactory extends BasePoolableObjectFactory<PeerManager>
{
	// fields -----------------------------------------------------------------
	
	private final Class<? extends PeerManager> peerManagerClass;
	
	private final Class<?> componentClass;
	
	// constructors -----------------------------------------------------------
	
	public PeerManagerObjectFactory(Class<? extends PeerManager> peerManagerClass, Class<?> componentClass)
	{
		this.peerManagerClass = peerManagerClass;
		this.componentClass = componentClass;
	}
	
	// PoolableObjectFactory methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PeerManager makeObject() throws Exception
	{
		PeerManager peerManager = peerManagerClass.newInstance();
		
		Object component = componentClass.newInstance();
		peerManager.registerComponent(component);
		
		return peerManager;
	}
}
