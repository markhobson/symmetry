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

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code StringToBytesCodec}.
 * 
 * @author Mark Hobson
 * @see StringToCharStreamCodec
 */
public class StringToCharStreamCodecTest
{
	// constants --------------------------------------------------------------
	
	private static final String STRING = "Hello World";
	
	private static final char[] CHARS = new char[] {
		'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'
	};
	
	// fields -----------------------------------------------------------------
	
	private StringToCharStreamCodec codec;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		codec = new StringToCharStreamCodec();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		char[] actual = IOUtils.toCharArray(codec.encode(STRING));
		
		assertTrue(Arrays.equals(CHARS, actual));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(STRING, codec.decode(new CharArrayReader(CHARS)));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
