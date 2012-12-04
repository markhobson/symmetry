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
package org.hobsoft.symmetry.support.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that can write individual bits.
 * 
 * @author Mark Hobson
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
