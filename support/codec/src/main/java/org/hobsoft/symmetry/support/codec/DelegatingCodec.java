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
 * Codec that delegates encoding and decoding to another codec.
 * 
 * @author	Mark Hobson
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 */
public class DelegatingCodec<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The codec to delegate to.
	 */
	private final Codec<X, Y> codec;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a delegating codec that delegates to the specified codec.
	 *
	 * @param	codec
	 * 				the codec to delegate to
	 */
	public DelegatingCodec(Codec<X, Y> codec)
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
		return codec.encode(object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return codec.decode(object);
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the underlying codec that this codec delegates to.
	 *
	 * @return	the codec that this codec delegates to
	 */
	public Codec<X, Y> getCodec()
	{
		return codec;
	}
}
