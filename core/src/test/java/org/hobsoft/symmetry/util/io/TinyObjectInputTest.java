/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/TinyObjectInputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.DataInput;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Arrays;

import org.hobsoft.symmetry.util.io.test.CustomSerializable;
import org.hobsoft.symmetry.util.io.test.CustomSubclassSerializable;
import org.hobsoft.symmetry.util.io.test.MultiFieldSerializable;
import org.hobsoft.symmetry.util.io.test.SimpleExternalizable;
import org.hobsoft.symmetry.util.io.test.SimpleSerializable;
import org.hobsoft.symmetry.util.io.test.SingletonSerializable;
import org.hobsoft.symmetry.util.io.test.SubclassSerializable;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

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
