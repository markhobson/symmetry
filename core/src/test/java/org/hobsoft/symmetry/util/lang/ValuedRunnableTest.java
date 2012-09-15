/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/lang/ValuedRunnableTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ValuedRunnableTest.java 98789 2012-02-27 12:01:42Z mark@IIZUKA.CO.UK $
 */
public class ValuedRunnableTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void runnableValue()
	{
		final Object expected = new Object();
		ValuedRunnable runnable = new ValuedRunnable()
		{
			@Override
			public void run()
			{
				setValue(expected);
			}
		};
		runnable.run();
		Object actual = runnable.getValue();
		assertEquals(expected, actual);
	}
}
