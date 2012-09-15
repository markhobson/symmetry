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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code TypeUtils}.
 * 
 * @author Mark Hobson
 * @see TypeUtils
 */
public class TypeUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void defaultValueWithBooleanPrimitive()
	{
		assertEquals(false, TypeUtils.defaultValue(Boolean.TYPE));
	}
	
	@Test
	public void defaultValueWithBytePrimitive()
	{
		assertEquals((byte) -1, TypeUtils.defaultValue(Byte.TYPE));
	}
	
	@Test
	public void defaultValueWithShortPrimitive()
	{
		assertEquals((short) -1, TypeUtils.defaultValue(Short.TYPE));
	}
	
	@Test
	public void defaultValueWithIntPrimitive()
	{
		assertEquals(-1, TypeUtils.defaultValue(Integer.TYPE));
	}
	
	@Test
	public void defaultValueWithLongPrimitive()
	{
		assertEquals(-1L, TypeUtils.defaultValue(Long.TYPE));
	}
	
	@Test
	public void defaultValueWithCharPrimitive()
	{
		assertEquals('\0', TypeUtils.defaultValue(Character.TYPE));
	}
	
	@Test
	public void defaultValueWithFloatPrimitive()
	{
		assertEquals(-1f, TypeUtils.defaultValue(Float.TYPE));
	}
	
	@Test
	public void defaultValueWithDoublePrimitive()
	{
		assertEquals(-1.0, TypeUtils.defaultValue(Double.TYPE));
	}
	
	@Test
	public void defaultValueWithVoidPrimitive()
	{
		assertNull(TypeUtils.defaultValue(Void.TYPE));
	}
	
	@Test
	public void defaultValueWithBooleanReference()
	{
		assertNull(TypeUtils.defaultValue(Boolean.class));
	}
	
	@Test
	public void defaultValueWithByteReference()
	{
		assertNull(TypeUtils.defaultValue(Byte.class));
	}
	
	@Test
	public void defaultValueWithShortReference()
	{
		assertNull(TypeUtils.defaultValue(Short.class));
	}
	
	@Test
	public void defaultValueWithIntegerReference()
	{
		assertNull(TypeUtils.defaultValue(Integer.class));
	}
	
	@Test
	public void defaultValueWithLongReference()
	{
		assertNull(TypeUtils.defaultValue(Long.class));
	}
	
	@Test
	public void defaultValueWithCharacterReference()
	{
		assertNull(TypeUtils.defaultValue(Character.class));
	}
	
	@Test
	public void defaultValueWithFloatReference()
	{
		assertNull(TypeUtils.defaultValue(Float.class));
	}
	
	@Test
	public void defaultValueWithDoubleReference()
	{
		assertNull(TypeUtils.defaultValue(Double.class));
	}
	
	@Test
	public void defaultValueWithVoidReference()
	{
		assertNull(TypeUtils.defaultValue(Void.class));
	}
	
	@Test
	public void defaultValueWithObjectReference()
	{
		assertNull(TypeUtils.defaultValue(Object.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void defaultValueWithNull()
	{
		TypeUtils.defaultValue(null);
	}
}
