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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>Base64InputStream</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64InputStreamTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		Base64InputStream
 */
public class Base64InputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private Base64InputStream in;

	// tests ------------------------------------------------------------------
	
	@Test
	public void writeBytes() throws IOException
	{
		in = createBase64InputStream("SGVsbG8gV29ybGQ=");
		
		assertEquals("Hello World", toString(in));
	}
	
	// private methods --------------------------------------------------------
	
	private Base64InputStream createBase64InputStream(String string)
	{
		return new Base64InputStream(new ByteArrayInputStream(string.getBytes()));
	}
	
	private String toString(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		int b;
		while ((b = in.read()) != -1)
		{
			out.write(b);
		}
		
		out.close();
		
		return out.toString();
	}
}
