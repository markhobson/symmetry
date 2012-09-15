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
package org.hobsoft.symmetry.support.xml.dom;

import java.text.BreakIterator;
import java.text.CharacterIterator;

import org.w3c.dom.Node;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class DOMCharacterIteratorUtils
{
	// TODO: This class is not really DOM specific, it's only the setIndex(Node, int) method call that constrains it
	// with working with DOMCharacterIterators.  Need to workaround that and turn this into a generic BreakIteratorUtils
	// class.
	
	// constructors -----------------------------------------------------------
	
	private DOMCharacterIteratorUtils()
	{
		// private constructor for utility class
	}

	// public methods ---------------------------------------------------------
	
	public static int wordStart(DOMCharacterIterator characterIterator, Node container, int offset)
	{
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(characterIterator);
		
		characterIterator.setIndex(container, offset);
		
		int end = characterIterator.getEndIndex();
		
		if (end == 0)
		{
			return BreakIterator.DONE;
		}

		int index = Math.min(characterIterator.getIndex(), end - 1);
			
		breakIterator.following(index);
		return breakIterator.previous();
	}
	
	public static int wordEnd(DOMCharacterIterator characterIterator, Node container, int offset)
	{
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(characterIterator);
		
		characterIterator.setIndex(container, offset);
		
		int end = characterIterator.getEndIndex();
		
		if (end == 0)
		{
			return BreakIterator.DONE;
		}

		int index = Math.min(characterIterator.getIndex(), end - 1);
			
		return breakIterator.following(index);
	}
	
	public static int previousWord(DOMCharacterIterator characterIterator, Node container, int offset)
	{
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(characterIterator);

		characterIterator.setIndex(container, offset);

		int last = breakIterator.current();
		int current = breakIterator.previous();

		while (current != BreakIterator.DONE)
		{
			if (!isWhitespace(characterIterator, current, last))
			{
				characterIterator.setIndex(current);
				return current;
			}
			
			last = current;
			current = breakIterator.previous();
		}
		
		return BreakIterator.DONE;
	}
	
	public static int nextWord(DOMCharacterIterator characterIterator, Node container, int offset)
	{
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(characterIterator);

		characterIterator.setIndex(container, offset);
		
		int end = characterIterator.getEndIndex();
		int index = characterIterator.getIndex();
		
		if (index == end)
		{
			return BreakIterator.DONE;
		}
		
		int last = breakIterator.following(index);
		int current = breakIterator.next();

		while (current != BreakIterator.DONE)
		{
			if (!isWhitespace(characterIterator, last, current))
			{
				characterIterator.setIndex(last);
				return last;
			}
			
			last = current;
			current = breakIterator.next();
		}
		
		return last;
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean isWhitespace(CharacterIterator iterator, int start, int end)
	{
		char c = iterator.setIndex(start);
		
		for (int i = start; i < end; i++)
		{
			if (!Character.isWhitespace(c))
			{
				return false;
			}
			
			c = iterator.next();
		}
		
		return true;
	}
}
