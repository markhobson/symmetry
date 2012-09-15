/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Encoders.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Encoders.java 75587 2010-08-02 20:41:05Z mark@IIZUKA.CO.UK $
 */
public final class Encoders
{
	// constructors -----------------------------------------------------------
	
	private Encoders()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	// TODO: compound

	public static <X, Y> Encoder<X, Y> notNull(Encoder<X, Y> encoder)
	{
		return Codecs.notNull(Codecs.adapt(encoder));
	}
	
	public static <X, Y> Encoder<X, Y> adapt(Decoder<X, Y> decoder)
	{
		return Codecs.inverse(Codecs.adapt(decoder));
	}
}
