/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/builder/CompoundParticle.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.builder;

import org.apache.commons.lang.Validate;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.Codecs;

/**
 * Builder particle for creating compound codecs.
 * 
 * <p>This class is not intended to be used standalone; see <code>Codecs</code> for the intended usage.</p>
 * 
 * @author	Mark Hobson
 * @version	$Id: CompoundParticle.java 75576 2010-08-02 17:23:56Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this builder particle can encode
 * @param	<Y>
 * 				the object type that this builder particle can decode
 * @see		Codecs#compound(Codec)
 */
public class CompoundParticle<X, Y>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The underlying codec used by this builder particle.
	 */
	private final Codec<X, Y> codec;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a codec builder for the specified codec.
	 * 
	 * @param	codec
	 * 				the codec to use
	 */
	public CompoundParticle(Codec<X, Y> codec)
	{
		Validate.notNull(codec, "codec cannot be null");
		
		this.codec = codec;
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Appends the specified codec to the compound codec being built.
	 * 
	 * @param	nextCodec
	 * 				the codec to use next in the chain
	 * @return	a builder that can be used to add further codecs to the chain, or to obtain the resultant compound codec
	 * @param	<Z>
	 * 				the object type that the resultant codec chain can decode
	 */
	public <Z> CompoundParticle<X, Z> add(Codec<Y, Z> nextCodec)
	{
		Validate.notNull(nextCodec, "nextCodec cannot be null");
		
		Codec<X, Z> compoundCodec = Codecs.compound(this.codec, nextCodec);
		
		return new CompoundParticle<X, Z>(compoundCodec);
	}
	
	/**
	 * Obtains the resultant compound codec being built.
	 *
	 * @return	the compound codec
	 */
	public Codec<X, Y> toCodec()
	{
		return codec;
	}
}
