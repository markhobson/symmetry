/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/BitOutputStreamTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code BitOutputStream}.
 * 
 * @author Mark Hobson
 * @see BitOutputStream
 */
public class BitOutputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream byteOut;
	
	private BitOutputStream out;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		byteOut = new ByteArrayOutputStream();
		
		out = new BitOutputStream(byteOut);
	}
	
	// write(int) tests -------------------------------------------------------
	
	@Test
	public void writeAligned() throws IOException
	{
		// 0101 0011
		out.write(0x53);
		
		assertBytes(0x53);
	}
	
	@Test
	public void writeUnaligned() throws IOException
	{
		// 1010 1001 1000 0000
		out.writeBit(1);
		out.write(0x53);
		out.flush();
		
		assertBytes(0xA9, 0x80);
	}
	
	// write(byte[]) tests ----------------------------------------------------
	
	@Test
	public void writeArrayAlignedSingle() throws IOException
	{
		// 0101 0011
		out.write(new byte[] {0x53});
		
		assertBytes(0x53);
	}
	
	@Test
	public void writeArrayAlignedMultiple() throws IOException
	{
		// 0101 0011 1100 1010
		out.write(new byte[] {0x53, (byte) 0xCA});
		
		assertBytes(0x53, 0xCA);
	}
	
	@Test
	public void writeArrayUnalignedSingle() throws IOException
	{
		// 1010 1001 1000 0000
		out.writeBit(1);
		out.write(new byte[] {0x53});
		out.flush();
		
		assertBytes(0xA9, 0x80);
	}
	
	@Test
	public void writeArrayUnalignedMultiple() throws IOException
	{
		// 1010 1001 1110 0101 0000 0000
		out.writeBit(1);
		out.write(new byte[] {0x53, (byte) 0xCA});
		out.flush();
		
		assertBytes(0xA9, 0xE5, 0x00);
	}
	
	// writeBit tests ---------------------------------------------------------
	
	@Test
	public void writeBitSingle() throws IOException
	{
		// 1000 0000
		out.writeBit(1);
		out.flush();
		
		assertBytes(0x80);
	}
	
	@Test
	public void writeBitByte() throws IOException
	{
		// 0101 0011
		out.writeBit(0);
		out.writeBit(1);
		out.writeBit(0);
		out.writeBit(1);
		out.writeBit(0);
		out.writeBit(0);
		out.writeBit(1);
		out.writeBit(1);
		
		assertBytes(0x53);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeBitLessThanZero() throws IOException
	{
		out.writeBit(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeBitGreaterThanOne() throws IOException
	{
		out.writeBit(2);
	}
	
	// writeBits tests --------------------------------------------------------
	
	@Test
	public void writeBitsZero() throws IOException
	{
		// 0000 0000
		out.writeBits(0x00, 0);
		out.flush();
		
		assertBytes();
	}
	
	@Test
	public void writeBitsSingle() throws IOException
	{
		// 1000 0000
		out.writeBits(0x01, 1);
		out.flush();
		
		assertBytes(0x80);
	}
	
	@Test
	public void writeBitsNibble() throws IOException
	{
		// 0000 1111
		out.writeBits(0x0F, 4);
		out.flush();
		
		assertBytes(0xF0);
	}
	
	@Test
	public void writeBitsByte() throws IOException
	{
		// 0101 0011
		out.writeBits(0x53, 8);
		
		assertBytes(0x53);
	}
	
	@Test
	public void writeBitsUnaligned() throws IOException
	{
		// 1010 1001 1000 0000
		out.writeBit(1);
		out.writeBits(0x53, 8);
		out.flush();
		
		assertBytes(0xA9, 0x80);
	}
	
	@Test
	public void writeBitsUnaligned2() throws IOException
	{
		// 1111 1111 1000 0000
		out.writeBits(0x7F, 7);
		out.writeBits(0x03, 2);
		out.flush();
		
		assertBytes(0xFF, 0x80);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeBitsInvalidByte() throws IOException
	{
		out.writeBits(0x100, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeBitsLessThanZero() throws IOException
	{
		out.writeBits(0xFF, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeBitsGreaterThanEight() throws IOException
	{
		out.writeBits(0xFF, 9);
	}
	
	// private methods --------------------------------------------------------
	
	private void assertBytes(int... expected)
	{
		BinaryAssert.assertBytes(expected, byteOut.toByteArray());
	}
}
