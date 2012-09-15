/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/AbstractCharStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Provides a base for encoders that encode an object into a stream of characters.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractCharStreamEncoder.java 75581 2010-08-02 18:51:27Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this encoder can encode
 */
public abstract class AbstractCharStreamEncoder<X> implements CharStreamEncoder<X>
{
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public final Reader encode(X object) throws EncoderException
	{
		if (object == null)
		{
			return null;
		}
		
		StringWriter writer = new StringWriter();
		
		encodeTo(object, writer);
		
		String string = writer.toString();
		
		return new StringReader(string);
	}
}
