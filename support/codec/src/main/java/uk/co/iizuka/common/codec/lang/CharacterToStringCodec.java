/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/CharacterToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes a character to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: CharacterToStringCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
class CharacterToStringCodec implements Codec<Character, String>
{
	// constants --------------------------------------------------------------
	
	public static final CharacterToStringCodec INSTANCE = new CharacterToStringCodec();

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Character object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Character decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		if (string.length() != 1)
		{
			throw new DecoderException("Cannot parse character: " + string);
		}
		
		return string.charAt(0);
	}
}
