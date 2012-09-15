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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code NullCodec}.
 * 
 * @author Mark Hobson
 * @see NullCodec
 */
public class NullCodecTest
{
	// fields -----------------------------------------------------------------
	
	private NullCodec<Object, Object> codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = NullCodec.get();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void get()
	{
		assertEquals(codec, NullCodec.get());
	}
	
	@Test
	public void getReturnsSameInstance()
	{
		assertSame(codec, NullCodec.get());
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertNull(codec.encode(new Object()));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertNull(codec.decode(new Object()));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
	
	// TODO: hashCode
	// TODO: equals
}
