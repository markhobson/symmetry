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
 * Identity codec that encodes and decodes an object to itself.
 * 
 * @author Mark Hobson
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
