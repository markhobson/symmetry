/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/StringToCharStreamCodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code StringToBytesCodec}.
 * 
 * @author Mark Hobson
 * @see StringToCharStreamCodec
 */
public class StringToCharStreamCodecTest
{
	// constants --------------------------------------------------------------
	
	private static final String STRING = "Hello World";
	
	private static final char[] CHARS = new char[] {
		'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'
	};
	
	// fields -----------------------------------------------------------------
	
	private StringToCharStreamCodec codec;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		codec = new StringToCharStreamCodec();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		char[] actual = IOUtils.toCharArray(codec.encode(STRING));
		
		assertTrue(Arrays.equals(CHARS, actual));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(STRING, codec.decode(new CharArrayReader(CHARS)));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
