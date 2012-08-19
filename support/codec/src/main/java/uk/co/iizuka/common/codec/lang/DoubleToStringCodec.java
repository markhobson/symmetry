/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/DoubleToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes a double to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: DoubleToStringCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
class DoubleToStringCodec implements Codec<Double, String>
{
	// constants --------------------------------------------------------------
	
	public static final DoubleToStringCodec INSTANCE = new DoubleToStringCodec();

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Double object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Double decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return Double.valueOf(string);
		}
		catch (NumberFormatException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
