/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/security/CipherCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version	$Id: CipherCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
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
