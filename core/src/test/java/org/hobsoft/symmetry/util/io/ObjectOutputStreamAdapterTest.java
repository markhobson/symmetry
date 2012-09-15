/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/ObjectOutputStreamAdapterTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectOutput;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code ObjectOutputStreamAdapter}.
 * 
 * @author Mark Hobson
 * @see ObjectOutputStreamAdapter
 */
public class ObjectOutputStreamAdapterTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private ObjectOutput out;
	
	private ObjectOutputStreamAdapter adapter;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp() throws IOException
	{
		out = mockery.mock(ObjectOutput.class);
		
		adapter = new ObjectOutputStreamAdapter(out);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullObjectOutput() throws IOException
	{
		new ObjectOutputStreamAdapter(null);
	}
	
	// TODO: test other methods
	
	@Test
	public void writeObject() throws IOException
	{
		final Object object = new Object();
		
		mockery.checking(new Expectations() { {
			one(out).writeObject(object);
		} });
		
		adapter.writeObject(object);
	}
}
