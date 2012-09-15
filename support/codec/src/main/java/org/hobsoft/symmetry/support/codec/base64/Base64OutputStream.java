/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/base64/Base64OutputStream.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * An output stream that Base64 encodes bytes to an underlying stream. This class uses the Base64 encoding as specified
 * in RFC 2045, 6.8. Base64 Content-Transfer-Encoding.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64OutputStream.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @see		<a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>
 */
public class Base64OutputStream extends FilterOutputStream
{
	// TODO: support wrapping

	// fields -----------------------------------------------------------------

	/**
	 * The alphabet to use when encoding bytes.
	 */
	private final Base64Alphabet alphabet;
	
	/**
	 * Whether to pad the output if not a multiple of 24-bits.
	 */
	private final boolean pad;

	/**
	 * The byte count.
	 */
	private int count;

	/**
	 * The carry bits from the last written byte.
	 */
	private int carry;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an output stream that Base64 encodes bytes to the specified underlying stream using the default alphabet,
	 * padding upon closure to the 24-bit boundary.
	 * 
	 * @param	out
	 *				the output stream to write Base64 encoded bytes to
	 */
	public Base64OutputStream(OutputStream out)
	{
		this(out, Base64Alphabet.DEFAULT);
	}

	/**
	 * Creates an output stream that Base64 encodes bytes to the specified underlying stream using the specified
	 * alphabet, padding upon closure to the 24-bit boundary.
	 * 
	 * @param	out
	 *				the output stream to write Base64 encoded bytes to
	 * @param	alphabet
	 *				the Base64 alphabet to use when encoding bytes
	 */
	public Base64OutputStream(OutputStream out, Base64Alphabet alphabet)
	{
		this(out, alphabet, true);
	}

	/**
	 * Creates an output stream that Base64 encodes bytes to the specified underlying stream using the specified
	 * alphabet, optionally padding upon closure to the 24-bit boundary.
	 * 
	 * @param	out
	 *				the output stream to write Base64 encoded bytes to
	 * @param	alphabet
	 * 				the Base64 alphabet to use when encoding bytes
	 * @param	pad
	 *				whether to pad the stream upon closure to the 24-bit boundary with the pad character
	 */
	public Base64OutputStream(OutputStream out, Base64Alphabet alphabet, boolean pad)
	{
		super(out);
		
		this.alphabet = alphabet;
		this.pad = pad;
		
		count = 0;
		carry = 0;
	}

	// OutputStream methods ---------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int b) throws IOException
	{
		// convert signed int to unsigned byte
		if (b < 0)
		{
			b += 256;
		}

		// validate byte
		if ((b & ~0xFF) != 0)
		{
			throw new IOException("Invalid byte: " + b);
		}

		switch (count++ % 3)
		{
			// first byte of 24-bits: write 6-bits and carry 2-bits
			case 0:
				writeEncoded(b >> 2);
				carry = b & 0x03;
				break;

			// second byte of 24-bits: write carry + 4-bits, carry 4-bits
			case 1:
				writeEncoded((carry << 4) + (b >> 4));
				carry = b & 0x0F;
				break;

			// third byte of 24-bits: write carry + 2-bits, write 6-bits
			case 2:
				writeEncoded((carry << 2) + (b >> 6));
				writeEncoded(b & 0x3F);
				break;

			default:
				throw new InternalError();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		switch (count % 3)
		{
			// third byte of 24-bits: 24-bit aligned
			case 0:
				break;

			// first byte of 24-bits: write 4-bit carry and pad 16-bits
			case 1:
				writeEncoded(carry << 4);
				
				if (pad)
				{
					writePadChar();
					writePadChar();
				}
				
				break;

			// second byte of 24-bits: write 2-bit carry and pad 8-bits
			case 2:
				writeEncoded(carry << 2);
				
				if (pad)
				{
					writePadChar();
				}
				
				break;

			default:
				throw new InternalError();
		}

		// close underlying stream
		super.close();
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Writes the specified byte to the underlying output stream after Base64 encoding it.
	 * 
	 * @param	b
	 * 				the byte to encode and write
	 * @throws	IOException
	 * 				if an I/O error occurs
	 */
	private void writeEncoded(int b) throws IOException
	{
		try
		{
			b = alphabet.encode((byte) b);
		}
		catch (EncoderException exception)
		{
			throw new IOException("Invalid Base64 byte: " + b);
		}
		
		out.write(b);
	}
	
	/**
	 * Writes the pad character to the underlying output stream.
	 *
	 * @throws	IOException
	 * 				if an I/O error occurs
	 */
	private void writePadChar() throws IOException
	{
		out.write(alphabet.getPadByte());
	}
}
