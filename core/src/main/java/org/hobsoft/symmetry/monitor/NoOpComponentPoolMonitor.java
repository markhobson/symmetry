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
