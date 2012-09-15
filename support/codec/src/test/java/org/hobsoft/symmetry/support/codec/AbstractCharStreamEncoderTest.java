/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/AbstractCharStreamEncoderTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code AbstractCharStreamEncoder}.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractCharStreamEncoderTest.java 75581 2010-08-02 18:51:27Z mark@IIZUKA.CO.UK $
 * @see AbstractCharStreamEncoder
 */
public class AbstractCharStreamEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private StubCharStreamEncoder encoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		encoder = new StubCharStreamEncoder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertEquals("a", IOUtils.toString(encoder.encode("a")));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(encoder.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		StringWriter writer = new StringWriter();
		
		encoder.encodeTo("a", writer);
		
		assertEquals("a", writer.toString());
	}
}
