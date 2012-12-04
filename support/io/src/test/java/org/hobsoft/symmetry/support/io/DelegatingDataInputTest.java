/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.io;

import java.io.DataInput;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DelegatingDataInput}.
 * 
 * @author Mark Hobson
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
