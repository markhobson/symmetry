/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/base64/Base64AlphabetTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import static org.junit.Assert.assertEquals;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests <code>Base64Alphabet</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64AlphabetTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
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
