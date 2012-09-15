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
 * Encoder that delegates encoding to another encoder.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 * @param <Y>
 *            the object type that this encoder encodes to
 */
public class DelegatingEncoder<X, Y> implements Encoder<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Encoder<X, Y> encoder;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingEncoder(Encoder<X, Y> encoder)
	{
		Validate.notNull(encoder, "encoder cannot be null");
		
		this.encoder = encoder;
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return encoder.encode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Encoder<X, Y> getEncoder()
	{
		return encoder;
	}
}
