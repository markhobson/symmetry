/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/StringDataInput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StringDataInput implements DataInput
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_DELIMITER = ",";
	
	// fields -----------------------------------------------------------------
	
	private final StringTokenizer tokenizer;
	
	// constructors -----------------------------------------------------------
	
	public StringDataInput(String in)
	{
		this(in, DEFAULT_DELIMITER);
	}
	
	public StringDataInput(String in, String delimiter)
	{
		if (in == null)
		{
			throw new NullPointerException("in cannot be null");
		}
		
		if (delimiter == null)
		{
			throw new NullPointerException("delimiter cannot be null");
		}
		
		if (delimiter.isEmpty())
		{
			throw new IllegalArgumentException("delimiter cannot be empty");
		}
		
		tokenizer = new StringTokenizer(in, delimiter);
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
		int end = off + len;
		
		for (int i = off; i < end; i++)
		{
			b[i] = readByte();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int skipBytes(int n) throws IOException
	{
		int actual = 0;
		
		try
		{
			while (n > 0)
			{
				readToken();
				
				actual++;
				n--;
			}
		}
		catch (EOFException exception)
		{
			// proceed
		}
		
		return actual;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean readBoolean() throws IOException
	{
		return Boolean.parseBoolean(readToken());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte readByte() throws IOException
	{
		String token = readToken();
		
		try
		{
			return Byte.parseByte(token);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException("Invalid byte: " + token);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedByte() throws IOException
	{
		String token = readToken();
		
		int v = parseInt(token, "Invalid unsigned byte");
		
		if (!isSize(v, Byte.SIZE))
		{
			throw new IOException("Invalid unsigned byte: " + token);
		}
		
		return v;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public short readShort() throws IOException
	{
		String token = readToken();
		
		try
		{
			return Short.parseShort(token);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException("Invalid short: " + token);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readUnsignedShort() throws IOException
	{
		String token = readToken();
		
		int v = parseInt(token, "Invalid unsigned short");
		
		if (!isSize(v, Short.SIZE))
		{
			throw new IOException("Invalid unsigned short: " + token);
		}
		
		return v;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public char readChar() throws IOException
	{
		String token = readToken();
		
		if (token.length() != 1)
		{
			throw new IOException("Invalid char: " + token);
		}
		
		return token.charAt(0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readInt() throws IOException
	{
		return parseInt(readToken(), "Invalid int");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long readLong() throws IOException
	{
		String token = readToken();
		
		try
		{
			return Long.parseLong(token);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException("Invalid long: " + token);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float readFloat() throws IOException
	{
		String token = readToken();
		
		try
		{
			return Float.parseFloat(token);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException("Invalid float: " + token);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double readDouble() throws IOException
	{
		String token = readToken();
		
		try
		{
			return Double.parseDouble(token);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException("Invalid double: " + token);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readLine() throws IOException
	{
		// TODO: implement
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readUTF() throws IOException
	{
		try
		{
			return readToken();
		}
		catch (EOFException exception)
		{
			return "";
		}
	}
	
	// private methods --------------------------------------------------------
	
	private String readToken() throws IOException
	{
		if (!tokenizer.hasMoreTokens())
		{
			throw new EOFException();
		}
		
		return tokenizer.nextToken();
	}
	
	private static int parseInt(String string, String message) throws IOException
	{
		try
		{
			return Integer.parseInt(string);
		}
		catch (NumberFormatException exception)
		{
			throw new IOException(message + ": " + string);
		}
	}
	
	private static boolean isSize(int v, int size)
	{
		int mask = (1 << size) - 1;
		
		return (v & ~mask) == 0;
	}
}
