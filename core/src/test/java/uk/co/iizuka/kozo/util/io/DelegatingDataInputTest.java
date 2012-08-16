/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/DelegatingDataInputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.DataInput;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code DelegatingDataInput}.
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingDataInputTest.java 95224 2011-11-16 15:37:43Z mark@IIZUKA.CO.UK $
 * @see DelegatingDataInput
 */
public class DelegatingDataInputTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private DataInput in;
	
	private DelegatingDataInput delegator;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		in = mockery.mock(DataInput.class);
		
		delegator = new DelegatingDataInput(in);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullDataInput()
	{
		new DelegatingDataInput(null);
	}
	
	@Test
	public void readFully() throws IOException
	{
		final byte[] b = new byte[8];
		
		mockery.checking(new Expectations() { {
			one(in).readFully(b);
		} });
		
		delegator.readFully(b);
	}
	
	@Test
	public void readFullyWithOffsetAndLength() throws IOException
	{
		final byte[] b = new byte[8];
		
		mockery.checking(new Expectations() { {
			one(in).readFully(b, 2, 4);
		} });
		
		delegator.readFully(b, 2, 4);
	}
	
	@Test
	public void skipBytes() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).skipBytes(8); will(returnValue(4));
		} });
		
		assertEquals(4, delegator.skipBytes(8));
	}
	
	@Test
	public void readBoolean() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readBoolean(); will(returnValue(true));
		} });
		
		assertTrue(delegator.readBoolean());
	}
	
	@Test
	public void readByte() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readByte(); will(returnValue((byte) 123));
		} });
		
		assertEquals((byte) 123, delegator.readByte());
	}
	
	@Test
	public void readUnsignedByte() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readUnsignedByte(); will(returnValue(123));
		} });
		
		assertEquals(123, delegator.readUnsignedByte());
	}
	
	@Test
	public void readShort() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readShort(); will(returnValue((short) 123));
		} });
		
		assertEquals((short) 123, delegator.readShort());
	}
	
	@Test
	public void readUnsignedShort() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readUnsignedShort(); will(returnValue(123));
		} });
		
		assertEquals(123, delegator.readUnsignedShort());
	}
	
	@Test
	public void readChar() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readChar(); will(returnValue('a'));
		} });
		
		assertEquals('a', delegator.readChar());
	}
	
	@Test
	public void readInt() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readInt(); will(returnValue(123));
		} });
		
		assertEquals(123, delegator.readInt());
	}
	
	@Test
	public void readLong() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readLong(); will(returnValue(123L));
		} });
		
		assertEquals(123L, delegator.readLong());
	}
	
	@Test
	public void readFloat() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readFloat(); will(returnValue(123f));
		} });
		
		assertEquals(123f, delegator.readFloat(), 0);
	}
	
	@Test
	public void readDouble() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readDouble(); will(returnValue(123d));
		} });
		
		assertEquals(123d, delegator.readDouble(), 0);
	}
	
	@Test
	public void readLine() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readLine(); will(returnValue("abc"));
		} });
		
		assertEquals("abc", delegator.readLine());
	}
	
	@Test
	public void readUTF() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(in).readUTF(); will(returnValue("abc"));
		} });
		
		assertEquals("abc", delegator.readUTF());
	}
	
	@Test
	public void getDataInput()
	{
		assertSame(in, delegator.getDataInput());
	}
}
