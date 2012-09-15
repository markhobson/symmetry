/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/TinyDataInputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests {@code TinyDataInput}.
 * 
 * @author Mark Hobson
 * @see TinyDataInput
 */
public class TinyDataInputTest
{
	// fields -----------------------------------------------------------------
	
	private TinyDataInput in;
	
	// readBoolean tests ------------------------------------------------------
	
	@Test
	public void readBooleanTrue() throws IOException
	{
		// 1000 0000
		in = createTinyDataInput(0x80);
		
		assertTrue(in.readBoolean());
	}
	
	@Test
	public void readBooleanFalse() throws IOException
	{
		// 0000 0000
		in = createTinyDataInput(0x00);
		
		assertFalse(in.readBoolean());
	}
	
	// readInt tests ----------------------------------------------------------
	
	@Test
	public void readIntByte() throws IOException
	{
		// 1110 0000 0100 0000
		in = createTinyDataInput(0xE0, 0x40);
		
		// 1000 0001
		assertEquals(0x81, in.readInt());
	}
	
	@Test
	public void readIntTwoBytes() throws IOException
	{
		// 1010 0000 0000 0000 0100 0000
		in = createTinyDataInput(0xA0, 0x00, 0x40);
		
		// 1000 0000 0000 0001
		assertEquals(0x8001, in.readInt());
	}
	
	@Test
	public void readIntThreeBytes() throws IOException
	{
		// 0110 0000 0000 0000 0000 0000 0100 0000
		in = createTinyDataInput(0x60, 0x00, 0x00, 0x40);
		
		// 1000 0000 0000 0000 0000 0001
		assertEquals(0x800001, in.readInt());
	}
	
	@Test
	public void readIntFourBytes() throws IOException
	{
		// 0010 0000 0000 0000 0000 0000 0000 0000 0100 0000
		in = createTinyDataInput(0x20, 0x00, 0x00, 0x00, 0x40);
		
		// 1000 0000 0000 0000 0000 0000 0000 0001
		assertEquals(0x80000001, in.readInt());
	}
	
	// private methods --------------------------------------------------------
	
	private static TinyDataInput createTinyDataInput(int... data)
	{
		byte[] bytes = new byte[data.length];
		
		for (int i = 0; i < data.length; i++)
		{
			bytes[i] = (byte) data[i];
		}
		
		return new TinyDataInput(new ByteArrayInputStream(bytes));
	}
}
