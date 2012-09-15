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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ObjectInputStreamAdapter extends ObjectInputStream
{
	// TODO: GetField implementation?
	
	// fields -----------------------------------------------------------------
	
	private final ObjectInput in;
	
	// constructors -----------------------------------------------------------
	
	public ObjectInputStreamAdapter(ObjectInput in) throws IOException
	{
		if (in == null)
		{
			throw new IllegalArgumentException("in cannot be null");
		}
		
		this.in = in;
	}
	
	// DataInput methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFully(byte[] b) throws IOException
	{
		in.readFully(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFully(byte[] b, int off, int len) throws IOException
	{
		in.readFully(b, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int skipBytes(int n) throws IOException
	{
		return in.skipBytes(n);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean readBoolean() throws IOException
	{
		return in.readBoolean();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte readByte() throws IOException
	{
		return in.readByte();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedByte() throws IOException
	{
		return in.readUnsignedByte();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public short readShort() throws IOException
	{
		return in.readShort();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedShort() throws IOException
	{
		return in.readUnsignedShort();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public char readChar() throws IOException
	{
		return in.readChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readInt() throws IOException
	{
		return in.readInt();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long readLong() throws IOException
	{
		return in.readLong();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float readFloat() throws IOException
	{
		return in.readFloat();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double readDouble() throws IOException
	{
		return in.readDouble();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readLine() throws IOException
	{
		return in.readLine();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readUTF() throws IOException
	{
		return in.readUTF();
	}
	
	// ObjectInput methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		return AbstractObjectInput.read(in);
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
		return AbstractObjectInput.read(in, bytes, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long skip(long n) throws IOException
	{
		return AbstractObjectInput.skip(in, n);
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
		AbstractObjectInput.close(in);
	}
	
	// ObjectInputStream methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object readObjectOverride() throws IOException, ClassNotFoundException
	{
		return in.readObject();
	}
}
