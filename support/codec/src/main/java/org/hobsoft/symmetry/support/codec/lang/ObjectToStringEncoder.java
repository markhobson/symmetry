/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/ObjectToStringEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Encoder;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Encoder that encodes an object to a string.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 */
class ObjectToStringEncoder<X> implements Encoder<X, String>
{
	// constants --------------------------------------------------------------
	
	private static final ObjectToStringEncoder<Object> INSTANCE = new ObjectToStringEncoder<Object>();

	// Encoder methods --------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public String encode(X object) throws EncoderException
	{
		return String.valueOf(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X> ObjectToStringEncoder<X> get()
	{
		@SuppressWarnings("unchecked")
		ObjectToStringEncoder<X> encoder = (ObjectToStringEncoder<X>) INSTANCE;
		
		return encoder;
	}
}
