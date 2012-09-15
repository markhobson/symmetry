/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/DelegatingCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import org.apache.commons.lang.Validate;

/**
 * Codec that delegates encoding and decoding to another codec.
 * 
 * @author	Mark Hobson
 * @version	$Id: DelegatingCodec.java 75367 2010-07-23 17:59:07Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 */
public class DelegatingCodec<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The codec to delegate to.
	 */
	private final Codec<X, Y> codec;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a delegating codec that delegates to the specified codec.
	 *
	 * @param	codec
	 * 				the codec to delegate to
	 */
	public DelegatingCodec(Codec<X, Y> codec)
	{
		Validate.notNull(codec, "codec cannot be null");
		
		this.codec = codec;
	}
	
	// Codec methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return codec.encode(object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return codec.decode(object);
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the underlying codec that this codec delegates to.
	 *
	 * @return	the codec that this codec delegates to
	 */
	public Codec<X, Y> getCodec()
	{
		return codec;
	}
}
