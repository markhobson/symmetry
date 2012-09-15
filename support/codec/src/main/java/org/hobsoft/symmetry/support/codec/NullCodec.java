/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/NullCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Null implementation of a codec.
 * 
 * @author Mark Hobson
 * @version $Id: NullCodec.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this codec can encode
 * @param <Y>
 *            the object type that this codec can decode
 */
final class NullCodec<X, Y> implements Codec<X, Y>
{
	// constants --------------------------------------------------------------
	
	private static final NullCodec<Object, Object> INSTANCE = new NullCodec<Object, Object>();
	
	// constructors -----------------------------------------------------------
	
	private NullCodec()
	{
		// private
	}

	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return null;
	}

	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return null;
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X, Y> NullCodec<X, Y> get()
	{
		@SuppressWarnings("unchecked")
		NullCodec<X, Y> codec = (NullCodec<X, Y>) INSTANCE;
		
		return codec;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return getClass().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		return (object instanceof NullCodec<?, ?>);
	}
}
