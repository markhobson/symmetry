/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/AbstractByteStreamEncoderTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code AbstractByteStreamEncoder}.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractByteStreamEncoderTest.java 75581 2010-08-02 18:51:27Z mark@IIZUKA.CO.UK $
 * @see AbstractByteStreamEncoder
 */
public class AbstractByteStreamEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private StubByteStreamEncoder encoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		encoder = new StubByteStreamEncoder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertEquals("a", IOUtils.toString(encoder.encode("a"), "UTF-8"));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(encoder.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException, UnsupportedEncodingException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		encoder.encodeTo("a", out);
		
		assertEquals("a", out.toString("UTF-8"));
	}
}
