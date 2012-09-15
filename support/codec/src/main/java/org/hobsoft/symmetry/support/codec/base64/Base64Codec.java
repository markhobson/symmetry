/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/base64/Base64Codec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import java.io.IOException;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes and decodes using the Base 64 algorithm.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64Codec.java 75547 2010-08-02 08:59:47Z mark@IIZUKA.CO.UK $
 */
public class Base64Codec implements Codec<byte[], byte[]>
{
	// constants --------------------------------------------------------------

	/**
	 * Shared instance that uses the default alphabet, padding upon closure to the 24-bit boundary.
	 */
	public static final Base64Codec INSTANCE = new Base64Codec();
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The alphabet to use when encoding and decoding bytes.
	 */
	private final Base64Alphabet alphabet;
	
	/**
	 * Whether to pad the output if not a multiple of 24-bits.
	 */
	private final boolean pad;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a Base64 codec that uses the default alphabet, padding upon closure to the 24-bit boundary.
	 */
	public Base64Codec()
	{
		this(Base64Alphabet.DEFAULT);
	}

	/**
	 * Creates a Base64 codec that uses the specified alphabet, padding upon closure to the 24-bit boundary.
	 *
	 * @param	alphabet
	 * 				the Base64 alphabet to use when encoding and decoding bytes
	 */
	public Base64Codec(Base64Alphabet alphabet)
	{
		this(alphabet, true);
	}
	
	/**
	 * Creates a Base64 codec that uses the specified alphabet, optionally padding upon closure to the 24-bit boundary.
	 *
	 * @param	alphabet
	 * 				the Base64 alphabet to use when encoding and decoding bytes
	 * @param	pad
	 *				whether to pad to the 24-bit boundary with the pad character
	 */
	public Base64Codec(Base64Alphabet alphabet, boolean pad)
	{
		this.alphabet = alphabet;
		this.pad = pad;
	}
	
	// Codec methods ----------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public byte[] encode(byte[] bytes) throws EncoderException
	{
		if (bytes == null)
		{
			return null;
		}

		try
		{
			return Base64Utils.encode(bytes, alphabet, pad);
		}
		catch (IOException exception)
		{
			throw new EncoderException(exception.toString());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] decode(byte[] bytes) throws DecoderException
	{
		if (bytes == null)
		{
			return null;
		}

		try
		{
			return Base64Utils.decode(bytes, alphabet);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.toString());
		}
	}
}
