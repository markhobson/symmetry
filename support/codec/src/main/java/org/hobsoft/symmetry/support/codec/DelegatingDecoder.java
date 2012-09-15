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
 * Decoder that delegates decoding to another decoder.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this decoder can decode
 * @param <Y>
 *            the object type that this decoder decodes to
 */
public class DelegatingDecoder<X, Y> implements Decoder<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Decoder<X, Y> decoder;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingDecoder(Decoder<X, Y> decoder)
	{
		Validate.notNull(decoder, "decoder cannot be null");
		
		this.decoder = decoder;
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y decode(X object) throws DecoderException
	{
		return decoder.decode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Decoder<X, Y> getDecoder()
	{
		return decoder;
	}
}
