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
package org.hobsoft.symmetry.util.lang;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ObjectUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void equalsWithNullAndNull()
	{
		assertTrue(ObjectUtils.equals(null, null));
	}
	
	@Test
	public void equalsWithNullAndObject()
	{
		assertFalse(ObjectUtils.equals(null, new Object()));
	}
	
	@Test
	public void equalsWithObjectAndNull()
	{
		assertFalse(ObjectUtils.equals(new Object(), null));
	}
	
	@Test
	public void equalsWithEqualObjects()
	{
		assertTrue(ObjectUtils.equals("x", "x"));
	}
	
	@Test
	public void equalsWithEqualObjectArrays()
	{
		assertTrue(ObjectUtils.equals(new Object[0], new Object[0]));
	}
	
	@Test
	public void equalsWithEqualBooleanArrays()
	{
		assertTrue(ObjectUtils.equals(new boolean[0], new boolean[0]));
	}
	
	@Test
	public void equalsWithEqualByteArrays()
	{
		assertTrue(ObjectUtils.equals(new byte[0], new byte[0]));
	}
	
	@Test
	public void equalsWithEqualCharArrays()
	{
		assertTrue(ObjectUtils.equals(new char[0], new char[0]));
	}
	
	@Test
	public void equalsWithEqualDoubleArrays()
	{
		assertTrue(ObjectUtils.equals(new double[0], new double[0]));
	}
	
	@Test
	public void equalsWithEqualFloatArrays()
	{
		assertTrue(ObjectUtils.equals(new float[0], new float[0]));
	}
	
	@Test
	public void equalsWithEqualIntArrays()
	{
		assertTrue(ObjectUtils.equals(new int[0], new int[0]));
	}
	
	@Test
	public void equalsWithEqualLongArrays()
	{
		assertTrue(ObjectUtils.equals(new long[0], new long[0]));
	}
	
	@Test
	public void equalsWithEqualShortArrays()
	{
		assertTrue(ObjectUtils.equals(new short[0], new short[0]));
	}
	
	@Test
	public void toStringWithNull()
	{
		assertEquals("null", ObjectUtils.toString(null));
	}
	
	@Test
	public void toStringWithObject()
	{
		assertEquals("x", ObjectUtils.toString("x"));
	}
	
	@Test
	public void toStringWithObjectArray()
	{
		assertEquals("[x, y]", ObjectUtils.toString(new Object[] {"x", "y"}));
	}
	
	@Test
	public void toStringWithBooleanArray()
	{
		assertEquals("[false, true]", ObjectUtils.toString(new boolean[] {false, true}));
	}
	
	@Test
	public void toStringWithByteArray()
	{
		assertEquals("[0, 1]", ObjectUtils.toString(new byte[] {0, 1}));
	}
	
	@Test
	public void toStringWithCharArray()
	{
		assertEquals("[x, y]", ObjectUtils.toString(new char[] {'x', 'y'}));
	}
	
	@Test
	public void toStringWithDoubleArray()
	{
		assertEquals("[0.0, 1.0]", ObjectUtils.toString(new double[] {0.0, 1.0}));
	}
	
	@Test
	public void toStringWithFloatArray()
	{
		assertEquals("[0.0, 1.0]", ObjectUtils.toString(new float[] {0.0f, 1.0f}));
	}
	
	@Test
	public void toStringWithIntArray()
	{
		assertEquals("[0, 1]", ObjectUtils.toString(new int[] {0, 1}));
	}
	
	@Test
	public void toStringWithLongArray()
	{
		assertEquals("[0, 1]", ObjectUtils.toString(new long[] {0, 1}));
	}
	
	@Test
	public void toStringWithShortArray()
	{
		assertEquals("[0, 1]", ObjectUtils.toString(new short[] {0, 1}));
	}
}
