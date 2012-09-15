/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/DelegatingDecoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import org.apache.commons.lang.Validate;

/**
 * Decoder that delegates decoding to another decoder.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this decoder can decode
 * @param <Y>
 *            the object type that this decoder decodes to
 */
public class DelegatingDecoder<X, Y> implements Decoder<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Decoder<X, Y> decoder;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingDecoder(Decoder<X, Y> decoder)
	{
		Validate.notNull(decoder, "decoder cannot be null");
		
		this.decoder = decoder;
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y decode(X object) throws DecoderException
	{
		return decoder.decode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Decoder<X, Y> getDecoder()
	{
		return decoder;
	}
}
