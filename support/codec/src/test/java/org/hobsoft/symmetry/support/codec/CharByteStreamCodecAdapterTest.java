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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code CharByteStreamCodecAdapter}.
 * 
 * @author Mark Hobson
 * @see CharByteStreamCodecAdapter
 */
public class CharByteStreamCodecAdapterTest
{
	// fields -----------------------------------------------------------------
	
	private CharByteStreamCodecAdapter<String> adapter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		adapter = new CharByteStreamCodecAdapter<String>(new StubCharStreamCodec());
	}

	// public methods ---------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertEquals("a", IOUtils.toString(adapter.encode("a"), "UTF-8"));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(adapter.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException, IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		adapter.encodeTo("a", out);
		
		assertEquals("a", out.toString("UTF-8"));
	}
	
	@Test
	public void decode() throws DecoderException, IOException
	{
		assertEquals("a", adapter.decode(new ByteArrayInputStream("a".getBytes("UTF-8"))));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(adapter.decode(null));
	}
}
