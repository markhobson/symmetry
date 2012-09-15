/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/ObjectInputStreamAdapterTest.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.io.IOException;
import java.io.ObjectInput;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Tests {@code ObjectInputStreamAdapter}.
 * 
 * @author Mark Hobson
 * @see ObjectInputStreamAdapter
 */
public class ObjectInputStreamAdapterTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private ObjectInput in;
	
	private ObjectInputStreamAdapter adapter;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp() throws IOException
	{
		in = mockery.mock(ObjectInput.class);
		
		adapter = new ObjectInputStreamAdapter(in);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNullObjectInput() throws IOException
	{
		new ObjectInputStreamAdapter(null);
	}
	
	// TODO: test other methods
	
	@Test
	public void readObject() throws IOException, ClassNotFoundException
	{
		final Object object = new Object();
		
		mockery.checking(new Expectations() { {
			one(in).readObject(); will(returnValue(object));
		} });
		
		assertSame(object, adapter.readObject());
	}
}
