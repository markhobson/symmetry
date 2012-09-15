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

import org.apache.commons.lang.Validate;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes an enum to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @param <E>
 *            the enum type that this codec can encode
 */
class EnumToStringCodec<E extends Enum<E>> implements Codec<E, String>
{
	// fields -----------------------------------------------------------------
	
	private final Class<E> enumType;

	// constructors -----------------------------------------------------------
	
	public EnumToStringCodec(Class<E> enumType)
	{
		Validate.notNull(enumType, "enumType cannot be null");
		
		this.enumType = enumType;
	}

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(E object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public E decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return Enum.valueOf(enumType, string);
		}
		catch (IllegalArgumentException exception)
		{
			throw new DecoderException("Cannot parse enum: " + string);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public static <E extends Enum<E>> EnumToStringCodec<E> get(Class<E> enumType)
	{
		return new EnumToStringCodec<E>(enumType);
	}
}
