/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Codec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

/**
 * Encodes and decodes objects to and from another object.
 * 
 * @author	Mark Hobson
 * @version	$Id: Codec.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 * @see		Encoder
 * @see		Decoder
 */
public interface Codec<X, Y> extends Encoder<X, Y>, Decoder<Y, X>
{
	// convenience interface
}
