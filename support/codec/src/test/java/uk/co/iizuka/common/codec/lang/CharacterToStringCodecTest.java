/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/CharacterToStringCodecTest.java $
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
 * Tests {@code CharacterToStringCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: CharacterToStringCodecTest.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @see CharacterToStringCodec
 */
public class CharacterToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private CharacterToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new CharacterToStringCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("a", codec.encode('a'));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals('a', (Object) codec.decode("a"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithEmptyString() throws DecoderException
	{
		codec.decode("");
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithMultipleCharacters() throws DecoderException
	{
		codec.decode("ab");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
