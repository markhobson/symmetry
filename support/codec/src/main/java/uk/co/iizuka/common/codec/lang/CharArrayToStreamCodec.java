/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/CharArrayToStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import uk.co.iizuka.common.codec.AbstractCharStreamEncoder;
import uk.co.iizuka.common.codec.CharStreamCodec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes a char array to a char stream and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: CharArrayToStreamCodec.java 75588 2010-08-02 20:52:50Z mark@IIZUKA.CO.UK $
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
