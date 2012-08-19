/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/math/BigDecimalToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.math;

import java.math.BigDecimal;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;
import uk.co.iizuka.common.codec.lang.LangEncoders;

/**
 * Codec that encodes a big decimal to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: BigDecimalToStringCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
class BigDecimalToStringCodec implements Codec<BigDecimal, String>
{
	// constants --------------------------------------------------------------
	
	public static final BigDecimalToStringCodec INSTANCE = new BigDecimalToStringCodec();

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(BigDecimal object) throws EncoderException
	{
		return LangEncoders.objectToString().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public BigDecimal decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return new BigDecimal(string);
		}
		catch (NumberFormatException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
