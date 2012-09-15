/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/DefaultComponentPool.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.hobsoft.symmetry.monitor.NoOpComponentPoolMonitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultComponentPool.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class DefaultComponentPool implements ComponentPool
{
	// constants --------------------------------------------------------------
	
	private static final int POOL_SIZE = 3;
	
	private static final int POOL_SPAWN_DELAY = 100;
	
	// fields -----------------------------------------------------------------
	
	private final ComponentPoolMonitor monitor;
	
	private final GenericObjectPool pool;
	
	// constructors -----------------------------------------------------------
	
	public DefaultComponentPool(PoolableObjectFactory objectFactory)
	{
		this(objectFactory, NoOpComponentPoolMonitor.INSTANCE);
	}
	
	public DefaultComponentPool(PoolableObjectFactory objectFactory, ComponentPoolMonitor monitor)
	{
		this.monitor = monitor;
		
		pool = new GenericObjectPool(objectFactory);
		
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
	public Object borrowComponent() throws ComponentPoolException
	{
		try
		{
			Object component = pool.borrowObject();
			
			monitor.componentBorrowed(component);
			
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
	public void invalidateComponent(Object component) throws ComponentPoolException
	{
		try
		{
			pool.invalidateObject(component);
			monitor.componentInvalidated(component);
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
	public void returnComponent(Object component) throws ComponentPoolException
	{
		try
		{
			pool.returnObject(component);
			monitor.componentReturned(component);
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
