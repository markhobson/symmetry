/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/StubFunction.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.functor.Function;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubFunction.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the output type
 */
public class StubFunction<T> implements Function<Object, T>
{
	// fields -----------------------------------------------------------------
	
	private final T output;
	
	// constructors -----------------------------------------------------------
	
	public StubFunction(T output)
	{
		this.output = output;
	}

	// Function methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T apply(Object input)
	{
		return output;
	}
}
