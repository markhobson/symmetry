/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/ObjectToStringEncoderTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.iizuka.common.codec.EncoderException;

/**
 * Tests {@code ObjectToStringEncoder}.
 * 
 * @author Mark Hobson
 * @version $Id: ObjectToStringEncoderTest.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @see ObjectToStringEncoder
 */
public class ObjectToStringEncoderTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("123", new ObjectToStringEncoder<Integer>().encode(123));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", new ObjectToStringEncoder<Integer>().encode(null));
	}
}
