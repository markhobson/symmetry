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

import java.util.Arrays;

import org.apache.commons.lang.Validate;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Defines an alphabet for use when encoding and decoding Base64.
 * 
 * @author	Mark Hobson
 */
public class Base64Alphabet
{
	// constants --------------------------------------------------------------
	
	/**
	 * The upper case alpha characters.
	 */
	public static final String UPPER_ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * The lower case alpha characters.
	 */
	public static final String LOWER_ALPHAS = UPPER_ALPHAS.toLowerCase();
	
	/**
	 * The numeric characters.
	 */
	public static final String NUMERICS = "0123456789";
	
	/**
	 * The alphanumeric characters.
	 */
	public static final String ALPHANUMERICS = UPPER_ALPHAS + LOWER_ALPHAS + NUMERICS;
	
	/**
	 * The default Base64 alphabet.
	 */
	public static final String DEFAULT_ALPHABET = ALPHANUMERICS + "+/";
	
	/**
	 * Custom Base64 alphabet that is valid within URLs.
	 */
	public static final String URL_ALPHABET = ALPHANUMERICS + "*!";
	
	/**
	 * The default character to use when padding encoded Base64.
	 */
	public static final char DEFAULT_PAD_CHAR = '=';
	
	/**
	 * A shared instance of the default Base64 alphabet.
	 */
	public static final Base64Alphabet DEFAULT = new Base64Alphabet();
	
	/**
	 * A shared instance of the URL Base64 alphabet.
	 */
	public static final Base64Alphabet URL = new Base64Alphabet(URL_ALPHABET);
	
	/**
	 * The base of this alphabet.
	 */
	private static final int BASE = 64;
	
	// fields -----------------------------------------------------------------
	
	/**
	 * Map of bytes to their encoded values, such that byte <code>b</code> is encoded to <code>alphabet[b]</code>.
	 */
	private final byte[] alphabet;
	
	/**
	 * Map of encoded bytes to their original values, such that encoded byte <code>b</code> is decoded to
	 * <code>inverseAlphabet[b]</code>.  A value of <code>-1</code> denotes that this encoded byte has no inverse
	 * mapping.
	 */
	private final int[] inverseAlphabet;
	
	/**
	 * The byte to use when padding encoded Base64.
	 */
	private final byte padByte;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a Base64 alphabet for the default characters and default pad character.
	 */
	public Base64Alphabet()
	{
		this(DEFAULT_ALPHABET);
	}
	
	/**
	 * Creates a Base64 alphabet for the specified characters that uses the default pad character.
	 *
	 * @param	alphabet
	 * 				a string of 64 characters to be used as the Base64 alphabet.  This string must only contain 8-bit
	 * 				characters; not contain duplicate characters; nor may it contain the specified pad character.
	 * @throws	IllegalArgumentException
	 * 				if the parameter constraints are violated
	 */
	public Base64Alphabet(String alphabet)
	{
		this(alphabet, DEFAULT_PAD_CHAR);
	}
	
	/**
	 * Creates a Base64 alphabet for the specified characters that uses the specified pad character.
	 *
	 * @param	alphabet
	 * 				a string of 64 characters to be used as the Base64 alphabet.  This string must only contain 8-bit
	 * 				characters; not contain duplicate characters; nor may it contain the specified pad character.
	 * @param	padChar
	 * 				the character to use when padding encoded Base64
	 * @throws	IllegalArgumentException
	 * 				if the parameter constraints are violated
	 */
	public Base64Alphabet(String alphabet, char padChar)
	{
		this(toAlphabetBytes(alphabet), toPadByte(padChar));
	}
	
