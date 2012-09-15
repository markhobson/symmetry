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
package org.hobsoft.symmetry.ui.event;

import java.util.EventObject;

import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the event type
 */
public abstract class AbstractClosureEventListener<T extends EventObject> implements ClosureEventListener<T>
{
	// fields -----------------------------------------------------------------
	
	private final Closure<? super T> closure;
	
	// constructors -----------------------------------------------------------
	
	public AbstractClosureEventListener(Closure<? super T> closure)
	{
		this.closure = closure;
	}
	
	// ClosureEventListener methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Closure<? super T> getClosure()
	{
		return closure;
	}
}
