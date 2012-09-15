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
package org.hobsoft.symmetry.ui.functor;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the input type
 */
public final class CompositeClosure<T> implements Closure<T>
{
	// fields -----------------------------------------------------------------
	
	private final Collection<Closure<T>> closures;
	
	// constructors -----------------------------------------------------------
	
	public CompositeClosure(Closure<T>... closures)
	{
		this(Arrays.asList(closures));
	}
	
	public CompositeClosure(Collection<Closure<T>> closures)
	{
		this.closures = closures;
	}
	
	// Closure methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(T input)
	{
		for (Closure<T> closure : closures)
		{
			closure.apply(input);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public Collection<Closure<T>> getClosures()
	{
		return closures;
	}
}
