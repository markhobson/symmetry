/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/AbstractObjectOutput.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractObjectOutput.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractObjectOutput extends DelegatingDataOutput implements ObjectOutput
{
	// constructors -----------------------------------------------------------
	
	public AbstractObjectOutput(DataOutput out)
	{
		super(out);
	}
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		flush(getDataOutput());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		close(getDataOutput());
	}
	
	// package methods --------------------------------------------------------
	
	static void flush(DataOutput out) throws IOException
	{
		if (out instanceof Flushable)
		{
			((Flushable) out).flush();
		}
	}
	
	static void close(DataOutput out) throws IOException
	{
		if (out instanceof Closeable)
		{
			((Closeable) out).close();
		}
	}
}
