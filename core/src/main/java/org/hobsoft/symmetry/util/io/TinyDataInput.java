/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/TinyDataInput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TinyDataInput.java 86025 2011-03-23 12:14:38Z mark@IIZUKA.CO.UK $
 */
public class TinyDataInput extends DefaultDataInput
{
	// constructors -----------------------------------------------------------
	
	public TinyDataInput(InputStream in)
	{
		super((in instanceof BitInputStream) ? (BitInputStream) in : new BitInputStream(in));
	}
	
	// DataInput methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean readBoolean() throws IOException
	{
		return (getBitInputStream().readBit() == 1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readInt() throws IOException
	{
		int n = getBitInputStream().readBits(2);
		
		int b0 = (n == 0) ? safeRead() : 0;
		int b1 = (n <= 1) ? safeRead() : 0;
		int b2 = (n <= 2) ? safeRead() : 0;
		int b3 = (n <= 3) ? safeRead() : 0;
		
		return ((b0 << 24) + (b1 << 16) + (b2 << 8) + b3);
	}
	
	// protected methods ------------------------------------------------------
	
	protected BitInputStream getBitInputStream()
	{
		return (BitInputStream) getInputStream();
	}
}
