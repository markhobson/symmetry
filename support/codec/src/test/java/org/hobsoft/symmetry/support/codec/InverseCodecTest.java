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
import org.hobsoft.symmetry.support.codec.test.ABCodec;
import org.hobsoft.symmetry.support.codec.test.B;
import org.hobsoft.symmetry.support.codec.test.BCCodec;
import org.hobsoft.symmetry.support.codec.test.C;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>InverseCodec</code>.
 * 
 * @author	Mark Hobson
 * @see		InverseCodec
 */
public class InverseCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Codec<A, B> codec;
	
	private Codec<B, A> inverseCodec;
	
	private Codec<B, A> inverseCodec2;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new ABCodec();

		inverseCodec = new InverseCodec<B, A>(codec);
		inverseCodec2 = new InverseCodec<B, A>(codec);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodecNull()
	{
		try
		{
			new InverseCodec<B, A>(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("Encoded object", new A("b"), inverseCodec.encode(new B("b")));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals("Decoded object", new B("a"), inverseCodec.decode(new A("a")));
	}
	
	@Test
	public void hashCodeTest()
	{
		assertEquals("Hash code", inverseCodec.hashCode(), inverseCodec2.hashCode());
	}
	
	@Test
	public void equals()
	{
		assertTrue("Expected equal", inverseCodec.equals(inverseCodec2));
	}
	
	@Test
	public void equalsWhenUnequal()
	{
		InverseCodec<C, B> inverseCodec3 = new InverseCodec<C, B>(new BCCodec());
		
		assertFalse("Expected unequal", inverseCodec.equals(inverseCodec3));
	}
}
