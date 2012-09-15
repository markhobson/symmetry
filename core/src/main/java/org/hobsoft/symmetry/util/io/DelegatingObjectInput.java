/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DelegatingObjectInput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingObjectInput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class DelegatingObjectInput extends DelegatingDataInput implements ExplicitObjectInput
{
	// constructors -----------------------------------------------------------
	
	public DelegatingObjectInput(ObjectInput in)
	{
		super(in);
	}
	
	// ExplicitObjectInput methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject(Class<?> type) throws ClassNotFoundException, IOException
	{
		Object object;
		
		if (getObjectInput() instanceof ExplicitObjectInput)
		{
			object = ((ExplicitObjectInput) getObjectInput()).readObject(type);
		}
		else
		{
			object = readObject();
		}
		
		return object;
	}
	
	// ObjectInput methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject() throws ClassNotFoundException, IOException
	{
		return getObjectInput().readObject();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		return getObjectInput().read();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b) throws IOException
	{
		return getObjectInput().read(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b, int off, int len) throws IOException
	{
		return getObjectInput().read(b, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long skip(long n) throws IOException
	{
		return getObjectInput().skip(n);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int available() throws IOException
	{
		return getObjectInput().available();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		getObjectInput().close();
	}
	
	// protected methods ------------------------------------------------------
	
	protected ObjectInput getObjectInput()
	{
		return (ObjectInput) getDataInput();
	}
}
