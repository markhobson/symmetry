/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DefaultDataInput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultDataInput.java 95220 2011-11-16 15:04:18Z mark@IIZUKA.CO.UK $
 */
public class DefaultDataInput implements DataInput, Closeable
{
	// constants --------------------------------------------------------------
	
	private static final int EOF = -1;
	
	// fields -----------------------------------------------------------------
	
	private final InputStream in;
	
	private final byte[] buffer;
	
	// constructors -----------------------------------------------------------
	
	public DefaultDataInput(InputStream in)
	{
		this.in = in;
		
		buffer = new byte[8];
	}
	
	// DataInput methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFully(byte[] b) throws IOException
	{
		readFully(b, 0, b.length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFully(byte[] b, int off, int len) throws IOException
	{
		int count;
		
		for (int n = 0; n < len; n += count)
		{
			count = in.read(b, off + n, len - n);
			
			if (count < 0)
			{
				throw new EOFException();
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int skipBytes(int n) throws IOException
	{
		int total = 0;
		int i = 1;
		
		while (total < n && i > 0)
		{
			i = (int) in.skip(n - total);
			
			if (i > 0)
			{
				total += i;
			}
		}
		
		return total;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean readBoolean() throws IOException
	{
		return (safeRead() != 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte readByte() throws IOException
	{
		return (byte) readUnsignedByte();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedByte() throws IOException
	{
		return safeRead();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public short readShort() throws IOException
	{
		return (short) readUnsignedShort();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedShort() throws IOException
	{
		int b1 = safeRead();
		int b2 = safeRead();
		
		return ((b1 << 8) + b2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public char readChar() throws IOException
	{
		return (char) readUnsignedShort();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readInt() throws IOException
	{
		int b1 = safeRead();
		int b2 = safeRead();
		int b3 = safeRead();
		int b4 = safeRead();
		
		return ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long readLong() throws IOException
	{
		readFully(buffer, 0, 8);
		
		return (((long) buffer[0] << 56)
			+ ((long) buffer[1] << 48)
			+ ((long) buffer[2] << 40)
			+ ((long) buffer[3] << 32)
			+ ((long) buffer[4] << 24)
			+ (buffer[5] << 16)
			+ (buffer[6] << 8) + (buffer[7]));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float readFloat() throws IOException
	{
		return Float.intBitsToFloat(readInt());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double readDouble() throws IOException
	{
		return Double.longBitsToDouble(readLong());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated This method does not properly convert bytes to characters. As of JDK&nbsp;1.1, the preferred way to
	 *             read lines of text is via the {@code BufferedReader.readLine()} method.
	 */
	@Override
	@Deprecated
	public String readLine() throws IOException
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readUTF() throws IOException
	{
		return DataInputStream.readUTF(this);
	}
	
	// Closeable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		in.close();
	}
	
	// protected methods ------------------------------------------------------
	
	protected InputStream getInputStream()
	{
		return in;
	}
	
	protected int safeRead() throws IOException
	{
		int b = in.read();
		
		if (b == EOF)
		{
			throw new EOFException();
		}
		
		return b;
	}
}
