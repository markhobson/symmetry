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
package org.hobsoft.symmetry.support.codec;

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.B;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code DelegatingDecoder}.
 * 
 * @author Mark Hobson
 * @see DelegatingDecoder
 */
@RunWith(JMock.class)
public class DelegatingDecoderTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery context = new JUnit4Mockery();

	private Decoder<A, B> mockDecoder;

	private DelegatingDecoder<A, B> decoder;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: can we avoid this cast warning without @SuppressWarnings
		@SuppressWarnings("unchecked")
		Decoder<A, B> uncheckedMockDecoder = context.mock(Decoder.class);
		
		mockDecoder = uncheckedMockDecoder;
		
		decoder = new DelegatingDecoder<A, B>(mockDecoder);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNull()
	{
		new DelegatingDecoder<A, B>(null);
	}
	
	@Test
	public void decode() throws DecoderException
	{
		final A a = new A("a");
		
		context.checking(new Expectations() { {
			one(mockDecoder).decode(a);
		} });
		
		decoder.decode(a);
	}
	
	@Test
	public void getDecoder()
	{
		assertEquals(mockDecoder, decoder.getDecoder());
	}
}
