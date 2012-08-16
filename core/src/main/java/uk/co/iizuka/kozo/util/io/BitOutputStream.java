/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/BitOutputStream.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that can write individual bits.
 * 
 * @author Mark Hobson
 * @version $Id: BitOutputStream.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class BitOutputStream extends FilterOutputStream
{
	// fields -----------------------------------------------------------------
	
	private int buffer;
	
	private int length;
	
	// constructors -----------------------------------------------------------
	
	public BitOutputStream(OutputStream out)
	{
		super(out);
		
		buffer = 0;
		length = 0;
	}
	
	// OutputStream methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int b) throws IOException
	{
		if (length == 0)
		{
			super.write(b);
		}
		else
		{
			super.write(buffer | (b >>> length));
			
			buffer = (b << (8 - length)) & 0xFF;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b, int offset, int len) throws IOException
	{
		if (length == 0)
		{
			super.write(b, offset, len);
		}
		else
		{
			int end = offset + len;
			
			for (int i = offset; i < end; i++)
			{
				write(b[i]);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		if (length > 0)
		{
			super.write(buffer);
			
			buffer = 0;
			length = 0;
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public void writeBit(int bit) throws IOException
	{
		if ((bit & 1) != bit)
		{
			throw new IllegalArgumentException("0 <= bit <= 1");
		}
		
		if (length == 7)
		{
			super.write(buffer | bit);
			
			buffer = 0;
			length = 0;
		}
		else
		{
			buffer |= (bit << (7 - length));
			length++;
		}
	}
	
	public void writeBits(int b, int n) throws IOException
	{
		if ((b & 0xFF) != b)
		{
			throw new IllegalArgumentException("Invalid byte: " + b);
		}
		
		if (n < 0 || n > 8)
		{
			throw new IllegalArgumentException("0 <= n <= 8");
		}
		
		if ((b & ((1 << n) - 1)) != b)
		{
			throw new IllegalArgumentException("Byte overflows bits: " + b);
		}
		
		if (length + n < 8)
		{
			buffer |= (b << (8 - length - n));
			length += n;
		}
		else if (length + n == 8)
		{
			super.write(buffer | b);
			
			buffer = 0;
			length = 0;
		}
		else
		{
			int m = n - (8 - length);
			
			super.write(buffer | (b >>> m));
			
			buffer = (b << (8 - m)) & 0xFF;
			length = m;
		}
	}
}
