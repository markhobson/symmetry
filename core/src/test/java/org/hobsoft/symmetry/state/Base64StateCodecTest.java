/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/state/Base64StateCodecTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.JdkBeanIntrospector;
import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Base64StateCodec}.
 * 
 * @author Mark Hobson
 * @see Base64StateCodec
 */
public class Base64StateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Base64StateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: use stub component codec
		stateCodec = new Base64StateCodec(null, new JdkBeanIntrospector(), null);
	}

	// tests ------------------------------------------------------------------

	@Test
	public void decodePropertyValueWithBooleanPrimitiveAndNull() throws StateException
	{
		assertEquals(false, decodePropertyValue("booleanPrimitive", null));
	}
	
	@Test
	public void decodePropertyValueWithIntegerPrimitiveAndNull() throws StateException
	{
		assertEquals(-1, decodePropertyValue("integerPrimitive", null));
	}
	
	// private methods --------------------------------------------------------
	
	private Object decodePropertyValue(String propertyName, String encodedPropertyValue) throws StateException
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(FakeBean.class, propertyName);
		
		return stateCodec.decodePropertyValue(new FakeBean(), descriptor, encodedPropertyValue);
	}
}
