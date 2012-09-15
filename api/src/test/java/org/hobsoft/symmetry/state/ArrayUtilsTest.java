/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/state/ArrayUtilsTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests {@code ArrayUtils}.
 * 
 * @author Mark Hobson
 * @version $Id: ArrayUtilsTest.java 98228 2012-02-02 11:29:46Z mark@IIZUKA.CO.UK $
 * @see ArrayUtils
 */
public class ArrayUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void hashCodeWithByteArray()
	{
		assertEquals(Arrays.hashCode(new byte[] {1, 2}), ArrayUtils.hashCode(new byte[] {1, 2}));
	}

	@Test
	public void hashCodeWithShortArray()
	{
		assertEquals(Arrays.hashCode(new short[] {1, 2}), ArrayUtils.hashCode(new short[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithIntArray()
	{
		assertEquals(Arrays.hashCode(new int[] {1, 2}), ArrayUtils.hashCode(new int[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithLongArray()
	{
		assertEquals(Arrays.hashCode(new long[] {1, 2}), ArrayUtils.hashCode(new long[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithCharArray()
	{
		assertEquals(Arrays.hashCode(new char[] {1, 2}), ArrayUtils.hashCode(new char[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithFloatArray()
	{
		assertEquals(Arrays.hashCode(new float[] {1, 2}), ArrayUtils.hashCode(new float[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithDoubleArray()
	{
		assertEquals(Arrays.hashCode(new double[] {1, 2}), ArrayUtils.hashCode(new double[] {1, 2}));
	}
	
	@Test
	public void hashCodeWithBooleanArray()
	{
		assertEquals(Arrays.hashCode(new boolean[] {true, false}), ArrayUtils.hashCode(new boolean[] {true, false}));
	}
	
	@Test
	public void hashCodeWithObjectArray()
	{
		assertEquals(Arrays.hashCode(new Object[] {"x", "y"}), ArrayUtils.hashCode(new Object[] {"x", "y"}));
	}

	@Test(expected = IllegalArgumentException.class)
	public void hashCodeWithObject()
	{
		ArrayUtils.hashCode(new Object());
	}
	
	@Test
	public void equalsWithByteArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new byte[] {1, 2}, new byte[] {1, 2}));
	}
	
	@Test
	public void equalsWithByteArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new byte[] {1, 2}, new byte[] {1, 3}));
	}
	
	@Test
	public void equalsWithShortArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new short[] {1, 2}, new short[] {1, 2}));
	}
	
	@Test
	public void equalsWithShortArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new short[] {1, 2}, new short[] {1, 3}));
	}
	
	@Test
	public void equalsWithIntArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new int[] {1, 2}, new int[] {1, 2}));
	}
	
	@Test
	public void equalsWithIntArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new int[] {1, 2}, new int[] {1, 3}));
	}
	
	@Test
	public void equalsWithLongArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new long[] {1, 2}, new long[] {1, 2}));
	}
	
	@Test
	public void equalsWithLongArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new long[] {1, 2}, new long[] {1, 3}));
	}

	@Test
	public void equalsWithCharArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new char[] {1, 2}, new char[] {1, 2}));
	}
	
	@Test
	public void equalsWithCharArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new char[] {1, 2}, new char[] {1, 3}));
	}
	
	@Test
	public void equalsWithFloatArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new float[] {1, 2}, new float[] {1, 2}));
	}
	
	@Test
	public void equalsWithFloatArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new float[] {1, 2}, new float[] {1, 3}));
	}
	
	@Test
	public void equalsWithDoubleArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new double[] {1, 2}, new double[] {1, 2}));
	}
	
	@Test
	public void equalsWithDoubleArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new double[] {1, 2}, new double[] {1, 3}));
	}
	
	@Test
	public void equalsWithBooleanArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new boolean[] {true, false}, new boolean[] {true, false}));
	}
	
	@Test
	public void equalsWithBooleanArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new boolean[] {true, false}, new boolean[] {true, true}));
	}
	
	@Test
	public void equalsWithObjectArrayWhenEqual()
	{
		assertTrue(ArrayUtils.equals(new Object[] {"x", "y"}, new Object[] {"x", "y"}));
	}
	
	@Test
	public void equalsWithObjectArrayWhenUnequal()
	{
		assertFalse(ArrayUtils.equals(new Object[] {"x", "y"}, new Object[] {"x", "z"}));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void equalsWithDifferentArrayTypes()
	{
		ArrayUtils.equals(new int[] {1, 2}, new long[] {1, 2});
	}
}
