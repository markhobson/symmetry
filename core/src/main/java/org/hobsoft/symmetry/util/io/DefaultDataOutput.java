/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DefaultDataOutput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultDataOutput implements DataOutput, Flushable, Closeable
{
	// fields -----------------------------------------------------------------
	
	private final OutputStream out;
	
	private final byte[] buffer;
	
	// constructors -----------------------------------------------------------
	
	public DefaultDataOutput(OutputStream out)
	{
		this.out = out;
		
		buffer = new byte[8];
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
		write(b, 0, b.length);
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
		write(v ? 1 : 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeByte(int v) throws IOException
	{
		write(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeShort(int v) throws IOException
	{
		write((v >>> 8) & 0xFF);
		write(v & 0xFF);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChar(int v) throws IOException
	{
		writeShort(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInt(int v) throws IOException
	{
		write((v >>> 24) & 0xFF);
		write((v >>> 16) & 0xFF);
		write((v >>> 8) & 0xFF);
		write(v & 0xFF);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeLong(long v) throws IOException
	{
		buffer[0] = (byte) (v >>> 56);
		buffer[1] = (byte) (v >>> 48);
		buffer[2] = (byte) (v >>> 40);
		buffer[3] = (byte) (v >>> 32);
		buffer[4] = (byte) (v >>> 24);
		buffer[5] = (byte) (v >>> 16);
		buffer[6] = (byte) (v >>> 8);
		buffer[7] = (byte) v;
		
		write(buffer, 0, 8);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeFloat(float v) throws IOException
	{
		writeInt(Float.floatToIntBits(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDouble(double v) throws IOException
	{
		writeLong(Double.doubleToLongBits(v));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(String s) throws IOException
	{
		int length = s.length();
		
		for (int i = 0; i < length; i++)
		{
			write((byte) s.charAt(i));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeChars(String s) throws IOException
	{
		int length = s.length();
		
		for (int i = 0; i < length; i++)
		{
			writeChar(s.charAt(i));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeUTF(String s) throws IOException
	{
		writeUTF(s, this);
	}
	
	// Flushable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		out.flush();
	}
	
	// Closeable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		out.close();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Writes a string to the specified {@code DataOutput} using modified UTF-8 encoding in a machine-independent
	 * manner.
	 * 
	 * @param s
	 *            the string to be written
	 * @param out
	 *            the destination to write to
	 * @return the number of bytes written out
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see java.io.DataInput
	 */
	public static int writeUTF(String s, DataOutput out) throws IOException
	{
		int length = s.length();
		char[] chars = new char[length];
		
		s.getChars(0, length, chars, 0);
		
		int utfLength = 0;
		
		for (int i = 0; i < length; i++)
		{
			int c = chars[i];
			
			if (c >= 0x0001 && c <= 0x007F)
			{
				utfLength++;
			}
			else if (c > 0x007F)
			{
				utfLength += 3;
			}
			else
			{
				utfLength += 2;
			}
		}
		
		if (utfLength > 0xFFFF)
		{
			throw new UTFDataFormatException("String too long");
		}
		
		byte[] bytes = new byte[utfLength + 2];
		
		bytes[0] = (byte) ((utfLength >>> 8) & 0xFF);
		bytes[1] = (byte) (utfLength & 0xFF);
		
		int count = 2;
		
		for (int i = 0; i < length; i++)
		{
			int c = chars[i];
			
			if (c >= 0x0001 && c <= 0x007F)
			{
				bytes[count++] = (byte) c;
			}
			else if (c > 0x007F)
			{
				bytes[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytes[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytes[count++] = (byte) (0x80 | (c & 0x3F));
			}
			else
			{
				bytes[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytes[count++] = (byte) (0x80 | (c & 0x3F));
			}
		}
		
		out.write(bytes);
		
		return bytes.length;
	}
	
	// protected methods ------------------------------------------------------
	
	protected OutputStream getOutputStream()
	{
		return out;
	}
}
