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
import java.io.NotSerializableException;
import java.util.Arrays;

import org.hobsoft.symmetry.support.io.test.CustomSerializable;
import org.hobsoft.symmetry.support.io.test.CustomSubclassSerializable;
import org.hobsoft.symmetry.support.io.test.MultiFieldSerializable;
import org.hobsoft.symmetry.support.io.test.SimpleExternalizable;
import org.hobsoft.symmetry.support.io.test.SimpleSerializable;
import org.hobsoft.symmetry.support.io.test.SingletonSerializable;
import org.hobsoft.symmetry.support.io.test.SubclassSerializable;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code TinyObjectInput}.
 * 
 * @author Mark Hobson
 * @see TinyObjectInput
 */
public class TinyObjectInputTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private DataInput dataIn;
	
	private TinyObjectInput in;
	
	private Sequence sequence;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		dataIn = mockery.mock(DataInput.class);
		
		in = new TinyObjectInput(dataIn);
		
		sequence = mockery.sequence("sequence");
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void readObjectWithClassString() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readUTF(); will(returnValue("abc"));
		} });
		
		assertEquals("abc", in.readObject(String.class));
	}
	
	@Test
	public void readObjectWithClassIntArray() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(3));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(1));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(2));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(3));
		} });
		
		assertTrue(Arrays.equals(new int[] {1, 2, 3}, (int[]) in.readObject(int[].class)));
	}
	
	@Test
	public void readObjectWithClassSerializable() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readUTF(); will(returnValue("abc"));
		} });
		
		assertEquals(new SimpleSerializable("abc"), in.readObject(SimpleSerializable.class));
	}
	
	@Test
	public void readObjectWithClassSerializableMultipleFields() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(123));
			one(dataIn).readDouble(); inSequence(sequence); will(returnValue(456.0));
			one(dataIn).readUTF(); inSequence(sequence); will(returnValue("abc"));
		} });
		
		assertEquals(new MultiFieldSerializable("abc", 123, 456.0), in.readObject(MultiFieldSerializable.class));
	}
	
	@Test
	public void readObjectWithClassSerializableSubclass() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readUTF(); inSequence(sequence); will(returnValue("abc"));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(123));
		} });
		
		assertEquals(new SubclassSerializable("abc", 123), in.readObject(SubclassSerializable.class));
	}
	
	@Test
	public void readObjectWithClassSerializableWithReadObject() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(3));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('a'));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('b'));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('c'));
		} });
		
		assertEquals(new CustomSerializable("abc"), in.readObject(CustomSerializable.class));
	}
	
	@Test
	public void readObjectWithClassSerializableSubclassWithReadObject() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(3));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('a'));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('b'));
			one(dataIn).readChar(); inSequence(sequence); will(returnValue('c'));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(12));
			one(dataIn).readInt(); inSequence(sequence); will(returnValue(3));
		} });
		
		assertEquals(new CustomSubclassSerializable("abc", 123), in.readObject(CustomSubclassSerializable.class));
	}
	
	@Test
	public void readObjectWithClassSerializableWithReadResolve() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readUTF(); will(returnValue("abc"));
		} });
		
		assertEquals(SingletonSerializable.getInstance("abc"), in.readObject(SingletonSerializable.class));
	}
	
	@Test
	public void readObjectWithClassExternalizable() throws IOException, ClassNotFoundException
	{
		mockery.checking(new Expectations() { {
			one(dataIn).readUTF(); will(returnValue("abc"));
		} });
		
		assertEquals(new SimpleExternalizable("abc"), in.readObject(SimpleExternalizable.class));
	}
	
	@Test(expected = NotSerializableException.class)
	public void readObjectWithClassNotSerializable() throws IOException, ClassNotFoundException
	{
		in.readObject(Object.class);
	}
}
