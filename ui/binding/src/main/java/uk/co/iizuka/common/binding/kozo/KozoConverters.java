/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/KozoConverters.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Converter;

import uk.co.iizuka.kozo.ui.functor.Function;

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
