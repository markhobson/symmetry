/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Decoder.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Decodes an object into another object.
 * 
 * @author	Mark Hobson
 * @version	$Id: Decoder.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this decoder can decode
 * @param	<Y>
 * 				the object type that this decoder decodes to
 * @see		Encoder
 */
public interface Decoder<X, Y>
{
	/**
	 * Decodes the specified object into another object.
	 *
	 * @param	object
	 * 				the object to decode
	 * @return	the decoded object
	 * @throws	DecoderException
	 * 				if an error occurred decoding the object
	 */
	Y decode(X object) throws DecoderException;
}
