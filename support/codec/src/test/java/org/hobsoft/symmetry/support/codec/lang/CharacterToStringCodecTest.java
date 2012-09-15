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
 * Tests {@code CharacterToStringCodec}.
 * 
 * @author Mark Hobson
 * @see CharacterToStringCodec
 */
public class CharacterToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private CharacterToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new CharacterToStringCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("a", codec.encode('a'));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals('a', (Object) codec.decode("a"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithEmptyString() throws DecoderException
	{
		codec.decode("");
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithMultipleCharacters() throws DecoderException
	{
		codec.decode("ab");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
