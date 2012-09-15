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

/**
 * Codec decorator that raises an exception when encoding or decoding null objects.
 * 
 * @author Mark Hobson
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
