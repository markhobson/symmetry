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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code BooleanToStringCodec}.
 * 
 * @author Mark Hobson
 * @see BooleanToStringCodec
 */
public class BooleanToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private BooleanToStringCodec lenientCodec;
	
	private BooleanToStringCodec strictCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		lenientCodec = new BooleanToStringCodec(true);
		strictCodec = new BooleanToStringCodec(false);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeWithTrue() throws EncoderException
	{
		assertEquals("true", lenientCodec.encode(true));
		assertEquals("true", strictCodec.encode(true));
	}
	
	@Test
	public void encodeWithFalse() throws EncoderException
	{
		assertEquals("false", lenientCodec.encode(false));
		assertEquals("false", strictCodec.encode(false));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", lenientCodec.encode(null));
		assertEquals("null", strictCodec.encode(null));
	}
	
	@Test
	public void decodeWithTrue() throws DecoderException
	{
		assertTrue(lenientCodec.decode("true"));
		assertTrue(strictCodec.decode("true"));
	}
	
	@Test
	public void decodeWithTrueMixedCase() throws DecoderException
	{
		assertTrue(lenientCodec.decode("TrUe"));
		assertTrue(strictCodec.decode("TrUe"));
	}
	
	@Test
	public void decodeWithFalse() throws DecoderException
	{
		assertFalse(lenientCodec.decode("false"));
		assertFalse(strictCodec.decode("false"));
	}
	
	@Test
	public void decodeWithFalseMixedCase() throws DecoderException
	{
		assertFalse(lenientCodec.decode("FaLsE"));
		assertFalse(strictCodec.decode("FaLsE"));
	}
	
	@Test
	public void decodeWithOtherWhenLenient() throws DecoderException
	{
		assertFalse(lenientCodec.decode("not a boolean"));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithOtherWhenStrict() throws DecoderException
	{
		strictCodec.decode("not a boolean");
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(lenientCodec.decode(null));
		assertNull(strictCodec.decode(null));
	}
}
