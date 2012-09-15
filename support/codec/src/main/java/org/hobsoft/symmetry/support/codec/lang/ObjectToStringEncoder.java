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
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Encoder;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Encoder that encodes an object to a string.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 */
class ObjectToStringEncoder<X> implements Encoder<X, String>
{
	// constants --------------------------------------------------------------
	
	private static final ObjectToStringEncoder<Object> INSTANCE = new ObjectToStringEncoder<Object>();

	// Encoder methods --------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public String encode(X object) throws EncoderException
	{
		return String.valueOf(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X> ObjectToStringEncoder<X> get()
	{
		@SuppressWarnings("unchecked")
		ObjectToStringEncoder<X> encoder = (ObjectToStringEncoder<X>) INSTANCE;
		
		return encoder;
	}
}
