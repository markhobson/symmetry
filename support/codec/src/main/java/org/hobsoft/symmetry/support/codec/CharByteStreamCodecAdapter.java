/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/CharByteStreamCodecAdapter.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: CharByteStreamCodecAdapter.java 75587 2010-08-02 20:41:05Z mark@IIZUKA.CO.UK $
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
