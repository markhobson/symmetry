/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/DelegatingDataOutputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import static org.junit.Assert.assertSame;

import java.io.DataOutput;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code DelegatingDataOutput}.
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingDataOutputTest.java 95224 2011-11-16 15:37:43Z mark@IIZUKA.CO.UK $
 * @see DelegatingDataOutput
 */
public class DelegatingDataOutputTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private DataOutput out;
	
	private DelegatingDataOutput delegator;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		out = mockery.mock(DataOutput.class);
		
		delegator = new DelegatingDataOutput(out);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullDataOutput()
	{
		new DelegatingDataOutput(null);
	}
	
	@Test
	public void write() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).write(123);
		} });
		
		delegator.write(123);
	}
	
	@Test
	public void writeWithArray() throws IOException
	{
		final byte[] b = new byte[8];
		
		mockery.checking(new Expectations() { {
			one(out).write(b);
		} });
		
		delegator.write(b);
	}
	
	@Test
	public void writeWithArrayAndOffsetAndLength() throws IOException
	{
		final byte[] b = new byte[8];
		
		mockery.checking(new Expectations() { {
			one(out).write(b, 2, 4);
		} });
		
		delegator.write(b, 2, 4);
	}
	
	@Test
	public void writeBoolean() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeBoolean(true);
		} });
		
		delegator.writeBoolean(true);
	}
	
	@Test
	public void writeByte() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeByte(123);
		} });
		
		delegator.writeByte(123);
	}
	
	@Test
	public void writeShort() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeShort(123);
		} });
		
		delegator.writeShort(123);
	}
	
	@Test
	public void writeChar() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeChar('a');
		} });
		
		delegator.writeChar('a');
	}
	
	@Test
	public void writeInt() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeInt(123);
		} });
		
		delegator.writeInt(123);
	}
	
	@Test
	public void writeLong() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeLong(123L);
		} });
		
		delegator.writeLong(123L);
	}
	
	@Test
	public void writeFloat() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeFloat(123f);
		} });
		
		delegator.writeFloat(123f);
	}
	
	@Test
	public void writeDouble() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeDouble(123d);
		} });
		
		delegator.writeDouble(123d);
	}
	
	@Test
	public void writeBytes() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeBytes("abc");
		} });
		
		delegator.writeBytes("abc");
	}
	
	@Test
	public void writeChars() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeChars("abc");
		} });
		
		delegator.writeChars("abc");
	}
	
	@Test
	public void writeUTF() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(out).writeUTF("abc");
		} });
		
		delegator.writeUTF("abc");
	}
	
	@Test
	public void getDataOutput()
	{
		assertSame(out, delegator.getDataOutput());
	}
}
