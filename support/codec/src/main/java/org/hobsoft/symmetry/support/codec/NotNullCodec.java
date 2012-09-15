/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/NotNullCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Codec decorator that raises an exception when encoding or decoding null objects.
 * 
 * @author Mark Hobson
 * @version $Id: NotNullCodec.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this codec can encode
 * @param <Y>
 *            the object type that this codec can decode
 */
class NotNullCodec<X, Y> extends DelegatingCodec<X, Y>
{
	// constructors -----------------------------------------------------------
	
	public NotNullCodec(Codec<X, Y> codec)
	{
		super(codec);
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Y encode(X object) throws EncoderException
	{
		if (object == null)
		{
			throw new EncoderException("Object cannot be null");
		}
		
		return super.encode(object);
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public X decode(Y object) throws DecoderException
	{
		if (object == null)
		{
			throw new DecoderException("Object cannot be null");
		}
		
		return super.decode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X, Y> NotNullCodec<X, Y> get(Codec<X, Y> codec)
	{
		return new NotNullCodec<X, Y>(codec);
	}
}
