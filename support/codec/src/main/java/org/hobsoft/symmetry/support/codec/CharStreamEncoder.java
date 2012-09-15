/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/CharStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.Reader;
import java.io.Writer;

/**
 * Encodes an object into a stream of characters.
 * 
 * @author Mark Hobson
 * @version $Id: CharStreamEncoder.java 75577 2010-08-02 17:54:34Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this encoder can encode
 */
public interface CharStreamEncoder<X> extends Encoder<X, Reader>
{
	/**
	 * Encodes the specified object to the specified writer.
	 * 
	 * @param object
	 *            the object to encode
	 * @param writer
	 *            the writer to encode the object to
	 * @throws EncoderException
	 *             if an error occurred encoding the object
	 */
	void encodeTo(X object, Writer writer) throws EncoderException;
}
