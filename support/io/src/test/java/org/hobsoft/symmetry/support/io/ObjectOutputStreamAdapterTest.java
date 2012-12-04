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
