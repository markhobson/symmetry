/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/CodecAdapter.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Codec that adapts an encoder and a corresponding decoder.
 * 
 * @author Mark Hobson
 * @version $Id: CodecAdapter.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @param <X>
 *            the object type that this codec can encode
 * @param <Y>
 *            the object type that this codec can decode
 */
class CodecAdapter<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Encoder<X, Y> encoder;
	
	private final Decoder<Y, X> decoder;
	
	// constructors -----------------------------------------------------------
	
	public CodecAdapter(Encoder<X, Y> encoder, Decoder<Y, X> decoder)
	{
		if (encoder == null)
		{
			encoder = NullCodec.get();
		}
		
		if (decoder == null)
		{
			decoder = NullCodec.get();
		}
		
		this.encoder = encoder;
		this.decoder = decoder;
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return encoder.encode(object);
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return decoder.decode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Encoder<X, Y> getEncoder()
	{
		return encoder;
	}
	
	public Decoder<Y, X> getDecoder()
	{
		return decoder;
	}
	
	public static <X, Y> CodecAdapter<X, Y> get(Encoder<X, Y> encoder, Decoder<Y, X> decoder)
	{
		return new CodecAdapter<X, Y>(encoder, decoder);
	}
}
