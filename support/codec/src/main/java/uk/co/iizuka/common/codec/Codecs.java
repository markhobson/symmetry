/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/Codecs.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import java.nio.charset.Charset;
import java.util.Map;

import uk.co.iizuka.common.codec.builder.CompoundParticle;

/**
 * Factory class for building codecs.
 * 
 * @author Mark Hobson
 * @version $Id: Codecs.java 92871 2011-09-23 12:49:17Z mark@IIZUKA.CO.UK $
 */
public final class Codecs
{
	// constructors -----------------------------------------------------------
	
	private Codecs()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X, Y> Codec<X, Y> nullCodec()
	{
		return NullCodec.get();
	}
	
	public static <X> Codec<X, X> identity()
	{
		return IdentityCodec.get();
	}
	
	/**
	 * Obtains an inverse of the specified codec.
	 * 
	 * @param <X>
	 *            the object type that the specified codec encodes
	 * @param <Y>
	 *            the object type that the specified codec decodes
	 * @param codec
	 *            the codec to invert
	 * @return the inverted codec
	 */
	public static <X, Y> Codec<Y, X> inverse(Codec<X, Y> codec)
	{
		return InverseCodec.get(codec);
	}

	public static <X, Y, Z> Codec<X, Z> compound(Codec<X, Y> codec1, Codec<Y, Z> codec2)
	{
		return CompoundCodec.get(codec1, codec2);
	}
	
	/**
	 * Initiates creating a compound codec that starts with the specified codec.  This method returns a builder that
	 * allows subsequent codecs to be added to the chain.
	 * 
	 * <p>It is intended to be used as follows:</p>
	 * 
	 * <pre>
	 * Codec&lt;A, C&gt; compoundCodec = CodecBuilder
	 *     .compound(abCodec)
	 *     .add(bcCodec)
	 *     .toCodec();
	 * </pre>
	 * 
	 * @param	codec
	 * 				the first codec to use in the chain
	 * @return	a builder that can be used to add further codecs to the chain; use <code>toCodec()</code> to obtain the
	 * 			resultant compound codec
	 * @param	<X>
	 * 				the object type that the first codec can encode
	 * @param	<Y>
	 * 				the object type that the first codec can decode
	 */
	public static <X, Y> CompoundParticle<X, Y> compound(Codec<X, Y> codec)
	{
		return new CompoundParticle<X, Y>(codec);
	}

	public static <X, Y> Codec<X, Y> notNull(Codec<X, Y> codec)
	{
		return NotNullCodec.get(codec);
	}
	
	public static <X, Y> Codec<X, Y> adapt(Encoder<X, Y> encoder)
	{
		return adapt(encoder, null);
	}
	
	public static <X, Y> Codec<X, Y> adapt(Decoder<Y, X> decoder)
	{
		return adapt(null, decoder);
	}
	
	public static <X, Y> Codec<X, Y> adapt(Encoder<X, Y> encoder, Decoder<Y, X> decoder)
	{
		return CodecAdapter.get(encoder, decoder);
	}
	
	public static <X> ByteStreamCodec<X> asByteStream(CharStreamCodec<X> codec)
	{
		return new CharByteStreamCodecAdapter<X>(codec);
	}
	
	public static <X> ByteStreamCodec<X> asByteStream(CharStreamCodec<X> codec, String charsetName)
	{
		return new CharByteStreamCodecAdapter<X>(codec, charsetName);
	}
	
	public static <X> ByteStreamCodec<X> asByteStream(CharStreamCodec<X> codec, Charset charset)
	{
		return new CharByteStreamCodecAdapter<X>(codec, charset);
	}
	
	public static <X, Y> Codec<X, Y> forMap(Map<X, Y> map)
	{
		return new MapCodec<X, Y>(map);
	}
	
	public static <X, Y> Codec<X, Y> forMap(Map<X, Y> encodingMap, Map<Y, X> decodingMap)
	{
		return new MapCodec<X, Y>(encodingMap, decodingMap);
	}
}
