/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/monitor/NoOpComponentPoolMonitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.monitor;

import org.hobsoft.symmetry.pool.ComponentPoolMonitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class NoOpComponentPoolMonitor implements ComponentPoolMonitor
{
	// constants --------------------------------------------------------------
	
	public static final NoOpComponentPoolMonitor INSTANCE = new NoOpComponentPoolMonitor();
	
	// ComponentPoolMonitor methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInstantiated(Object component)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInstantiationException(Exception exception)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInvalidated(Object component)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentBorrowed(Object component)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentReturned(Object component)
	{
		// no-op
	}
}
