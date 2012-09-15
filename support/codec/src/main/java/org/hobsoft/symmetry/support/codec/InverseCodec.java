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

/**
 * Reverses a codec so encoding becomes decoding and vice-versa.
 * 
 * @author	Mark Hobson
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
