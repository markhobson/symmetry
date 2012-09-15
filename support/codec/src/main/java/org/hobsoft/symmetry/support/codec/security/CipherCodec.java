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

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.lang.Validate;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that uses a cipher to perform the encoding and decoding.
 * 
 * @author	Mark Hobson
 */
class CipherCodec implements Codec<byte[], byte[]>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The cipher used by this codec.
	 */
	private final Cipher cipher;
	
	/**
	 * The key to use with the cipher.
	 */
	private final Key key;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a cipher codec for the specified cipher and key.
	 * 
	 * @param	cipher
	 * 				the cipher to use
	 * @param	key
	 * 				the key to use with the cipher
	 */
	public CipherCodec(Cipher cipher, Key key)
	{
		Validate.notNull(cipher, "cipher cannot be null");
		Validate.notNull(key, "key cannot be null");
		
		this.cipher = cipher;
		this.key = key;
	}
	
	// Codec methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] encode(byte[] input) throws EncoderException
	{
		if (input == null)
		{
			return null;
		}

		try
		{
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			return cipher.doFinal(input);
		}
		catch (InvalidKeyException exception)
		{
			throw new EncoderException(exception.toString());
		}
		catch (IllegalBlockSizeException exception)
		{
			throw new EncoderException(exception.toString());
		}
		catch (BadPaddingException exception)
		{
			throw new EncoderException(exception.toString());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] decode(byte[] input) throws DecoderException
	{
		if (input == null)
		{
			return null;
		}

		try
		{
			cipher.init(Cipher.DECRYPT_MODE, key);

			return cipher.doFinal(input);
		}
		catch (InvalidKeyException exception)
		{
			throw new DecoderException(exception.toString());
		}
		catch (IllegalBlockSizeException exception)
		{
			throw new DecoderException(exception.toString());
		}
		catch (BadPaddingException exception)
		{
			throw new DecoderException(exception.toString());
		}
	}
}
