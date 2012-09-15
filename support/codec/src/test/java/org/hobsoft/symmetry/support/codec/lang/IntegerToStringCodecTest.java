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
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code IntegerToStringCodec}.
 * 
 * @author Mark Hobson
 * @see IntegerToStringCodec
 */
public class IntegerToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private IntegerToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new IntegerToStringCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("123", codec.encode(123));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(123, (Object) codec.decode("123"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithLong() throws DecoderException
	{
		codec.decode("12345678901");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
