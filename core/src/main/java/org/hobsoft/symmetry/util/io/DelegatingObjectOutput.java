/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/DelegatingObjectOutput.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingObjectOutput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class DelegatingObjectOutput extends DelegatingDataOutput implements ObjectOutput
{
	// constructors -----------------------------------------------------------
	
	public DelegatingObjectOutput(ObjectOutput out)
	{
		super(out);
	}
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeObject(Object object) throws IOException
	{
		getObjectOutput().writeObject(object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		getObjectOutput().flush();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		getObjectOutput().close();
	}
	
	// protected methods ------------------------------------------------------
	
	protected ObjectOutput getObjectOutput()
	{
		return (ObjectOutput) getDataOutput();
	}
}
