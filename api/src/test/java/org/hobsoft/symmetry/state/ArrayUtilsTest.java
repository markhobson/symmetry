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
package org.hobsoft.symmetry.state;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code ArrayUtils}.
 * 
 * @author Mark Hobson
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
