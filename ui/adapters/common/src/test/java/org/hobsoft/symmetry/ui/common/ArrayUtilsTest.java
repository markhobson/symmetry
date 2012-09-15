/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/test/java/uk/co/iizuka/kozo/ui/common/ArrayUtilsTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Tests {@code ArrayUtils}.
 * 
 * @author Mark Hobson
 * @version $Id: ArrayUtilsTest.java 98237 2012-02-02 16:27:34Z mark@IIZUKA.CO.UK $
 * @see ArrayUtils
 */
public class ArrayUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void mergeWithReferenceArrays()
	{
		Integer[] actual = (Integer[]) ArrayUtils.merge(Integer.class, new Integer[] {1, 2}, new Integer[] {3, 4});
		
		assertArrayEquals(new Integer[] {1, 2, 3, 4}, actual);
	}
	
	@Test
	public void mergeWithPrimitiveArrays()
	{
		int[] actual = (int[]) ArrayUtils.merge(int.class, new int[] {1, 2}, new int[] {3, 4});
		
		assertArrayEquals(new int[] {1, 2, 3, 4}, actual);
	}
	
	@Test
	public void mergeWithNullArray()
	{
		int[] actual = (int[]) ArrayUtils.merge(int.class, (Object) null);
		
		assertArrayEquals(new int[0], actual);
	}
}
