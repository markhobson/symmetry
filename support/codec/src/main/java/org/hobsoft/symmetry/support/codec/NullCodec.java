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
 * Null implementation of a codec.
 * 
 * @author Mark Hobson
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
