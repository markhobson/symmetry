/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/ObjectOutputStreamAdapter.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ObjectOutputStreamAdapter.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class ObjectOutputStreamAdapter extends ObjectOutputStream
{
	// TODO: PutField implementation?
	
	// fields -----------------------------------------------------------------
	
	private final ObjectOutput out;
	
	// constructors -----------------------------------------------------------
	
	public ObjectOutputStreamAdapter(ObjectOutput out) throws IOException
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
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		AbstractObjectOutput.flush(out);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		AbstractObjectOutput.close(out);
	}
	
	// ObjectOutputStream methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeObjectOverride(Object object) throws IOException
	{
		out.writeObject(object);
	}
}
