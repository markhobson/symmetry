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

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.hobsoft.symmetry.support.codec.AbstractCharStreamEncoder;
import org.hobsoft.symmetry.support.codec.CharStreamCodec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes strings to char streams and vice-versa.
 * 
 * @author Mark Hobson
 */
class StringToCharStreamCodec extends AbstractCharStreamEncoder<String> implements CharStreamCodec<String>
{
	// constants --------------------------------------------------------------
	
	/**
	 * A shared instance.
	 */
	public static final StringToCharStreamCodec INSTANCE = new StringToCharStreamCodec();
	
	// CharStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(String string, Writer writer) throws EncoderException
	{
		if (writer == null)
		{
			return;
		}
		
		try
		{
			char[] chars = string.toCharArray();
			
			writer.write(chars);
		}
		catch (IOException exception)
		{
			throw new EncoderException(exception.getMessage());
		}
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String decode(Reader reader) throws DecoderException
	{
		if (reader == null)
		{
			return null;
		}

		try
		{
			char[] chars = StreamUtils.toChars(reader);
			
			return new String(chars);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.toString());
		}
	}
}
