/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/ByteArrayToStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uk.co.iizuka.common.codec.AbstractByteStreamEncoder;
import uk.co.iizuka.common.codec.ByteStreamCodec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes a byte array to a byte stream and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: ByteArrayToStreamCodec.java 75589 2010-08-02 20:54:55Z mark@IIZUKA.CO.UK $
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
