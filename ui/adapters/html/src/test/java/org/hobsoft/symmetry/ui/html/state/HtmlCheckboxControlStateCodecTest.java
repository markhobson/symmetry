/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/HtmlCheckboxControlStateCodecTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.state.NullStateCodec;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code HtmlCheckboxControlStateCodec}.
 * 
 * @author Mark Hobson
 * @see HtmlCheckboxControlStateCodec
 */
public class HtmlCheckboxControlStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Object bean;
	
	private PropertyDescriptor descriptor;
	
	private HtmlCheckboxControlStateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		bean = new FakeBean();
		descriptor = Properties.getDescriptor(FakeBean.class, "booleanPrimitive");
		
		stateCodec = new HtmlCheckboxControlStateCodec(new NullStateCodec(), descriptor);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encodePropertyValueWithTrue() throws StateException
	{
		assertEquals("on", encodePropertyValue(true));
	}
	
	@Test
	public void encodePropertyValueWithFalse() throws StateException
	{
		assertNull(encodePropertyValue(false));
	}
	
	@Test
	public void decodePropertyValueWithOn() throws StateException
	{
		assertEquals(true, decodePropertyValue("on"));
	}
	
	@Test
	public void decodePropertyValueWithNull() throws StateException
	{
		assertEquals(false, decodePropertyValue(null));
	}
	
	// private methods --------------------------------------------------------
	
	private String encodePropertyValue(Object value) throws StateException
	{
		return stateCodec.encodePropertyValue(new PropertyState(bean, descriptor, value));
	}
	
	private Object decodePropertyValue(String encodedValue) throws StateException
	{
		return stateCodec.decodePropertyValue(bean, descriptor, encodedValue);
	}
}
