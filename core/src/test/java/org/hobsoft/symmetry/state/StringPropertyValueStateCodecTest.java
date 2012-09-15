/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/state/StringPropertyValueStateCodecTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hobsoft.symmetry.support.bean.Properties.getDescriptor;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code StringPropertyValueStateCodec}.
 * 
 * @author Mark Hobson
 * @see StringPropertyValueStateCodec
 */
public class StringPropertyValueStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private StringPropertyValueStateCodec stateCodec;
	
	private FakeBean bean;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		stateCodec = new StringPropertyValueStateCodec(new NullStateCodec());
		
		bean = new FakeBean();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encodePropertyValueWithBooleanPrimitive() throws StateException
	{
		assertEquals("true", stateCodec.encodePropertyValue(createPropertyState(bean, "booleanPrimitive", true)));
	}
	
	@Test
	public void encodePropertyValueWithIntegerPrimitive() throws StateException
	{
		assertEquals("1", stateCodec.encodePropertyValue(createPropertyState(bean, "integerPrimitive", 1)));
	}
	
	@Test
	public void encodePropertyValueWithBoolean() throws StateException
	{
		assertEquals("true", stateCodec.encodePropertyValue(createPropertyState(bean, "boolean", Boolean.TRUE)));
	}
	
	@Test
	public void encodePropertyValueWithInteger() throws StateException
	{
		assertEquals("1", stateCodec.encodePropertyValue(createPropertyState(bean, "integer", Integer.valueOf(1))));
	}
	
	@Test
	public void encodePropertyValueWithString() throws StateException
	{
		assertEquals("x", stateCodec.encodePropertyValue(createPropertyState(bean, "string", "x")));
	}
	
	@Test
	public void encodePropertyValueWithIntegerPrimitiveArray() throws StateException
	{
		String actual = stateCodec.encodePropertyValue(createPropertyState(bean, "integerPrimitiveArray",
			new int[] {1, 2}));
		
		assertEquals("2,1,2", actual);
	}
	
	@Test
	public void encodePropertyValueWithIntegerArray() throws StateException
	{
		String actual = stateCodec.encodePropertyValue(createPropertyState(bean, "integerArray", new Integer[] {1, 2}));
		
		assertEquals("2,1,2", actual);
	}
	
	@Test
	public void decodePropertyValueWithBooleanPrimitive() throws StateException
	{
		assertEquals(true, stateCodec.decodePropertyValue(bean, getDescriptor(bean, "booleanPrimitive"), "true"));
	}
	
	@Test
	public void decodePropertyValueWithIntegerPrimitive() throws StateException
	{
		assertEquals(1, stateCodec.decodePropertyValue(bean, getDescriptor(bean, "integerPrimitive"), "1"));
	}
	
	@Test
	public void decodePropertyValueWithBoolean() throws StateException
	{
		assertEquals(Boolean.TRUE, stateCodec.decodePropertyValue(bean, getDescriptor(bean, "boolean"), "true"));
	}
	
	@Test
	public void decodePropertyValueWithInteger() throws StateException
	{
		assertEquals(Integer.valueOf(1), stateCodec.decodePropertyValue(bean, getDescriptor(bean, "integer"), "1"));
	}
	
	@Test
	public void decodePropertyValueWithString() throws StateException
	{
		assertEquals("x", stateCodec.decodePropertyValue(bean, getDescriptor(bean, "string"), "x"));
	}
	
	@Test
	public void decodePropertyValueWithIntegerPrimitiveArray() throws StateException
	{
		Object actual = stateCodec.decodePropertyValue(bean, getDescriptor(bean, "integerPrimitiveArray"), "2,1,2");
		
		assertThat(actual, instanceOf(int[].class));
		assertArrayEquals(new int[] {1, 2}, (int[]) actual);
	}
	
	@Test
	public void decodePropertyValueWithIntegerArray() throws StateException
	{
		Object actual = stateCodec.decodePropertyValue(bean, getDescriptor(bean, "integerArray"), "2,1,2");
		
		assertThat(actual, instanceOf(Integer[].class));
		assertArrayEquals(new Integer[] {1, 2}, (Integer[]) actual);
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyState createPropertyState(Object bean, String propertyName, Object value)
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(bean, propertyName);
		
		return new PropertyState(bean, descriptor, value);
	}
}
