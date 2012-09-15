/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/CharacterToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a character to a string and vice-versa.
 * 
 * @author Mark Hobson
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
