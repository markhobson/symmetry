/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Decoders.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Decoders.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 */
public final class Decoders
{
	// constructors -----------------------------------------------------------
	
	private Decoders()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	// TODO: compound

	public static <X, Y> Decoder<X, Y> notNull(Decoder<X, Y> decoder)
	{
		return Codecs.notNull(Codecs.adapt(decoder));
	}
	
	public static <X, Y> Decoder<X, Y> adapt(Encoder<X, Y> encoder)
	{
		return Codecs.inverse(Codecs.adapt(encoder));
	}
}
