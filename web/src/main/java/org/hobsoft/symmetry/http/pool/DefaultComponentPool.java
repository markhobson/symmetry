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

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the type of object managed by this pool
 */
public class DefaultComponentPool<T> implements ComponentPool<T>
{
	// constants --------------------------------------------------------------
	
	private static final int POOL_SIZE = 3;
	
	private static final int POOL_SPAWN_DELAY = 100;
	
	// fields -----------------------------------------------------------------
	
	private final GenericObjectPool<T> pool;
	
	// constructors -----------------------------------------------------------
	
	public DefaultComponentPool(PoolableObjectFactory<T> objectFactory)
	{
		pool = new GenericObjectPool<T>(objectFactory);
		
		pool.setMinIdle(POOL_SIZE);
		pool.setMaxActive(POOL_SIZE);
		pool.setNumTestsPerEvictionRun(0);
		pool.setMinEvictableIdleTimeMillis(0);
		pool.setTimeBetweenEvictionRunsMillis(POOL_SPAWN_DELAY);
	}
	
	// ComponentPool methods --------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T borrowComponent() throws ComponentPoolException
	{
		try
		{
			T component = pool.borrowObject();
			
			return component;
		}
		catch (Exception exception)
		{
			throw new ComponentPoolException("Cannot borrow component from pool", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidateComponent(T component) throws ComponentPoolException
	{
		try
		{
			pool.invalidateObject(component);
		}
		catch (Exception exception)
		{
			throw new ComponentPoolException("Cannot invalidate component in pool", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void returnComponent(T component) throws ComponentPoolException
	{
		try
		{
			pool.returnObject(component);
		}
		catch (Exception exception)
		{
			throw new ComponentPoolException("Cannot return component to pool", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws ComponentPoolException
	{
		try
		{
			pool.close();
		}
		catch (Exception exception)
		{
			throw new ComponentPoolException("Cannot close component pool", exception);
		}
	}
}
