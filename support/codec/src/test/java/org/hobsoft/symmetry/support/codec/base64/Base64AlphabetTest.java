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
package org.hobsoft.symmetry.support.codec.base64;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>Base64Alphabet</code>.
 * 
 * @author	Mark Hobson
 * @see		Base64Alphabet
 */
public class Base64AlphabetTest
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_BASE64_CHARS =
		"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	// fields -----------------------------------------------------------------
	
	private Base64Alphabet alphabet;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		alphabet = new Base64Alphabet();
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void createWithNullAlphabet()
	{
		try
		{
			new Base64Alphabet(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("alphabet cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createWithInvalidLengthAlphabet()
	{
		try
		{
			new Base64Alphabet("ABC");
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("alphabet must contain 64 bytes", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createWithInvalidCharAlphabet()
	{
		try
		{
			new Base64Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+\u0100");
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("alphabet must only contain 8-bit characters", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createWithDuplicateCharAlphabet()
	{
		try
		{
			new Base64Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+A");
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("alphabet must not contain duplicate bytes", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createWithPadCharAlphabet()
	{
		try
		{
			new Base64Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+=", '=');
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("alphabet must not contain pad byte", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		for (byte b = 0; b < 64; b++)
		{
			byte actual = alphabet.encode(b);
			byte expected = (byte) DEFAULT_BASE64_CHARS.charAt(b);
			
			assertEquals("Byte " + b, expected, actual);
		}
	}
	
	@Test
	public void decode() throws DecoderException
	{
		for (int i = 0; i < 64; i++)
		{
			byte b = (byte) DEFAULT_BASE64_CHARS.charAt(i);
			
			byte actual = alphabet.decode(b);
			byte expected = (byte) i;
			
			assertEquals("Character " + b, expected, actual);
		}
	}
	
	@Test
	public void getPadByte()
	{
		Base64Alphabet alphabet = new Base64Alphabet(Base64Alphabet.DEFAULT_ALPHABET, '@');
		
		assertEquals("Pad byte", (byte) '@', alphabet.getPadByte());
	}
}
