/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/math/BigIntegerToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.math;

import java.math.BigInteger;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.support.codec.lang.LangEncoders;

/**
 * Codec that encodes a big integer to a string and vice-versa.
 * 
 * @author Mark Hobson
 */
class BigIntegerToStringCodec implements Codec<BigInteger, String>
{
	// constants --------------------------------------------------------------
	
	public static final BigIntegerToStringCodec INSTANCE = new BigIntegerToStringCodec();

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(BigInteger object) throws EncoderException
	{
		return LangEncoders.objectToString().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public BigInteger decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return new BigInteger(string);
		}
		catch (NumberFormatException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
