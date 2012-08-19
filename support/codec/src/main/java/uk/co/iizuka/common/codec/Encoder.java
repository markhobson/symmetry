/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Encoder.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

/**
 * Encodes an object into another object.
 * 
 * @author	Mark Hobson
 * @version	$Id: Encoder.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this encoder can encode
 * @param	<Y>
 * 				the object type that this encoder encodes to
 * @see		Decoder
 */
public interface Encoder<X, Y>
{
	/**
	 * Encodes the specified object into another object.
	 *
	 * @param	object
	 * 				the object to encode
	 * @return	the encoded object
	 * @throws	EncoderException
	 * 				if an error occurred encoding the object
	 */
	Y encode(X object) throws EncoderException;
}
