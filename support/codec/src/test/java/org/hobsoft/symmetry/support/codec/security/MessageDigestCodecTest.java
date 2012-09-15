/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.codec.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>MessageDigestCodec</code>.
 * 
 * @author	Mark Hobson
 * @see		MessageDigestCodec
 */
public class MessageDigestCodecTest
{
	// constants --------------------------------------------------------------
	
	private static final String DIGEST_ALGORITHM = "MD5";
	
	private static final byte[] MESSAGE = new byte[] {
		72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100
	};

	private static final byte[] DIGEST = new byte[] {
		-79, 10, -115, -79, 100, -32, 117, 65, 5, -73, -87, -101, -25, 46, 63, -27
	};

	private static final byte[] INVALID_DIGEST = new byte[] {
		1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
	};

	// fields -----------------------------------------------------------------
	
	private MessageDigestCodec codec;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp() throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
		
		codec = new MessageDigestCodec(digest);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		byte[] actual = codec.encode(MESSAGE);
		byte[] expected = concat(MESSAGE, DIGEST);
		
		assertTrue(Arrays.equals(expected, actual));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		byte[] input = concat(MESSAGE, DIGEST);
		byte[] actual = codec.decode(input);
		
		assertTrue(Arrays.equals(MESSAGE, actual));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}

	@Test(expected = DecoderException.class)
	public void decodeWithMismatchedDigest() throws DecoderException
	{
		byte[] input = concat(MESSAGE, INVALID_DIGEST);
		
		try
		{
			codec.decode(input);
		}
		catch (DecoderException exception)
		{
			assertEquals("Mismatched digest", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithMissingDigest() throws DecoderException
	{
		try
		{
			codec.decode(MESSAGE);
		}
		catch (DecoderException exception)
		{
			assertEquals("Insufficient bytes for digest", exception.getMessage());
			
			throw exception;
		}
	}
	
	// private methods --------------------------------------------------------
	
	private byte[] concat(byte[] a, byte[] b)
	{
		byte[] c = new byte[a.length + b.length];
		
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		
		return c;
	}
}
