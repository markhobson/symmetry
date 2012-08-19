/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/ObjectToStringEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import uk.co.iizuka.common.codec.Encoder;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Encoder that encodes an object to a string.
 * 
 * @author Mark Hobson
 * @version $Id: ObjectToStringEncoder.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
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
