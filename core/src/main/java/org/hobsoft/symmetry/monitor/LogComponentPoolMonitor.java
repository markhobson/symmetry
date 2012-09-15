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
