/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/CharArrayToStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.hobsoft.symmetry.support.codec.AbstractCharStreamEncoder;
import org.hobsoft.symmetry.support.codec.CharStreamCodec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a char array to a char stream and vice-versa.
 * 
 * @author Mark Hobson
 */
class CharArrayToStreamCodec extends AbstractCharStreamEncoder<char[]> implements CharStreamCodec<char[]>
{
	// constants --------------------------------------------------------------
	
	public static final CharArrayToStreamCodec INSTANCE = new CharArrayToStreamCodec();

	// CharStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(char[] chars, Writer writer) throws EncoderException
	{
		if (chars == null)
		{
			return;
		}
		
		try
		{
			writer.write(chars);
		}
		catch (IOException exception)
		{
			throw new EncoderException(exception.getMessage());
		}
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public char[] decode(Reader reader) throws DecoderException
	{
		if (reader == null)
		{
			return null;
		}
		
		try
		{
			return StreamUtils.toChars(reader);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
