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

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code AbstractCharStreamEncoder}.
 * 
 * @author Mark Hobson
 * @see AbstractCharStreamEncoder
 */
public class AbstractCharStreamEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private StubCharStreamEncoder encoder;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		encoder = new StubCharStreamEncoder();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertEquals("a", IOUtils.toString(encoder.encode("a")));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(encoder.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		StringWriter writer = new StringWriter();
		
		encoder.encodeTo("a", writer);
		
		assertEquals("a", writer.toString());
	}
}
