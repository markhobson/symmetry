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

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a float to a string and vice-versa.
 * 
 * @author Mark Hobson
 */
class FloatToStringCodec implements Codec<Float, String>
{
	// constants --------------------------------------------------------------
	
	public static final FloatToStringCodec INSTANCE = new FloatToStringCodec();

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Float object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Float decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return Float.valueOf(string);
		}
		catch (NumberFormatException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
