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

import java.io.DataOutput;
import java.io.IOException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StringDataOutput implements DataOutput
{
	// constants ------------------------------------------------------------
	
	private static final String DEFAULT_DELIMITER = ",";
	
	// fields -----------------------------------------------------------------
	
	private final Appendable out;
	
	private final String delimiter;
	
	private boolean first;
	
	// constructors -----------------------------------------------------------
	
	public StringDataOutput(Appendable out)
	{
		this(out, DEFAULT_DELIMITER);
	}
	
	public StringDataOutput(Appendable out, String delimiter)
	{
		if (out == null)
		{
			throw new NullPointerException("out cannot be null");
		}
		
		if (delimiter == null)
		{
			throw new NullPointerException("delimiter cannot be null");
		}
		
		if (delimiter.isEmpty())
		{
			throw new IllegalArgumentException("delimiter cannot be empty");
		}
		
		this.out = out;
		this.delimiter = delimiter;
		
		first = true;
	}
	
	// DataOutput methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int b) throws IOException
	{
		writeByte(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b) throws IOException
	{
		write(b, 0, b.length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		for (int i = off; i < off + len; i++)
		{
			write(b[i]);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBoolean(boolean v) throws IOException
	{
		writeDelimiter();
		
		out.append(Boolean.toString(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeByte(int v) throws IOException
	{
		writeDelimiter();
		
		out.append(Byte.toString((byte) v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeShort(int v) throws IOException
	{
		writeDelimiter();
		
		out.append(Short.toString((short) v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChar(int v) throws IOException
	{
		writeDelimiter();
		
		out.append(Character.toString((char) v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInt(int v) throws IOException
	{
		writeDelimiter();
		
		out.append(Integer.toString(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeLong(long v) throws IOException
	{
		writeDelimiter();
		
		out.append(Long.toString(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeFloat(float v) throws IOException
	{
		writeDelimiter();
		
		out.append(Float.toString(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDouble(double v) throws IOException
	{
		writeDelimiter();
		
		out.append(Double.toString(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(String s) throws IOException
	{
		writeDelimiter();
		
		out.append(s);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChars(String s) throws IOException
	{
		writeDelimiter();
		
		out.append(s);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeUTF(String s) throws IOException
	{
		writeDelimiter();
		
		out.append(s);
	}
	
	// private methods --------------------------------------------------------
	
	private void writeDelimiter() throws IOException
	{
		if (first)
		{
			first = false;
		}
		else
		{
			out.append(delimiter);
		}
	}
}
