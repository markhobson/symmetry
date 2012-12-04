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
package org.hobsoft.symmetry.support.io;

import java.util.Arrays;

import junit.framework.Assert;

/**
 * Provides assertion methods for binary data.
 * 
 * @author Mark Hobson
 */
public final class BinaryAssert
{
	// constructors -----------------------------------------------------------
	
	private BinaryAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void assertBytes(int[] expected, byte[] actual)
	{
		byte[] bytes = new byte[expected.length];
		
		for (int i = 0; i < expected.length; i++)
		{
			bytes[i] = (byte) expected[i];
		}
		
		if (!Arrays.equals(bytes, actual))
		{
			Assert.fail("expected:<" + toBinaryString(bytes) + "> but was:<" + toBinaryString(actual) + ">");
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static String toBinaryString(byte[] bytes)
	{
		StringBuilder builder = new StringBuilder();
		
		for (byte b : bytes)
		{
			if (builder.length() > 0)
			{
				builder.append(" ");
			}
			
			builder.append(toBinaryString(b));
		}
		
		return builder.toString();
	}
	
	private static String toBinaryString(byte b)
	{
		char[] chars = new char[8];
		
		for (int i = 0; i < 8; i++)
		{
			int bit = b & (1 << (7 - i));
			
			chars[i] = (bit == 0) ? '0' : '1';
		}
		
		return new String(chars);
	}
}
