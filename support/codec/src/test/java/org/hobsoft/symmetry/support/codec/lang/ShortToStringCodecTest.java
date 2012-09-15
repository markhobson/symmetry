/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/ShortToStringCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code ShortToStringCodec}.
 * 
 * @author Mark Hobson
 * @see ShortToStringCodec
 */
public class ShortToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private ShortToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new ShortToStringCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("123", codec.encode((short) 123));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals((short) 123, (Object) codec.decode("123"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithInteger() throws DecoderException
	{
		codec.decode("123456");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
