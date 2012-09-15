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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Encoder that adapts a character stream codec into a byte stream codec.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 */
class CharByteStreamCodecAdapter<X> extends AbstractByteStreamEncoder<X> implements ByteStreamCodec<X>
{
	// fields -----------------------------------------------------------------
	
	private final CharStreamCodec<X> delegate;
	
	private final Charset charset;
	
	// constructors -----------------------------------------------------------
	
	public CharByteStreamCodecAdapter(CharStreamCodec<X> delegate)
	{
		this(delegate, (Charset) null);
	}
	
	public CharByteStreamCodecAdapter(CharStreamCodec<X> delegate, String charsetName)
	{
		this(delegate, Charset.forName(charsetName));
	}
	
	public CharByteStreamCodecAdapter(CharStreamCodec<X> delegate, Charset charset)
	{
		if (charset == null)
		{
			charset = Charset.defaultCharset();
		}
		
		this.delegate = delegate;
		this.charset = charset;
	}
	
	// ByteStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(X object, OutputStream output) throws EncoderException
	{
		Writer writer = new OutputStreamWriter(output, charset);
		
		delegate.encodeTo(object, writer);
		
		try
		{
			writer.flush();
		}
		catch (IOException exception)
		{
			// ignore
		}
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(InputStream in) throws DecoderException
	{
		if (in == null)
		{
			return null;
		}
		
		Reader reader = new InputStreamReader(in, charset);
		
		return delegate.decode(reader);
	}
}
