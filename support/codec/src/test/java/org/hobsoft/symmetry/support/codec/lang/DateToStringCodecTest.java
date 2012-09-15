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

import java.util.Calendar;
import java.util.Date;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code DateToStringCodec}.
 * 
 * @author Mark Hobson
 * @see DateToStringCodec
 */
public class DateToStringCodecTest
{
	// fields -----------------------------------------------------------------
	
	private DateToStringCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new DateToStringCodec("dd-MM-yyyy");
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("01-02-2003", codec.encode(createDate(1, 2, 2003)));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertEquals("null", codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(createDate(1, 2, 2003), codec.decode("01-02-2003"));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
	
	// private methods --------------------------------------------------------
	
	private static Date createDate(int day, int month, int year)
	{
		Calendar calendar = Calendar.getInstance();
	
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
}
