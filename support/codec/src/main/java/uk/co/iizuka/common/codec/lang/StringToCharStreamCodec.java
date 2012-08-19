/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/StringToCharStreamCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * Codec that encodes strings to char streams and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: StringToCharStreamCodec.java 75587 2010-08-02 20:41:05Z mark@IIZUKA.CO.UK $
 */
class StringToCharStreamCodec extends AbstractCharStreamEncoder<String> implements CharStreamCodec<String>
{
	// constants --------------------------------------------------------------
	
	/**
	 * A shared instance.
	 */
	public static final StringToCharStreamCodec INSTANCE = new StringToCharStreamCodec();
	
	// CharStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(String string, Writer writer) throws EncoderException
	{
		if (writer == null)
		{
			return;
		}
		
		try
		{
			char[] chars = string.toCharArray();
			
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
	public String decode(Reader reader) throws DecoderException
	{
		if (reader == null)
		{
			return null;
		}

		try
		{
			char[] chars = StreamUtils.toChars(reader);
			
			return new String(chars);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.toString());
		}
	}
}
