/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/ByteStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.InputStream;

/**
 * Encodes and decodes objects to and from byte streams.
 * 
 * @author Mark Hobson
 * @version $Id: ByteStreamCodec.java 75577 2010-08-02 17:54:34Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this codec can encode
 */
public interface ByteStreamCodec<X> extends Codec<X, InputStream>, ByteStreamEncoder<X>
{
	// subtype to fix type parameters
}
