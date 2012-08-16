/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/HtmlTextControlStateCodecTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.state;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.bean.Properties;
import uk.co.iizuka.kozo.state.NullStateCodec;
import uk.co.iizuka.kozo.state.PropertyState;
import uk.co.iizuka.kozo.state.StateException;

/**
 * Tests {@code HtmlTextControlStateCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextControlStateCodecTest.java 94233 2011-10-10 16:51:01Z mark@IIZUKA.CO.UK $
 * @see HtmlTextControlStateCodec
 */
public class HtmlTextControlStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Object bean;
	
	private PropertyDescriptor descriptor;
	
	private HtmlTextControlStateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		bean = new FakeBean();
		descriptor = Properties.getDescriptor(FakeBean.class, "string");
		
		stateCodec = new HtmlTextControlStateCodec(new NullStateCodec(), descriptor);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encodePropertyValueWithString() throws StateException
	{
		assertEquals("a", encodePropertyValue("a"));
	}
	
	@Test
	public void encodePropertyValueWithEmptyString() throws StateException
	{
		assertEquals("", encodePropertyValue(""));
	}
	
	@Test
	public void decodePropertyValueWithString() throws StateException
	{
		assertEquals("a", decodePropertyValue("a"));
	}
	
	@Test
	public void decodePropertyValueWithEmptyString() throws StateException
	{
		assertEquals("", decodePropertyValue(""));
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
