/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/test/java/uk/co/iizuka/kozo/xml/DefaultIdEncoderTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.xml;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.codec.EncoderException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultIdEncoderTest.java 95597 2011-11-28 12:44:19Z mark@IIZUKA.CO.UK $
 */
public class DefaultIdEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultIdEncoder encoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		encoder = new DefaultIdEncoder();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeOnce() throws EncoderException
	{
		assertEquals("0", encoder.encode(new Object()));
	}
	
	@Test
	public void encodeTwice() throws EncoderException
	{
		encoder.encode(new Object());
		
		assertEquals("1", encoder.encode(new Object()));
	}
	
	@Test
	public void encodeWithSameObject() throws EncoderException
	{
		Object object = new Object();
		encoder.encode(object);
		
		assertEquals("0", encoder.encode(object));
	}
}
