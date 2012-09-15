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

import java.io.Closeable;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractObjectInput extends DelegatingDataInput implements ObjectInput
{
	// constructors -----------------------------------------------------------
	
	public AbstractObjectInput(DataInput in)
	{
		super(in);
	}
	
	// ObjectInput methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		return read(getDataInput());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] bytes) throws IOException
	{
		return read(bytes, 0, bytes.length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] bytes, int off, int len) throws IOException
	{
		return read(getDataInput(), bytes, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long skip(long n) throws IOException
	{
		return skip(getDataInput(), n);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int available() throws IOException
	{
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		close(getDataInput());
	}
	
	// package methods --------------------------------------------------------
	
	static int read(DataInput in) throws IOException
	{
		int b;
		
		try
		{
			b = in.readByte();
		}
		catch (EOFException exception)
		{
			b = -1;
		}
		
		return b;
	}
	
	static int read(DataInput in, byte[] bytes, int off, int len) throws IOException
	{
		int n = 0;
		
		try
		{
			while (n < len)
			{
				bytes[off + n] = in.readByte();
				n++;
			}
		}
		catch (EOFException exception)
		{
			// proceed
		}
		
		return n;
	}
	
	static long skip(DataInput in, long n) throws IOException
	{
		long actual = 0;
		
		while (n > Integer.MAX_VALUE)
		{
			actual += in.skipBytes(Integer.MAX_VALUE);
			
			n -= Integer.MAX_VALUE;
		}
		
		return actual;
	}
	
	static void close(DataInput in) throws IOException
	{
		if (in instanceof Closeable)
		{
			((Closeable) in).close();
		}
	}
}
