/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/TinyDataOutputTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code TinyDataOutput}.
 * 
 * @author Mark Hobson
 * @version $Id: TinyDataOutputTest.java 98789 2012-02-27 12:01:42Z mark@IIZUKA.CO.UK $
 * @see TinyDataOutput
 */
public class TinyDataOutputTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream byteOut;
	
	private TinyDataOutput out;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		byteOut = new ByteArrayOutputStream();
		
		out = new TinyDataOutput(byteOut);
	}
	
	// writeBoolean tests -----------------------------------------------------
	
	@Test
	public void writeBooleanTrue() throws IOException
	{
		out.writeBoolean(true);
		out.getBitOutputStream().writeBit(1);
		out.flush();
		
		// 1100 0000
		assertBytes(0xC0);
	}
	
	@Test
	public void writeBooleanFalse() throws IOException
	{
		out.writeBoolean(false);
		out.getBitOutputStream().writeBit(1);
		out.flush();
		
		// 0100 0000
		assertBytes(0x40);
	}
	
	// writeInt tests ---------------------------------------------------------
	
	@Test
	public void writeIntByte() throws IOException
	{
		// 1000 0001
		out.writeInt(0x81);
		out.flush();
		
		// 1110 0000 0100 0000
		assertBytes(0xE0, 0x40);
	}
	
	@Test
	public void writeIntTwoBytes() throws IOException
	{
		// 1000 0000 0000 0001
		out.writeInt(0x8001);
		out.flush();
		
		// 1010 0000 0000 0000 0100 0000
		assertBytes(0xA0, 0x00, 0x40);
	}
	
	@Test
	public void writeIntThreeBytes() throws IOException
	{
		// 1000 0000 0000 0000 0000 0001
		out.writeInt(0x800001);
		out.flush();
		
		// 0110 0000 0000 0000 0000 0000 0100 0000
		assertBytes(0x60, 0x00, 0x00, 0x40);
	}
	
	@Test
	public void writeIntFourBytes() throws IOException
	{
		// 1000 0000 0000 0000 0000 0000 0000 0001
		out.writeInt(0x80000001);
		out.flush();
		
		// 0010 0000 0000 0000 0000 0000 0000 0000 0100 0000
		assertBytes(0x20, 0x00, 0x00, 0x00, 0x40);
	}
	
	// private methods --------------------------------------------------------
	
	private void assertBytes(int... expected)
	{
		BinaryAssert.assertBytes(expected, byteOut.toByteArray());
	}
}
