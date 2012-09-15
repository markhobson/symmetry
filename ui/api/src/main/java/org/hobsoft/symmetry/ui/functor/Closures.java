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

import org.hobsoft.symmetry.ui.functor.HyperlinkableClosure.Method;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Closures
{
	// constants --------------------------------------------------------------
	
	private static final Closure<Object> NOP = new Closure<Object>()
	{
		@Override
		public void apply(Object input)
		{
			// no-op
		}
	};

	// constructors -----------------------------------------------------------
	
	private Closures()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> Closure<T> nop()
	{
		@SuppressWarnings("unchecked")
		Closure<T> nop = (Closure<T>) NOP;
		
		return nop;
	}
	
	public static <T, U> Closure<T> compose(final Function<T, U> function, final Closure<? super U> closure)
	{
		return new Closure<T>()
		{
			@Override
			public void apply(T input)
			{
				closure.apply(function.apply(input));
			}
		};
	}

	// TODO: remove when Hyperlinkable event listeners are supported
	public static <T, U> HyperlinkableClosure<T> compose(final Function<T, U> function,
		final HyperlinkableClosure<? super U> closure)
	{
		return new HyperlinkableClosure<T>()
		{
			@Override
			public void apply(T input)
			{
				closure.apply(function.apply(input));
			}
			
			@Override
			public String toHyperlink(T input)
			{
				return closure.toHyperlink(function.apply(input));
			}
			
			@Override
			public Method getMethod()
			{
				return closure.getMethod();
			}
			
			@Override
			public boolean isAsynchronous()
			{
				return closure.isAsynchronous();
			}
		};
	}
	
	public static <T> Closure<T> compose(Closure<T>... closures)
	{
		return (closures.length == 1) ? closures[0] : new CompositeClosure<T>(closures);
	}
	
	public static <T> HyperlinkableClosure<T> hyperlink(String hyperlink)
	{
		return new DefaultHyperlinkableClosure<T>(hyperlink, Method.GET, false);
	}
	
	public static <T> HyperlinkableClosure<T> postHyperlink(String hyperlink)
	{
		return new DefaultHyperlinkableClosure<T>(hyperlink, Method.POST, false);
	}
	
	public static <T> HyperlinkableClosure<T> asyncHyperlink(String hyperlink)
	{
		return new DefaultHyperlinkableClosure<T>(hyperlink, Method.GET, true);
	}
}
