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
import java.io.CharArrayWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code CharArrayToStreamCodec}.
 * 
 * @author Mark Hobson
 * @see CharArrayToStreamCodec
 */
public class CharArrayToStreamCodecTest
{
	// fields -----------------------------------------------------------------
	
	private CharArrayToStreamCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new CharArrayToStreamCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertArrayEquals(new char[] {'a', 'b', 'c'}, IOUtils.toCharArray(codec.encode(new char[] {'a', 'b', 'c'})));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		codec.encodeTo(new char[] {'a', 'b', 'c'}, writer);
		
		assertArrayEquals(new char[] {'a', 'b', 'c'}, writer.toCharArray());
	}
	
	@Test
	public void encodeToWithNull() throws EncoderException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		codec.encodeTo(null, writer);
		
		assertArrayEquals(new char[0], writer.toCharArray());
	}
	
	@Test
	public void decode() throws DecoderException
	{
		CharArrayReader reader = new CharArrayReader(new char[] {'a', 'b', 'c'});
		
		assertArrayEquals(new char[] {'a', 'b', 'c'}, codec.decode(reader));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
