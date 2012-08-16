/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/BitInputStreamTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests {@code BitInputStream}.
 * 
 * @author Mark Hobson
 * @version $Id: BitInputStreamTest.java 98789 2012-02-27 12:01:42Z mark@IIZUKA.CO.UK $
 * @see BitInputStream
 */
public class BitInputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private BitInputStream in;
	
	// read() tests -----------------------------------------------------------
	
	@Test
	public void readAligned() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		assertEquals(0x53, in.read());
		assertEquals(-1, in.read());
		assertEquals(-1, in.read());
	}
	
	@Test
	public void readUnaligned() throws IOException
	{
		// 0101 0011 1100 1010
		in = createBitInputStream(0x53, 0xCA);
		
		in.skipBits(1);
		
		// 1010 0111
		assertEquals(0xA7, in.read());
		// 1001 0100
		assertEquals(0x94, in.read());
		assertEquals(-1, in.read());
		assertEquals(-1, in.read());
	}
	
	// read(byte[]) tests -----------------------------------------------------
	
	@Test
	public void readArrayAlignedSingle() throws IOException
	{
		in = createBitInputStream(0x53);
		
		byte[] bytes = new byte[1];
		
		assertEquals(1, in.read(bytes));
		assertEquals(0x53, bytes[0]);
		
		assertEquals(-1, in.read(bytes));
	}
	
	@Test
	public void readArrayAlignedSingleTwice() throws IOException
	{
		in = createBitInputStream(0x53, 0xCA);
		
		byte[] bytes = new byte[1];
		
		assertEquals(1, in.read(bytes));
		assertEquals(0x53, bytes[0]);
		
		assertEquals(1, in.read(bytes));
		assertEquals((byte) 0xCA, bytes[0]);
		
		assertEquals(-1, in.read(bytes));
	}
	
	@Test
	public void readArrayAlignedMultiple() throws IOException
	{
		in = createBitInputStream(0x53, 0xCA);
		
		byte[] bytes = new byte[2];
		
		assertEquals(2, in.read(bytes));
		assertEquals(0x53, bytes[0]);
		assertEquals((byte) 0xCA, bytes[1]);
		
		assertEquals(-1, in.read(bytes));
	}
	
	@Test
	public void readArrayUnalignedSingle() throws IOException
	{
		in = createBitInputStream(0x53, 0x80);
		
		in.skipBits(1);
		
		byte[] bytes = new byte[2];
		
		assertEquals(2, in.read(bytes));
		assertEquals((byte) 0xA7, bytes[0]);
		assertEquals(0x00, bytes[1]);
		
		assertEquals(-1, in.read(bytes));
	}
	
	// readBit tests ----------------------------------------------------------
	
	@Test
	public void readBitSingle() throws IOException
	{
		// 1000 0000
		in = createBitInputStream(0x80);
		
		assertEquals(1, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(-1, in.readBit());
		assertEquals(-1, in.readBit());
	}
	
	@Test
	public void readBitMultiple() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		assertEquals(0, in.readBit());
		assertEquals(1, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(1, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(0, in.readBit());
		assertEquals(1, in.readBit());
		assertEquals(1, in.readBit());
		assertEquals(-1, in.readBit());
		assertEquals(-1, in.readBit());
	}
	
	// readBits tests ---------------------------------------------------------
	
	@Test
	public void readBitsByBit() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		assertEquals(0, in.readBits(1));
		assertEquals(1, in.readBits(1));
		assertEquals(0, in.readBits(1));
		assertEquals(1, in.readBits(1));
		assertEquals(0, in.readBits(1));
		assertEquals(0, in.readBits(1));
		assertEquals(1, in.readBits(1));
		assertEquals(1, in.readBits(1));
		assertEquals(-1, in.readBits(1));
		assertEquals(-1, in.readBits(1));
	}
	
	@Test
	public void readBitsByCrumb() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		// 01
		assertEquals(1, in.readBits(2));
		// 01
		assertEquals(1, in.readBits(2));
		// 00
		assertEquals(0, in.readBits(2));
		// 11
		assertEquals(3, in.readBits(2));
		assertEquals(-1, in.readBits(2));
		assertEquals(-1, in.readBits(2));
	}
	
	@Test
	public void readBitsByTriplet() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		// 010
		assertEquals(2, in.readBits(3));
		// 100
		assertEquals(4, in.readBits(3));
		// 11
		assertEquals(6, in.readBits(3));
		assertEquals(-1, in.readBits(3));
		assertEquals(-1, in.readBits(3));
	}
	
	@Test
	public void readBitsByNibble() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		// 0101
		assertEquals(5, in.readBits(4));
		// 0011
		assertEquals(3, in.readBits(4));
		assertEquals(-1, in.readBits(4));
		assertEquals(-1, in.readBits(4));
	}
	
	@Test
	public void readBitsByByte() throws IOException
	{
		// 0101 0011
		in = createBitInputStream(0x53);
		
		assertEquals(0x53, in.readBits(8));
		assertEquals(-1, in.read());
		assertEquals(-1, in.read());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void readBitsZero() throws IOException
	{
		in = createBitInputStream();
		
		in.readBits(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void readBitsLessThanZero() throws IOException
	{
		in = createBitInputStream();
		
		in.readBits(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void readBitsGreaterThanEight() throws IOException
	{
		in = createBitInputStream();
		
		in.readBits(9);
	}
	
	// skipBits tests ---------------------------------------------------------
	
	@Test
	public void skipBitsByBit() throws IOException
	{
		// 0100 0000
		in = createBitInputStream(0x40);
		
		assertEquals(1, in.skipBits(1));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsByCrumb() throws IOException
	{
		// 0010 0000
		in = createBitInputStream(0x20);
		
		assertEquals(2, in.skipBits(2));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsByTriplet() throws IOException
	{
		// 0001 0000
		in = createBitInputStream(0x10);
		
		assertEquals(3, in.skipBits(3));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsByNibble() throws IOException
	{
		// 0000 1000
		in = createBitInputStream(0x08);
		
		assertEquals(4, in.skipBits(4));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsByByte() throws IOException
	{
		// 0000 0000 1000 0000
		in = createBitInputStream(0x00, 0x80);
		
		assertEquals(8, in.skipBits(8));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsByByteAndBit() throws IOException
	{
		// 0000 0000 0100 0000
		in = createBitInputStream(0x00, 0x40);
		
		assertEquals(9, in.skipBits(9));
		assertEquals(1, in.readBit());
	}
	
	@Test
	public void skipBitsOverEnd() throws IOException
	{
		// 0000 0000
		in = createBitInputStream(0x00);
		
		assertEquals(8, in.skipBits(9));
		assertEquals(-1, in.readBit());
	}
	
	// private methods --------------------------------------------------------
	
	private static BitInputStream createBitInputStream(int... data)
	{
		byte[] bytes = new byte[data.length];
		
		for (int i = 0; i < data.length; i++)
		{
			bytes[i] = (byte) data[i];
		}
		
		return new BitInputStream(new ByteArrayInputStream(bytes));
	}
}
