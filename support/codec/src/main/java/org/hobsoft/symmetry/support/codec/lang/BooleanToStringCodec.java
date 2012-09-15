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
 * Codec that encodes a boolean to a string and vice-versa.
 * 
 * @author Mark Hobson
 */
class BooleanToStringCodec implements Codec<Boolean, String>
{
	// constants --------------------------------------------------------------
	
	public static final BooleanToStringCodec LENIENT_INSTANCE = new BooleanToStringCodec(true);
	
	public static final BooleanToStringCodec STRICT_INSTANCE = new BooleanToStringCodec(false);
	
	private static final boolean DEFAULT_LENIENT = true;
	
	private static final String TRUE_TOKEN = Boolean.TRUE.toString();
	
	private static final String FALSE_TOKEN = Boolean.FALSE.toString();
	
	// fields -----------------------------------------------------------------
	
	private final boolean lenient;
	
	// constructors -----------------------------------------------------------
	
	public BooleanToStringCodec()
	{
		this(DEFAULT_LENIENT);
	}

	public BooleanToStringCodec(boolean lenient)
	{
		this.lenient = lenient;
	}
	
	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Boolean object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}

		if (!lenient && !TRUE_TOKEN.equalsIgnoreCase(string) && !FALSE_TOKEN.equalsIgnoreCase(string))
		{
			throw new DecoderException("Cannot parse boolean: " + string);
		}
		
		return Boolean.valueOf(string);
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean isLenient()
	{
		return lenient;
	}
}
