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

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class PooledPeerManagerProvider implements PeerManagerProvider
{
	// constants --------------------------------------------------------------
	
	private static final int POOL_SIZE = 3;
	
	private static final int POOL_SPAWN_DELAY = 100;
	
	// fields -----------------------------------------------------------------
	
	private final ObjectPool<PeerManager> pool;
	
	// constructors -----------------------------------------------------------
	
	public PooledPeerManagerProvider(Class<? extends PeerManager> peerManagerClass, Class<?> componentClass)
	{
		pool = createPool(peerManagerClass, componentClass);
	}
	
	// PeerManagerProvider methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PeerManager get() throws PeerManagerProviderException
	{
		try
		{
			return pool.borrowObject();
		}
		catch (Exception exception)
		{
			throw new PeerManagerProviderException("Cannot borrow peer manager from pool", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void release(PeerManager peerManager) throws PeerManagerProviderException
	{
		try
		{
			pool.returnObject(peerManager);
		}
		catch (Exception exception)
		{
			throw new PeerManagerProviderException("Cannot return peer manager to pool", exception);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static ObjectPool<PeerManager> createPool(Class<? extends PeerManager> peerManagerClass,
		Class<?> componentClass)
	{
		PoolableObjectFactory<PeerManager> objectFactory = new PeerManagerObjectFactory(peerManagerClass,
			componentClass);
		
		GenericObjectPool<PeerManager> pool = new GenericObjectPool<PeerManager>(objectFactory);
		
		pool.setMinIdle(POOL_SIZE);
		pool.setMaxActive(POOL_SIZE);
		pool.setNumTestsPerEvictionRun(0);
		pool.setMinEvictableIdleTimeMillis(0);
		pool.setTimeBetweenEvictionRunsMillis(POOL_SPAWN_DELAY);

		return pool;
	}
}
