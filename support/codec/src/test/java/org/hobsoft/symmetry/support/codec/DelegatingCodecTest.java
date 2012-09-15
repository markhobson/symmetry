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
 * Tests <code>DelegatingCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: DelegatingCodecTest.java 75367 2010-07-23 17:59:07Z mark@IIZUKA.CO.UK $
 * @see		DelegatingCodec
 */
@RunWith(JMock.class)
public class DelegatingCodecTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery context = new JUnit4Mockery();

	private Codec<A, B> mockCodec;

	private DelegatingCodec<A, B> codec;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: can we avoid this cast warning without @SuppressWarnings
		@SuppressWarnings("unchecked")
		Codec<A, B> uncheckedMockCodec = context.mock(Codec.class);
		
		mockCodec = uncheckedMockCodec;
		
		codec = new DelegatingCodec<A, B>(mockCodec);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNull()
	{
		new DelegatingCodec<A, B>(null);
	}
	
	@Test
	public void encode() throws EncoderException
	{
		final A a = new A("a");
		
		context.checking(new Expectations() { {
			one(mockCodec).encode(a);
		} });
		
		codec.encode(a);
	}
	
	@Test
	public void decode() throws DecoderException
	{
		final B b = new B("b");
		
		context.checking(new Expectations() { {
			one(mockCodec).decode(b);
		} });
		
		codec.decode(b);
	}
	
	@Test
	public void getCodec()
	{
		assertEquals(mockCodec, codec.getCodec());
	}
}
