/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/ObjectToStringEncoderTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ObjectToStringEncoder}.
 * 
 * @author Mark Hobson
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
