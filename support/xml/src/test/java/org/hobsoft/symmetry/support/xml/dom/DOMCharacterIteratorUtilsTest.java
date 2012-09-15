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

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>DOMCharacterIteratorUtils</code>.
 * 
 * @author Mark Hobson
 * @see DOMCharacterIteratorUtils
 */
public class DOMCharacterIteratorUtilsTest extends AbstractDOMTestCase
{
	// nextWord tests ---------------------------------------------------------
	
	@Test
	public void nextWordAtStartOfWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(2, DOMCharacterIteratorUtils.nextWord(iterator, text, 0));
		assertDOMCharacterIterator(text, 2, iterator);
	}
	
	@Test
	public void nextWordAtStartOfLastWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(3, DOMCharacterIteratorUtils.nextWord(iterator, text, 2));
		assertDOMCharacterIterator(text, 3, iterator);
	}
	
	@Test
	public void nextWordAtStartOfWordWithPunctuation()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a. b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(1, DOMCharacterIteratorUtils.nextWord(iterator, text, 0));
		assertDOMCharacterIterator(text, 1, iterator);
	}
	
	@Test
	public void nextWordAtEndOfWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(2, DOMCharacterIteratorUtils.nextWord(iterator, text, 1));
		assertDOMCharacterIterator(text, 2, iterator);
	}
	
	@Test
	public void nextWordAtEndOfText()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(BreakIterator.DONE, DOMCharacterIteratorUtils.nextWord(iterator, text, 1));
		assertDOMCharacterIterator(text, 1, iterator);
	}
	
	// previousWord tests -----------------------------------------------------
	
	@Test
	public void previousWordAtStartOfWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(0, DOMCharacterIteratorUtils.previousWord(iterator, text, 2));
		assertDOMCharacterIterator(text, 0, iterator);
	}
	
	@Test
	public void previousWordAtStartOfWordWithPunctuation()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a. b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(1, DOMCharacterIteratorUtils.previousWord(iterator, text, 3));
		assertDOMCharacterIterator(text, 1, iterator);
	}
	
	@Test
	public void previousWordAtEndOfWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(2, DOMCharacterIteratorUtils.previousWord(iterator, text, 3));
		assertDOMCharacterIterator(text, 2, iterator);
	}
	
	@Test
	public void previousWordAtEndOfFirstWord()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a b");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(0, DOMCharacterIteratorUtils.previousWord(iterator, text, 1));
		assertDOMCharacterIterator(text, 0, iterator);
	}
	
	@Test
	public void previousWordAtStartOfText()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("a");
		root.appendChild(text);
		getDocument().appendChild(root);

		DOMCharacterIterator iterator = new DOMCharacterIterator(root);
		assertEquals(BreakIterator.DONE, DOMCharacterIteratorUtils.previousWord(iterator, text, 0));
		assertDOMCharacterIterator(text, 0, iterator);
	}
	
	// private methods ------------------------------------------------------
	
	private void assertDOMCharacterIterator(Node expectedContainer, int expectedOffset, DOMCharacterIterator actual)
	{
		RangeAssert.assertBoundaryPoint("Index", expectedContainer, expectedOffset, actual.getNode(),
			actual.getOffset());
	}
}
