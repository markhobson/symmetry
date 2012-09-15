/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/base64/Base64InputStream.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hobsoft.symmetry.support.codec.DecoderException;

/**
 * An input stream that Base64 decodes bytes from an underlying stream. This class uses the Base64 encoding as specified
 * in RFC 2045, 6.8. Base64 Content-Transfer-Encoding.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64InputStream.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @see		<a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>
 */
public class Base64InputStream extends FilterInputStream
{
	// TODO: support wrapping

	// fields -----------------------------------------------------------------
	
	/**
	 * The alphabet to use when decoding bytes.
	 */
	private final Base64Alphabet alphabet;

	/**
	 * The byte count.
	 */
	private int count;

	/**
	 * The carry bits from the last read byte.
	 */
	private int carry;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an input stream that Base64 decodes bytes from the specified underlying stream using the default
	 * alphabet.
	 * 
	 * @param	in
	 *				the input stream to decode Base64 bytes from
	 */
	public Base64InputStream(InputStream in)
	{
		this(in, Base64Alphabet.DEFAULT);
	}

	/**
	 * Creates an input stream that Base64 decodes bytes from the specified underlying stream using the specified
	 * alphabet.
	 * 
	 * @param	in
	 *				the input stream to decode Base64 bytes from
	 * @param	alphabet
	 *				the Base64 alphabet to use when decoding bytes
	 */
	public Base64InputStream(InputStream in, Base64Alphabet alphabet)
	{
		super(in);
		
		this.alphabet = alphabet;
		
		count = 0;
		carry = 0;
	}

	// InputStream methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		int b = in.read();
		
		if (b == -1 || b == alphabet.getPadByte())
		{
			return -1;
		}
		
		if ((b & ~0xFF) != 0)
		{
			throw new IOException("Invalid byte: " + b);
		}

		try
		{
			b = alphabet.decode((byte) b);
		}
		catch (DecoderException exception)
		{
			throw new IOException("Invalid Base64 byte: " + b);
		}

		int r;
		
		switch (count++ % 4)
		{
			case 0:
				carry = b;
				r = read();
				break;
				
			case 1:
				r = (carry << 2) + (b >> 4);
				carry = b & 0x0F;
				break;
				
			case 2:
				r = (carry << 4) + (b >> 2);
				carry = b & 0x03;
				break;
				
			case 3:
				r = (carry << 6) + b;
				break;
				
			default:
				throw new InternalError();
		}
		
		return r;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException
	{
		int end = offset + length;
		
		for (int i = offset; i < end; i++)
		{
			int b = read();
			
			if (b == -1)
			{
				return (i == offset) ? -1 : i - offset;
			}
			
			bytes[i] = (byte) b;
		}
		
		return length;
	}
}
