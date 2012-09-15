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
package org.hobsoft.symmetry.support.xml.dtd;

import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author Mark Hobson
 */
class StringScanner
{
	// fields -----------------------------------------------------------------
	
	private final String string;
	
	private final String delimiters;
	
	private final boolean ignoreWhitespace;
	
	private final int length;
	
	private int index;
	
	// constructors -----------------------------------------------------------
	
	public StringScanner(String string, String delimiters)
	{
		this(string, delimiters, false);
	}
	
	public StringScanner(String string, String delimiters, boolean ignoreWhitespace)
	{
		this.string = string;
		this.delimiters = delimiters;
		this.ignoreWhitespace = ignoreWhitespace;
		
		length = string.length();
		index = 0;
		
		if (ignoreWhitespace)
		{
			skipWhitespace();
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public int getIndex()
	{
		return index;
	}
	
	public boolean hasNext()
	{
		return index < length;
	}
	
	public boolean hasNext(String token)
	{
		int oldIndex = index;
		boolean hasNext = false;
		
		try
		{
			hasNext = next().equals(token);
		}
		catch (NoSuchElementException exception)
		{
			// proceed
		}
		
		index = oldIndex;
		
		return hasNext;
	}
	
	public String next()
	{
		if (index >= length)
		{
			throw new NoSuchElementException();
		}

		int start = index;
		int end = start + 1;
		
		if (delimiters.indexOf(string.charAt(start)) == -1)
		{
			while (end < length && delimiters.indexOf(string.charAt(end)) == -1)
			{
				end++;
			}
		}
		
		index = end;
		
		if (ignoreWhitespace)
		{
			while (end - 1 > start && Character.isWhitespace(string.charAt(end - 1)))
			{
				end--;
			}
			
			skipWhitespace();
		}
		
		return string.substring(start, end);
	}
	
	// private methods --------------------------------------------------------
	
	private void skipWhitespace()
	{
		while (index < length && Character.isWhitespace(string.charAt(index)))
		{
			index++;
		}
	}
}
