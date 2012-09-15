/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/StringDataOutputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code StringDataOutput}.
 * 
 * @author Mark Hobson
 * @see StringDataOutput
 */
public class StringDataOutputTest
{
	// fields -----------------------------------------------------------------
	
	private StringBuilder builder;
	
	private StringDataOutput out;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		builder = new StringBuilder();
		
		out = new StringDataOutput(builder);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void newStringDataOutputWithNullOutput()
	{
		new StringDataOutput(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void newStringDataOutputWithNullDelimiter()
	{
		new StringDataOutput(new StringBuilder(), null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newStringDataOutputWithEmptyDelimiter()
	{
		new StringDataOutput(new StringBuilder(), "");
	}
	
	@Test
	public void write() throws IOException
	{
		out.write(123);
		
		assertEquals("123", builder.toString());
	}
	
	@Test
	public void writeDelimiter() throws IOException
	{
		out.write(123);
		out.write(123);
		
		assertEquals("123,123", builder.toString());
	}
	
	@Test
	public void writeArray() throws IOException
	{
		out.write(new byte[] {1, 2, 3});
		
		assertEquals("1,2,3", builder.toString());
	}
	
	@Test
	public void writeArrayDelimiter() throws IOException
	{
		out.write(new byte[] {1, 2, 3});
		out.write(new byte[] {1, 2, 3});
		
		assertEquals("1,2,3,1,2,3", builder.toString());
	}
	
	@Test
	public void writeArrayWithOffsetAndLength() throws IOException
	{
		out.write(new byte[] {1, 2, 3, 4}, 1, 2);
		
		assertEquals("2,3", builder.toString());
	}
	
	@Test
	public void writeArrayWithOffsetAndLengthDelimiter() throws IOException
	{
		out.write(new byte[] {1, 2, 3, 4}, 1, 2);
		out.write(new byte[] {1, 2, 3, 4}, 1, 2);
		
		assertEquals("2,3,2,3", builder.toString());
	}
	
	@Test
	public void writeBoolean() throws IOException
	{
		out.writeBoolean(true);
		
		assertEquals("true", builder.toString());
	}
	
	@Test
	public void writeBooleanDelimiter() throws IOException
	{
		out.writeBoolean(true);
		out.writeBoolean(true);
		
		assertEquals("true,true", builder.toString());
	}
	
	@Test
	public void writeByte() throws IOException
	{
		out.writeByte(123);
		
		assertEquals("123", builder.toString());
	}
	
	@Test
	public void writeByteDelimiter() throws IOException
	{
		out.writeByte(123);
		out.writeByte(123);
		
		assertEquals("123,123", builder.toString());
	}
	
	@Test
	public void writeShort() throws IOException
	{
		out.writeShort(123);
		
		assertEquals("123", builder.toString());
	}
	
	@Test
	public void writeShortDelimiter() throws IOException
	{
		out.writeShort(123);
		out.writeShort(123);
		
		assertEquals("123,123", builder.toString());
	}
	
	@Test
	public void writeChar() throws IOException
	{
		out.writeChar('a');
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void writeCharDelimiter() throws IOException
	{
		out.writeChar('a');
		out.writeChar('a');
		
		assertEquals("a,a", builder.toString());
	}
	
	@Test
	public void writeInt() throws IOException
	{
		out.writeInt(123);
		
		assertEquals("123", builder.toString());
	}
	
	@Test
	public void writeIntDelimiter() throws IOException
	{
		out.writeInt(123);
		out.writeInt(123);
		
		assertEquals("123,123", builder.toString());
	}
	
	@Test
	public void writeLong() throws IOException
	{
		out.writeLong(123);
		
		assertEquals("123", builder.toString());
	}
	
	@Test
	public void writeLongDelimiter() throws IOException
	{
		out.writeLong(123);
		out.writeLong(123);
		
		assertEquals("123,123", builder.toString());
	}
	
	@Test
	public void writeFloat() throws IOException
	{
		out.writeFloat(123);
		
		assertEquals("123.0", builder.toString());
	}
	
	@Test
	public void writeFloatDelimiter() throws IOException
	{
		out.writeFloat(123);
		out.writeFloat(123);
		
		assertEquals("123.0,123.0", builder.toString());
	}
	
	@Test
	public void wstWriteDouble() throws IOException
	{
		out.writeDouble(123);
		
		assertEquals("123.0", builder.toString());
	}
	
	@Test
	public void writeDoubleDelimiter() throws IOException
	{
		out.writeDouble(123);
		out.writeDouble(123);
		
		assertEquals("123.0,123.0", builder.toString());
	}
	
	@Test
	public void writeBytes() throws IOException
	{
		out.writeBytes("a");
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void writeBytesDelimiter() throws IOException
	{
		out.writeBytes("a");
		out.writeBytes("a");
		
		assertEquals("a,a", builder.toString());
	}
	
	@Test
	public void writeChars() throws IOException
	{
		out.writeChars("a");
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void writeCharsDelimiter() throws IOException
	{
		out.writeChars("a");
		out.writeChars("a");
		
		assertEquals("a,a", builder.toString());
	}
	
	@Test
	public void writeUTF() throws IOException
	{
		out.writeUTF("a");
		
		assertEquals("a", builder.toString());
	}
	
	@Test
	public void writeUTFDelimiter() throws IOException
	{
		out.writeUTF("a");
		out.writeUTF("a");
		
		assertEquals("a,a", builder.toString());
	}
}
