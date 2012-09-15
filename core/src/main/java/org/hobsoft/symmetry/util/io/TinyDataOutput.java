/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/TinyDataOutput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TinyDataOutput.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class TinyDataOutput extends DefaultDataOutput
{
	// constructors -----------------------------------------------------------
	
	public TinyDataOutput(OutputStream out)
	{
		super((out instanceof BitOutputStream) ? (BitOutputStream) out : new BitOutputStream(out));
	}
	
	// DataOutput methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBoolean(boolean v) throws IOException
	{
		getBitOutputStream().writeBit(v ? 1 : 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInt(int v) throws IOException
	{
		int b0 = (v >>> 24) & 0xFF;
		int b1 = (v >>> 16) & 0xFF;
		int b2 = (v >>> 8) & 0xFF;
		
		int n = (b0 == 0) ? (b1 == 0) ? (b2 == 0) ? 3 : 2 : 1 : 0;
		getBitOutputStream().writeBits(n, 2);
		
		if (n == 0)
		{
			write(b0);
		}
		
		if (n <= 1)
		{
			write(b1);
		}
		
		if (n <= 2)
		{
			write(b2);
		}
		
		if (n <= 3)
		{
			write(v & 0xFF);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected BitOutputStream getBitOutputStream()
	{
		return (BitOutputStream) getOutputStream();
	}
}
