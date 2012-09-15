/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/BooleanToStringCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code BooleanToStringCodec}.
 * 
 * @author Mark Hobson
 * @see BooleanToStringCodec
 */
public class BooleanToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private BooleanToStringCodec lenientCodec;
	
	private BooleanToStringCodec strictCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		lenientCodec = new BooleanToStringCodec(true);
		strictCodec = new BooleanToStringCodec(false);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeWithTrue() throws EncoderException
	{
		assertEquals("true", lenientCodec.encode(true));
		assertEquals("true", strictCodec.encode(true));
	}
	
	@Test
	public void encodeWithFalse() throws EncoderException
	{
		assertEquals("false", lenientCodec.encode(false));
		assertEquals("false", strictCodec.encode(false));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", lenientCodec.encode(null));
		assertEquals("null", strictCodec.encode(null));
	}
	
	@Test
	public void decodeWithTrue() throws DecoderException
	{
		assertTrue(lenientCodec.decode("true"));
		assertTrue(strictCodec.decode("true"));
	}
	
	@Test
	public void decodeWithTrueMixedCase() throws DecoderException
	{
		assertTrue(lenientCodec.decode("TrUe"));
		assertTrue(strictCodec.decode("TrUe"));
	}
	
	@Test
	public void decodeWithFalse() throws DecoderException
	{
		assertFalse(lenientCodec.decode("false"));
		assertFalse(strictCodec.decode("false"));
	}
	
	@Test
	public void decodeWithFalseMixedCase() throws DecoderException
	{
		assertFalse(lenientCodec.decode("FaLsE"));
		assertFalse(strictCodec.decode("FaLsE"));
	}
	
	@Test
	public void decodeWithOtherWhenLenient() throws DecoderException
	{
		assertFalse(lenientCodec.decode("not a boolean"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithOtherWhenStrict() throws DecoderException
	{
		strictCodec.decode("not a boolean");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(lenientCodec.decode(null));
		assertNull(strictCodec.decode(null));
	}
}
