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
 * Tests <code>CompoundCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: CompoundCodecTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		CompoundCodec
 */
public class CompoundCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Codec<A, B> codec1;
	
	private Codec<B, C> codec2;
	
	private Codec<A, C> compoundCodec;
	
	private Codec<A, C> compoundCodec2;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		codec1 = new ABCodec();
		codec2 = new BCCodec();
		
		compoundCodec = new CompoundCodec<A, B, C>(codec1, codec2);
		compoundCodec2 = new CompoundCodec<A, B, C>(codec1, codec2);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodec1Null()
	{
		try
		{
			new CompoundCodec<A, B, C>(null, codec2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec1 cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodec2Null()
	{
		try
		{
			new CompoundCodec<A, B, C>(codec1, null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec2 cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals(new C("a"), compoundCodec.encode(new A("a")));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(new A("c"), compoundCodec.decode(new C("c")));
	}
	
	@Test
	public void hashCodeTest()
	{
		assertEquals("Hash code", compoundCodec.hashCode(), compoundCodec2.hashCode());
	}
	
	@Test
	public void equals()
	{
		assertTrue("Expected equal", compoundCodec.equals(compoundCodec2));
	}
	
	@Test
	public void equalsWhenUnequal()
	{
		Codec<B, A> codec3 = new InverseCodec<B, A>(codec1);
		CompoundCodec<A, B, A> compoundCodec3 = new CompoundCodec<A, B, A>(codec1, codec3);
		
		assertFalse("Expected unequal", compoundCodec.equals(compoundCodec3));
	}
}
