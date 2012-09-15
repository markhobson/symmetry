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
import java.io.InputStream;
import java.io.OutputStream;

import org.hobsoft.symmetry.support.codec.AbstractByteStreamEncoder;
import org.hobsoft.symmetry.support.codec.ByteStreamCodec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a byte array to a byte stream and vice-versa.
 * 
 * @author Mark Hobson
 */
class ByteArrayToStreamCodec extends AbstractByteStreamEncoder<byte[]> implements ByteStreamCodec<byte[]>
{
	// constants --------------------------------------------------------------
	
	public static final ByteArrayToStreamCodec INSTANCE = new ByteArrayToStreamCodec();

	// ByteStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(byte[] bytes, OutputStream out) throws EncoderException
	{
		if (bytes == null)
		{
			return;
		}
		
		try
		{
			out.write(bytes);
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
	public byte[] decode(InputStream in) throws DecoderException
	{
		if (in == null)
		{
			return null;
		}
		
		try
		{
			return StreamUtils.toBytes(in);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
