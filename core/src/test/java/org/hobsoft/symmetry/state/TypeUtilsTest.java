/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/state/TypeUtilsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
