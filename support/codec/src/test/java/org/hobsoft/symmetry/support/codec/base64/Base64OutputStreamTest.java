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
package org.hobsoft.symmetry.support.codec.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>Base64OutputStream</code>.
 * 
 * @author	Mark Hobson
 * @see		Base64OutputStream
 */
public class Base64OutputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream byteOut;
	
	private Base64OutputStream out;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		byteOut = new ByteArrayOutputStream();
		
		out = new Base64OutputStream(byteOut);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void writeBytes() throws IOException
	{
		out.write("Hello World".getBytes());
		out.close();
		
		assertOut("SGVsbG8gV29ybGQ=");
	}
	
	// private methods --------------------------------------------------------
	
	private void assertOut(String expected)
	{
		String actual = new String(byteOut.toByteArray());
		
		assertEquals("Output", expected, actual);
	}
}
