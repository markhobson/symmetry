/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/IntegerToStringCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code IntegerToStringCodec}.
 * 
 * @author Mark Hobson
 * @see IntegerToStringCodec
 */
public class IntegerToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private IntegerToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new IntegerToStringCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("123", codec.encode(123));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(123, (Object) codec.decode("123"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithLong() throws DecoderException
	{
		codec.decode("12345678901");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