	/**
	 * Creates a Base64 alphabet for the specified bytes that uses the specified pad byte.
	 *
	 * @param	alphabet
	 * 				an array that maps bytes to their encoded values, so that byte <code>b</code> is encoded to
	 * 				<code>alphabet[b]</code>.  This array must not contain duplicate values, nor may it contain the
	 * 				specified pad byte.
	 * @param	padByte
	 * 				the byte to use when padding encoded Base64
	 * @throws	IllegalArgumentException
	 * 				if the parameter constraints are violated
	 */
	public Base64Alphabet(byte[] alphabet, byte padByte)
	{
		Validate.notNull(alphabet, "alphabet cannot be null");
		Validate.isTrue(alphabet.length == BASE, "alphabet must contain " + BASE + " bytes");
		Validate.isTrue(containsUniqueBytes(alphabet), "alphabet must not contain duplicate bytes");
		Validate.isTrue(!contains(alphabet, padByte), "alphabet must not contain pad byte");
		
		this.alphabet = alphabet.clone();
		
		inverseAlphabet = createInverseAlphabet(alphabet);
		
		this.padByte = padByte;
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Base64 encodes the specified byte using this alphabet.
	 * 
	 * @param	b
	 * 				the byte to encode, such that 0 <= <code>b</code> < 64
	 * @return	the Base64 encoded byte
	 * @throws	EncoderException
	 * 				if the specified byte was out of range
	 */
	public byte encode(byte b) throws EncoderException
	{
		// validate byte
		if (b < 0 || b >= alphabet.length)
		{
			throw new EncoderException("Invalid Base64 byte: " + b);
		}
		
		return alphabet[b];
	}
	
	/**
	 * Base64 decodes the specified byte using this alphabet.
	 *
	 * @param	b
	 * 				the Base64 byte to decode
	 * @return	the decoded byte <code>r</code>, such that 0 <= <code>r</code> < 64
	 * @throws	DecoderException
	 * 				if the specified byte was an invalid Base64 byte
	 */
	public byte decode(byte b) throws DecoderException
	{
		int r = inverseAlphabet[b];
		
		// validate byte
		if (r == -1)
		{
			throw new DecoderException("Invalid Base64 byte: " + b);
		}
		
		return (byte) r;
	}
	
	/**
	 * Gets the byte to use when padding encoded Base64 with this alphabet.
	 * 
	 * @return	the pad byte
	 */
	public byte getPadByte()
	{
		return padByte;
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Converts the specified string of alphabet characters into bytes.
	 * 
	 * @param	alphabet
	 * 				the alphabet as a string of characters
	 * @return	the alphabet as a byte array
	 * @throws	IllegalArgumentException
	 * 				if the string contains characters that are not 8-bit
	 */
	private static byte[] toAlphabetBytes(String alphabet)
	{
		if (alphabet == null)
		{
			return null;
		}
		
		Validate.isTrue(getMaxChar(alphabet) <= 0xFF, "alphabet must only contain 8-bit characters");
		
		return alphabet.getBytes();
	}
	
	/**
	 * Converts the specified pad character to a byte.
	 *
	 * @param	c
	 * 				the pad character
	 * @return	the pad byte
	 * @throws	IllegalArgumentException
	 * 				if the specified character is not 8-bit
	 */
	private static byte toPadByte(char c)
	{
		Validate.isTrue(c <= 0xFF, "Invalid pad byte: " + c);
		
		return (byte) c;
	}
	
	/**
	 * Gets the maximum character from the specified string.
	 *
	 * @param	string
	 * 				the string to check
	 * @return	the largest character within the specified string
	 */
	private static char getMaxChar(String string)
	{
		char max = Character.MIN_VALUE;
		
		for (int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
		
			if (c > max)
			{
				max = c;
			}
		}
		
		return max;
	}
	
	/**
	 * Gets whether the specified byte array contains unique bytes.
	 *
	 * @param	bytes
	 * 				the byte array to check
	 * @return	<code>true</code> if the specified byte array contains only unique bytes
	 */
	private static boolean containsUniqueBytes(byte[] bytes)
	{
		for (int i = 0; i < bytes.length; i++)
		{
			byte b = bytes[i];
			
			for (int j = i + 1; j < bytes.length; j++)
			{
				if (b == bytes[j])
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Gets whether the specified byte array contains the specified byte.
	 *
	 * @param	bytes
	 * 				the byte array to check
	 * @param	b
	 * 				the byte to check for
	 * @return	<code>true</code> if the specified byte array contains the specified byte
	 */
	private static boolean contains(byte[] bytes, byte b)
	{
		for (int i = 0; i < bytes.length; i++)
		{
			if (bytes[i] == b)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Creates an inverse lookup table for the specified alphabet.
	 *
	 * @param	alphabet
	 * 				an array that maps bytes to their encoded values, so that byte <code>b</code> is encoded to
	 * 				<code>alphabet[b]</code>
	 * @return	an array <code>inverseAlphabet</code> that maps encoded bytes to their original values, such that
	 * 			encoded byte <code>b</code> is decoded to <code>inverseAlphabet[b]</code>. A value of <code>-1</code>
	 * 			denotes that this encoded byte has no inverse mapping.
	 */
	private static int[] createInverseAlphabet(byte[] alphabet)
	{
		int[] inverse = new int[0xFF];
		
		Arrays.fill(inverse, -1);
		
		for (int i = 0; i < alphabet.length; i++)
		{
			inverse[alphabet[i]] = i;
		}
		
		return inverse;
	}
}
