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
package org.hobsoft.symmetry.util.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An input stream that can read individual bits.
 * 
 * @author Mark Hobson
 */
public class BitInputStream extends FilterInputStream
{
	// constants --------------------------------------------------------------
	
	private static final int EOF = -1;
	
	// fields -----------------------------------------------------------------
	
	private int buffer;
	
	private int length;
	
	// constructors -----------------------------------------------------------
	
	public BitInputStream(InputStream in)
	{
		super(in);
		
		buffer = 0;
		length = 0;
	}
	
	// InputStream methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		if (buffer == EOF)
		{
			return EOF;
		}
		
		if (length == 0)
		{
			return super.read();
		}
		
		int b = (buffer << (8 - length)) & 0xFF;
		
		buffer = super.read();
		
		if (buffer != EOF)
		{
			b |= (buffer >>> length);
		}
		
		return b;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] bytes, int offset, int len) throws IOException
	{
		int i = 0;
		int b = 0;
		
		while (i < len && b != EOF)
		{
			b = read();
			
			if (b != EOF)
			{
				bytes[offset + i] = (byte) b;
				i++;
			}
		}
		
		return (i > 0) ? i : EOF;
	}
	
	// public methods ---------------------------------------------------------
	
	public int readBit() throws IOException
	{
		if (buffer != EOF && length == 0)
		{
			buffer = super.read();
			length = 8;
		}
		
		if (buffer == EOF)
		{
			return EOF;
		}
		
		length--;
		
		return (buffer >>> length) & 1;
	}
	
	public int readBits(int n) throws IOException
	{
		if (n <= 0 || n > 8)
		{
			throw new IllegalArgumentException("0 < n <= 8");
		}
		
		if (buffer == EOF)
		{
			return EOF;
		}
		
		if (n <= length)
		{
			length -= n;
			
			return (buffer >>> length) & ((1 << n) - 1);
		}
		
		int b = (buffer << (8 - length)) & 0xFF;
		
		buffer = super.read();
		
		if (buffer == EOF && length == 0)
		{
			return EOF;
		}
		
		if (buffer != EOF)
		{
			b |= (buffer >>> length);
		}
		
		b = b >> (8 - n);
		
		length = 8 - (n - length);
		
		return b;
	}
	
	public long skipBits(long n) throws IOException
	{
		long nBytes = n / 8;
		long actualBytes = skip(nBytes);
		long actualBits = 8 * actualBytes;
		
		if (actualBytes != nBytes)
		{
			return actualBits;
		}

		int nBits = (int) (n % 8);
		
		if (nBits <= length)
		{
			length -= nBits;
			actualBits += nBits;
		}
		else
		{
			actualBits += length;
			
			buffer = super.read();
			
			if (buffer != EOF)
			{
				int extraBits = nBits - length;
				length = 8 - extraBits;
				actualBits += extraBits;
			}
		}
		
		return actualBits;
	}
}
