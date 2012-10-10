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
package org.hobsoft.symmetry.ui.binding;

import org.hobsoft.entangle.Converter;
import org.hobsoft.symmetry.ui.functor.Function;

/**
 * Factory for adapting Kozo to converters.
 * 
 * @author Mark Hobson
 * @version $Id: KozoConverters.java 97383 2011-12-28 20:22:56Z mark@IIZUKA.CO.UK $
 */
public final class KozoConverters
{
	// constructors -----------------------------------------------------------
	
	private KozoConverters()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <S, T> Converter<S, T> forFunction(final Function<? super S, ? extends T> toFunction)
	{
		return forFunction(toFunction, null);
	}
	
	public static <S, T> Converter<S, T> forFunction(final Function<? super S, ? extends T> toFunction,
		final Function<? super T, ? extends S> fromFunction)
	{
		if (toFunction == null)
		{
			throw new NullPointerException("toFunction cannot be null");
		}
		
		return new Converter<S, T>()
		{
			@Override
			public T convert(S source)
			{
				return toFunction.apply(source);
			}
			
			@Override
			public S unconvert(T target)
			{
				if (fromFunction == null)
				{
					throw new UnsupportedOperationException();
				}
				
				return fromFunction.apply(target);
			}
		};
	}
}
