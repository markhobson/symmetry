/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/security/MessageDigestCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.security;

import java.security.DigestException;
import java.security.MessageDigest;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that appends a digest when encoding and removes and validates the digest when decoding.
 * 
 * @author	Mark Hobson
 * @version	$Id: MessageDigestCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
class MessageDigestCodec implements Codec<byte[], byte[]>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The message digest used by this codec.
	 */
	private final MessageDigest digest;
	
	/**
	 * A reusable array to store computed hashes in.
	 */
	private final byte[] hash;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a message digest codec for the specified message digest.
	 * 
	 * @param	digest
	 * 				the message digest to use
	 */
	public MessageDigestCodec(MessageDigest digest)
	{
		this.digest = digest;
		
		int hashLength = digest.getDigestLength();
		
		if (hashLength == 0)
		{
			throw new IllegalArgumentException("Digest length of zero not supported");
		}
		
		hash = new byte[hashLength];
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

		// compute hash
		
		digest.reset();
		digest.update(input);
		
		try
		{
			digest.digest(hash, 0, hash.length);
		}
		catch (DigestException exception)
		{
			throw new EncoderException(exception.toString());
		}
		
		// concatenate input with hash
		
		byte[] output = new byte[input.length + hash.length];
		
		System.arraycopy(input, 0, output, 0, input.length);
		System.arraycopy(hash, 0, output, input.length, hash.length);
		
		return output;
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

		int keyLength = input.length - hash.length;
		
		if (keyLength < 0)
		{
			throw new DecoderException("Insufficient bytes for digest");
		}
		
		// compute hash
		
		digest.reset();
		digest.update(input, 0, keyLength);
		
		try
		{
			digest.digest(hash, 0, hash.length);
		}
		catch (DigestException exception)
		{
			throw new DecoderException(exception.toString());
		}
		
		// ensure hashes are equal

		if (!isEqual(hash, input, keyLength))
		{
			throw new DecoderException("Mismatched digest");
		}
		
		// remove hash from input
		
		byte[] output = new byte[keyLength];
		
		System.arraycopy(input, 0, output, 0, keyLength);
		
		return output;
	}

	// private methods --------------------------------------------------------
	
	/**
	 * Compares byte array <code>a</code> from index zero with byte array <code>b</code> from index <code>offset</code>.
	 * 
	 * @param	a
	 * 				the first byte array to compare from index zero
	 * @param	b
	 * 				the second byte array to compare from index <code>offset</code>
	 * @param	offset
	 * 				the offset into byte array <code>b</code> to start comparing from
	 * @return	<code>true</code> if byte array <code>a</code> is equal to byte array <code>b</code> starting from index
	 * 			<code>offset</code>
	 */
	private static boolean isEqual(byte[] a, byte[] b, int offset)
	{
		if (b.length - offset != a.length)
		{
			throw new IllegalArgumentException("Array lengths must be equal");
		}
		
		boolean equal = true;
		
		for (int i = 0; i < a.length && equal; i++)
		{
			if (a[i] != b[offset + i])
			{
				equal = false;
			}
		}
		
		return equal;
	}
}
