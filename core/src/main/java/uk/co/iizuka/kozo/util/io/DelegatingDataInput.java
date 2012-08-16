/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DelegatingDataInput.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.DataInput;
import java.io.IOException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingDataInput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class DelegatingDataInput implements DataInput
{
	// fields -----------------------------------------------------------------
	
	private final DataInput in;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingDataInput(DataInput in)
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
	
	// protected methods ------------------------------------------------------
	
	protected DataInput getDataInput()
	{
		return in;
	}
}
