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
public class DelegatingDataOutput implements DataOutput
{
	// fields -----------------------------------------------------------------
	
	private final DataOutput out;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingDataOutput(DataOutput out)
	{
		if (out == null)
		{
			throw new IllegalArgumentException("out cannot be null");
		}
		
		this.out = out;
	}
	
	// DataOutput methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int b) throws IOException
	{
		out.write(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b) throws IOException
	{
		out.write(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		out.write(b, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBoolean(boolean v) throws IOException
	{
		out.writeBoolean(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeByte(int v) throws IOException
	{
		out.writeByte(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeShort(int v) throws IOException
	{
		out.writeShort(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChar(int v) throws IOException
	{
		out.writeChar(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInt(int v) throws IOException
	{
		out.writeInt(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeLong(long v) throws IOException
	{
		out.writeLong(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeFloat(float v) throws IOException
	{
		out.writeFloat(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDouble(double v) throws IOException
	{
		out.writeDouble(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(String s) throws IOException
	{
		out.writeBytes(s);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChars(String s) throws IOException
	{
		out.writeChars(s);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeUTF(String s) throws IOException
	{
		out.writeUTF(s);
	}
	
	// protected methods ------------------------------------------------------
	
	protected DataOutput getDataOutput()
	{
		return out;
	}
}
