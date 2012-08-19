/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/ByteStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Encodes an object into a stream of bytes.
 * 
 * @author Mark Hobson
 * @version $Id: ByteStreamEncoder.java 75577 2010-08-02 17:54:34Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this encoder can encode
 */
public interface ByteStreamEncoder<X> extends Encoder<X, InputStream>
{
	/**
	 * Encodes the specified object to the specified output stream.
	 * 
	 * @param object
	 *            the object to encode
	 * @param out
	 *            the output stream to encode the object to
	 * @throws EncoderException
	 *             if an error occurred encoding the object
	 */
	void encodeTo(X object, OutputStream out) throws EncoderException;
}
