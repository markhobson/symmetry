/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/monitor/LogComponentPoolMonitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.monitor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hobsoft.symmetry.pool.ComponentPoolMonitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LogComponentPoolMonitor implements ComponentPoolMonitor
{
	// constants --------------------------------------------------------------
	
	public static final LogComponentPoolMonitor INSTANCE = new LogComponentPoolMonitor();
	
	private static final Logger LOG = Logger.getLogger(LogComponentPoolMonitor.class.getName());
	
	// ComponentPoolMonitor methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInstantiated(Object component)
	{
		LOG.log(Level.FINE, "Component instantiated {0}", component);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInstantiationException(Exception exception)
	{
		LOG.log(Level.WARNING, "Cannot instantate component", exception);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentInvalidated(Object component)
	{
		LOG.log(Level.FINE, "Component invalidated {0}", component);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentBorrowed(Object component)
	{
		LOG.log(Level.FINE, "Component borrowed {0}", component);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void componentReturned(Object component)
	{
		LOG.log(Level.FINE, "Component returned {0}", component);
	}
}
