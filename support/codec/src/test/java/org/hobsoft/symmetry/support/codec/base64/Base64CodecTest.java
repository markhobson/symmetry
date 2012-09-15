/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/base64/Base64CodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests <code>Base64Codec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64CodecTest.java 75547 2010-08-02 08:59:47Z mark@IIZUKA.CO.UK $
 * @see		Base64Codec
 */
public class Base64CodecTest
{
	// constants --------------------------------------------------------------
	
	private static final byte[] PLAINTEXT = new byte[] {
		72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100
	};
	
	private static final byte[] CIPHERTEXT = new byte[] {
		83, 71, 86, 115, 98, 71, 56, 103, 86, 50, 57, 121, 98, 71, 81, 61
	};

	// fields -----------------------------------------------------------------
	
	private Base64Codec codec;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		codec = new Base64Codec();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		byte[] actual = codec.encode(PLAINTEXT);
		
		assertTrue("Ciphertext", Arrays.equals(CIPHERTEXT, actual));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		byte[] actual = codec.decode(CIPHERTEXT);
		
		assertTrue("Plaintext", Arrays.equals(PLAINTEXT, actual));
	}

	@Test
	public void testDecodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
