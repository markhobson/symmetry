/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.codec;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Codec that chains together two other compatible codecs.
 * 
 * @author	Mark Hobson
 * @param	<X>
 * 				the object type that the first codec can encode from
 * @param	<Y>
 * 				the object type that the first codec encodes to, and the second codec encodes from
 * @param	<Z>
 * 				the object type that the second codec encodes to
 */
class CompoundCodec<X, Y, Z> implements Codec<X, Z>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The codec that is invoked first when encoding.
	 */
	private final Codec<X, Y> codec1;
	
	/**
	 * The codec that is invoked second when encoding.
	 */
	private final Codec<Y, Z> codec2;

	// constructors -----------------------------------------------------------

	/**
	 * Creates a codec that chains together two other compatible codecs.
	 * 
	 * @param	codec1
	 * 				the codec to be invoked first when encoding
	 * @param	codec2
	 * 				the codec to be invoked second when encoding
	 */
	public CompoundCodec(Codec<X, Y> codec1, Codec<Y, Z> codec2)
	{
		Validate.notNull(codec1, "codec1 cannot be null");
		Validate.notNull(codec2, "codec2 cannot be null");
		
		this.codec1 = codec1;
		this.codec2 = codec2;
	}
	
	// Codec methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Z encode(X object) throws EncoderException
	{
		return codec2.encode(codec1.encode(object));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Z object) throws DecoderException
	{
		return codec1.decode(codec2.decode(object));
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the codec that is invoked first when encoding.
	 * 
	 * @return	the first codec
	 */
	public Codec<X, Y> getCodec1()
	{
		return codec1;
	}
	
	/**
	 * Gets the codec that is invoked second when encoding.
	 * 
	 * @return	the second codec
	 */
	public Codec<Y, Z> getCodec2()
	{
		return codec2;
	}
	
	public static <X, Y, Z> CompoundCodec<X, Y, Z> get(Codec<X, Y> codec1, Codec<Y, Z> codec2)
	{
		return new CompoundCodec<X, Y, Z>(codec1, codec2);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder()
			.append(codec1)
			.append(codec2)
			.toHashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof CompoundCodec<?, ?, ?>))
		{
			return false;
		}
		
		CompoundCodec<?, ?, ?> compound = (CompoundCodec<?, ?, ?>) object;
		
		return new EqualsBuilder()
			.append(codec1, compound.codec1)
			.append(codec2, compound.codec2)
			.isEquals();
	}
}
