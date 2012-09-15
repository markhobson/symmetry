/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/CharStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.Reader;

/**
 * Encodes and decodes objects to and from character streams.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this codec can encode
 */
public interface CharStreamCodec<X> extends Codec<X, Reader>, CharStreamEncoder<X>
{
	// subtype to fix type parameters
}
