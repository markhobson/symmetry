/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/AbstractByteStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Provides a base for encoders that encode an object into a stream of bytes.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 */
public abstract class AbstractByteStreamEncoder<X> implements ByteStreamEncoder<X>
{
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public final InputStream encode(X object) throws EncoderException
	{
		if (object == null)
		{
			return null;
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		encodeTo(object, out);
		
		byte[] bytes = out.toByteArray();
		
		return new ByteArrayInputStream(bytes);
	}
}
