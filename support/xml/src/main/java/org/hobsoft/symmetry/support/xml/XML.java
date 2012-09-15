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
package org.hobsoft.symmetry.support.xml;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class XML
{
	// constructors -----------------------------------------------------------
	
	private XML()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Determines whether the specified character is a valid XML character.
	 * 
	 * @param c
	 *            the character
	 * @return {@code true} if the specified character is a valid XML character
	 * @see <a href="http://www.w3.org/TR/2006/REC-xml-20060816/#charsets">Extensible Markup Language (XML) 1.0 (Fourth
	 *      Edition)</a>
	 */
	public static boolean isValidChar(char c)
	{
		if (c == 0x9 || c == 0xA || c == 0xD)
		{
			return true;
		}
		
		if (c >= 0x20 && c <= 0xD7FF)
		{
			return true;
		}
		
		if (c >= 0xE000 && c <= 0xFFFD)
		{
			return true;
		}
		
		if (c >= 0x10000 && c <= 0x10FFFF)
		{
			return true;
		}
		
		return false;
	}
}
