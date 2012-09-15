/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/InverseCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import org.apache.commons.lang.Validate;

/**
 * Reverses a codec so encoding becomes decoding and vice-versa.
 * 
 * @author	Mark Hobson
 * @version	$Id: InverseCodec.java 75575 2010-08-02 17:22:56Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 */
class InverseCodec<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The underlying codec that this codec reverses.
	 */
	private final Codec<Y, X> codec;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a codec that reverses the specified codec.
	 * 
	 * @param	codec
	 * 				the codec to reverse
	 */
	public InverseCodec(Codec<Y, X> codec)
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
		try
		{
			return codec.decode(object);
		}
		catch (DecoderException exception)
		{
			throw new EncoderException(exception.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		try
		{
			return codec.encode(object);
		}
		catch (EncoderException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}

	// public methods ---------------------------------------------------------

	/**
	 * Gets the underlying codec that this codec reverses.
	 * 
	 * @return	the underling codec
	 */
	public Codec<Y, X> getCodec()
	{
		return codec;
	}
	
	public static <X, Y> InverseCodec<X, Y> get(Codec<Y, X> codec)
	{
		return new InverseCodec<X, Y>(codec);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return codec.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof InverseCodec<?, ?>))
		{
			return false;
		}
		
		InverseCodec<?, ?> inverseCodec = (InverseCodec<?, ?>) object;
		
		return codec.equals(inverseCodec.codec);
	}
}
