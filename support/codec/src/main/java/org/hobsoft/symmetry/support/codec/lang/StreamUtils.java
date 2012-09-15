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

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Provides utility methods for working with streams.
 * 
 * @author Mark Hobson
 */
final class StreamUtils
{
	// constants --------------------------------------------------------------
	
	private static final int BUFFER_SIZE = 1024 * 4;

	// constructors -----------------------------------------------------------
	
	private StreamUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static byte[] toBytes(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int n;
		
		while ((n = in.read(buffer)) != -1)
		{
			out.write(buffer, 0, n);
		}
		
		return out.toByteArray();
	}
	
	public static char[] toChars(Reader reader) throws IOException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		char[] buffer = new char[BUFFER_SIZE];
		int n;
		
		while ((n = reader.read(buffer)) != -1)
		{
			writer.write(buffer, 0, n);
		}
		
		return writer.toCharArray();
	}
}
