/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/DOMCharacterIteratorUtilsTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import static org.junit.Assert.assertEquals;

import java.text.BreakIterator;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Tests <code>DOMCharacterIteratorUtils</code>.
 * 
 * @author Mark Hobson
 * @version $Id: DOMCharacterIteratorUtilsTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
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
