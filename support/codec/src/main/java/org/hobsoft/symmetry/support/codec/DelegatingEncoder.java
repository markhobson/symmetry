/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/DelegatingEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import org.apache.commons.lang.Validate;

/**
 * Encoder that delegates encoding to another encoder.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 * @param <Y>
 *            the object type that this encoder encodes to
 */
public class DelegatingEncoder<X, Y> implements Encoder<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Encoder<X, Y> encoder;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingEncoder(Encoder<X, Y> encoder)
	{
		Validate.notNull(encoder, "encoder cannot be null");
		
		this.encoder = encoder;
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return encoder.encode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Encoder<X, Y> getEncoder()
	{
		return encoder;
	}
}
