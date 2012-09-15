/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/LongToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a long to a string and vice-versa.
 * 
 * @author Mark Hobson
 */
class LongToStringCodec implements Codec<Long, String>
{
	// constants --------------------------------------------------------------
	
	public static final LongToStringCodec INSTANCE = new LongToStringCodec();
	
	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Long object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Long decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return Long.valueOf(string);
		}
		catch (NumberFormatException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
