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
 * Codec that adapts an encoder and a corresponding decoder.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this codec can encode
 * @param <Y>
 *            the object type that this codec can decode
 */
class CodecAdapter<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Encoder<X, Y> encoder;
	
	private final Decoder<Y, X> decoder;
	
	// constructors -----------------------------------------------------------
	
	public CodecAdapter(Encoder<X, Y> encoder, Decoder<Y, X> decoder)
	{
		if (encoder == null)
		{
			encoder = NullCodec.get();
		}
		
		if (decoder == null)
		{
			decoder = NullCodec.get();
		}
		
		this.encoder = encoder;
		this.decoder = decoder;
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return encoder.encode(object);
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return decoder.decode(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public Encoder<X, Y> getEncoder()
	{
		return encoder;
	}
	
	public Decoder<Y, X> getDecoder()
	{
		return decoder;
	}
	
	public static <X, Y> CodecAdapter<X, Y> get(Encoder<X, Y> encoder, Decoder<Y, X> decoder)
	{
		return new CodecAdapter<X, Y>(encoder, decoder);
	}
}
