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
package org.hobsoft.symmetry.util.io;

import java.io.DataOutput;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Tests {@code DelegatingDataOutput}.
 * 
 * @author Mark Hobson
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
