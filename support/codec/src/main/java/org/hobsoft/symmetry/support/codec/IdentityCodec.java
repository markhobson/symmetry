/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/IdentityCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Identity codec that encodes and decodes an object to itself.
 * 
 * @author Mark Hobson
 * @version $Id: IdentityCodec.java 75376 2010-07-26 12:41:52Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this codec can accept
 */
final class IdentityCodec<X> implements Codec<X, X>
{
	// constants --------------------------------------------------------------
	
	private static final IdentityCodec<Object> INSTANCE = new IdentityCodec<Object>();
	
	// constructors -----------------------------------------------------------
	
	private IdentityCodec()
	{
		// private
	}

	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X encode(X object) throws EncoderException
	{
		return object;
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(X object) throws DecoderException
	{
		return object;
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X> IdentityCodec<X> get()
	{
		@SuppressWarnings("unchecked")
		IdentityCodec<X> codec = (IdentityCodec<X>) INSTANCE;
		
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
		return (object instanceof IdentityCodec<?>);
	}
}
