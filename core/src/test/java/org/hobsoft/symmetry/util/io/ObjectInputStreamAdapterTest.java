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
