/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/EnumToStringCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Tests {@code EnumToStringCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: EnumToStringCodecTest.java 75409 2010-07-27 09:58:20Z mark@IIZUKA.CO.UK $
 * @see EnumToStringCodec
 */
public class EnumToStringCodecTest
{
	// types ------------------------------------------------------------------
	
	private enum FakeEnum
	{
		APPLE,
		BANANA,
		CARROT;
	}

	// fields -----------------------------------------------------------------
	
	private EnumToStringCodec<FakeEnum> codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new EnumToStringCodec<FakeEnum>(FakeEnum.class);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("APPLE", codec.encode(FakeEnum.APPLE));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(FakeEnum.APPLE, codec.decode("APPLE"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithInvalidValue() throws DecoderException
	{
		codec.decode("invalid");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
