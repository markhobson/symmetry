/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/TinyObjectOutputTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;

import org.hobsoft.symmetry.util.io.test.CustomSerializable;
import org.hobsoft.symmetry.util.io.test.CustomSubclassSerializable;
import org.hobsoft.symmetry.util.io.test.MultiFieldSerializable;
import org.hobsoft.symmetry.util.io.test.SimpleExternalizable;
import org.hobsoft.symmetry.util.io.test.SimpleSerializable;
import org.hobsoft.symmetry.util.io.test.SubclassSerializable;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code TinyObjectOutput}.
 * 
 * @author Mark Hobson
 * @see TinyObjectOutput
 */
public class TinyObjectOutputTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private DataOutput dataOut;
	
	private TinyObjectOutput out;
	
	private Sequence sequence;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		dataOut = mockery.mock(DataOutput.class);
		
		out = new TinyObjectOutput(dataOut);
		
		sequence = mockery.sequence("sequence");
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void testWriteObjectString() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(dataOut).writeUTF("abc");
		} });
		
		out.writeObject("abc");
	}
	
	@Test
	public void testWriteObjectIntArray() throws IOException
	{
		mockery.checking(new Expectations() { {
			one(dataOut).writeInt(3); inSequence(sequence);
			one(dataOut).writeInt(1); inSequence(sequence);
			one(dataOut).writeInt(2); inSequence(sequence);
			one(dataOut).writeInt(3); inSequence(sequence);
		} });
		
		out.writeObject(new int[] {1, 2, 3});
	}
	
	@Test
	public void testWriteObjectSerializable() throws IOException
	{
		Serializable serializable = new SimpleSerializable("abc");
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeUTF("abc");
		} });
		
		out.writeObject(serializable);
	}
	
	@Test
	public void testWriteObjectSerializableMultipleFields() throws IOException
	{
		Serializable serializable = new MultiFieldSerializable("abc", 123, 456.0);
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeInt(123); inSequence(sequence);
			one(dataOut).writeDouble(456.0); inSequence(sequence);
			one(dataOut).writeUTF("abc"); inSequence(sequence);
		} });
		
		out.writeObject(serializable);
	}
	
	@Test
	public void testWriteObjectSerializableSubclass() throws IOException
	{
		Serializable serializable = new SubclassSerializable("abc", 123);
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeUTF("abc"); inSequence(sequence);
			one(dataOut).writeInt(123); inSequence(sequence);
		} });
		
		out.writeObject(serializable);
	}
	
	@Test
	public void testWriteObjectSerializableWithWriteObject() throws IOException
	{
		Serializable serializable = new CustomSerializable("abc");
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeInt(3); inSequence(sequence);
			one(dataOut).writeChar('a'); inSequence(sequence);
			one(dataOut).writeChar('b'); inSequence(sequence);
			one(dataOut).writeChar('c'); inSequence(sequence);
		} });
		
		out.writeObject(serializable);
	}
	
	@Test
	public void testWriteObjectSerializableSubclassWithWriteObject() throws IOException
	{
		Serializable serializable = new CustomSubclassSerializable("abc", 123);
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeInt(3); inSequence(sequence);
			one(dataOut).writeChar('a'); inSequence(sequence);
			one(dataOut).writeChar('b'); inSequence(sequence);
			one(dataOut).writeChar('c'); inSequence(sequence);
			one(dataOut).writeInt(12); inSequence(sequence);
			one(dataOut).writeInt(3); inSequence(sequence);
		} });
		
		out.writeObject(serializable);
	}
	
	// TODO: test writeReplace
	
	@Test
	public void testWriteObjectExternalizable() throws IOException
	{
		Externalizable externalizable = new SimpleExternalizable("abc");
		
		mockery.checking(new Expectations() { {
			one(dataOut).writeUTF("abc");
		} });
		
		out.writeObject(externalizable);
	}
	
	@Test(expected = NotSerializableException.class)
	public void testWriteObjectNotSerializable() throws IOException
	{
		Object object = new Object();
		
		out.writeObject(object);
	}
}
