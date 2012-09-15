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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code ByteArrayToStreamCodec}.
 * 
 * @author Mark Hobson
 * @see ByteArrayToStreamCodec
 */
public class ByteArrayToStreamCodecTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayToStreamCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new ByteArrayToStreamCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertArrayEquals(new byte[] {1, 2, 3}, IOUtils.toByteArray(codec.encode(new byte[] {1, 2, 3})));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		codec.encodeTo(new byte[] {1, 2, 3}, out);
		
		assertArrayEquals(new byte[] {1, 2, 3}, out.toByteArray());
	}

	@Test
	public void encodeToWithNull() throws EncoderException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		codec.encodeTo(null, out);
		
		assertArrayEquals(new byte[0], out.toByteArray());
	}
	
	@Test
	public void decode() throws DecoderException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(new byte[] {1, 2, 3});
		
		assertArrayEquals(new byte[] {1, 2, 3}, codec.decode(in));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
