/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/DOMCharacterIteratorTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import static org.junit.Assert.assertEquals;

import java.text.CharacterIterator;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Tests <code>DOMCharacterIterator</code>.
 * 
 * @author Mark Hobson
 * @see DOMCharacterIterator
 */
public class DOMCharacterIteratorTest extends AbstractDOMTestCase
{
	// first tests ------------------------------------------------------------
	
	@Test
	public void first()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('a', iterator.first());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void firstWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.first());
		assertEquals(0, iterator.getIndex());
	}
	
	// last tests -------------------------------------------------------------
	
	@Test
	public void last()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.last());
		assertEquals(1, iterator.getIndex());
	}
	
	@Test
	public void lastWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.last());
		assertEquals(0, iterator.getIndex());
	}
	
	// current tests ----------------------------------------------------------
	
	@Test
	public void current()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('a', iterator.current());
	}
	
	@Test
	public void currentWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.current());
	}
	
	// next tests -------------------------------------------------------------
	
	@Test
	public void next()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.next());
	}
	
	@Test
	public void nextAtEnd()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.next());
		assertEquals(1, iterator.getIndex());
	}
	
	@Test
	public void nextWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.next());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void nextAtTextBoundary()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.next());
	}
	
	@Test
	public void nextAtEmptyTextBoundary()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode(""));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.next());
	}
	
	@Test
	public void nextAtElementBoundary()
	{
		Element element = getDocument().createElement("element");
		Element element1 = getDocument().createElement("element");
		element1.appendChild(getDocument().createTextNode("a"));
		Element element2 = getDocument().createElement("element");
		element2.appendChild(getDocument().createTextNode("b"));
		element.appendChild(element1);
		element.appendChild(element2);
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.next());
	}
	
	// previous tests ---------------------------------------------------------
	
	@Test
	public void previous()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals('a', iterator.previous());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void previousAtBegin()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.previous());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void previousWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals(CharacterIterator.DONE, iterator.previous());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void previousAtTextBoundary()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals('a', iterator.previous());
	}
	
	@Test
	public void previousAtEmptyTextBoundary()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode(""));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals('a', iterator.previous());
	}
	
	@Test
	public void previousAtElementBoundary()
	{
		Element element = getDocument().createElement("element");
		Element element1 = getDocument().createElement("element");
		element1.appendChild(getDocument().createTextNode("a"));
		Element element2 = getDocument().createElement("element");
		element2.appendChild(getDocument().createTextNode("b"));
		element.appendChild(element1);
		element.appendChild(element2);
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals('a', iterator.previous());
	}
	
	// setIndex tests ---------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void setIndexToBeforeBegin()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		
		try
		{
			iterator.setIndex(-1);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("position: -1 < 0", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void setIndexToBegin()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('a', iterator.setIndex(0));
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void setIndexToEnd()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.setIndex(3));
		assertEquals(3, iterator.getIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setIndexToAfterEnd()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		
		try
		{
			iterator.setIndex(2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("position: 2 > 1", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void setIndexToMiddle()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.setIndex(1));
		assertEquals(1, iterator.getIndex());
	}
	
	@Test
	public void setIndexWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.setIndex(0));
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void setIndexToStartWithMultipleTextNodes()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('b', iterator.setIndex(1));
		assertEquals(1, iterator.getIndex());
	}
	
	@Test
	public void setIndexToEndWithMultipleTextNodes()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("bc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('c', iterator.setIndex(2));
		assertEquals(2, iterator.getIndex());
	}
	
	@Test
	public void setIndexToMiddleWithMultipleTextNodes()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("bcd"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('c', iterator.setIndex(2));
		assertEquals(2, iterator.getIndex());
	}
	
	@Test
	public void setIndexBoundaryPointWithEmpty()
	{
		Element element = getDocument().createElement("element");
		Text text = (Text) element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.setIndex(text, 0);
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void setIndexBoundaryPointToBegin()
	{
		Element element = getDocument().createElement("element");
		Text text = (Text) element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals('a', iterator.setIndex(text, 0));
		assertEquals(text, iterator.getNode());
		assertEquals(0, iterator.getOffset());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void setIndexBoundaryPointToEnd()
	{
		Element element = getDocument().createElement("element");
		Text text = (Text) element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(CharacterIterator.DONE, iterator.setIndex(text, 3));
		assertEquals(text, iterator.getNode());
		assertEquals(3, iterator.getOffset());
		assertEquals(3, iterator.getIndex());
	}
	
	// TODO: finish setIndexBoundaryPoint tests
	
	// getBeginIndex tests ----------------------------------------------------
	
	@Test
	public void getBeginIndex()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(0, iterator.getBeginIndex());
	}
	
	@Test
	public void getBeginIndexWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(0, iterator.getBeginIndex());
	}
	
	@Test
	public void getBeginIndexPreservePosition()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		element.appendChild(getDocument().createTextNode("cd"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals(3, iterator.getIndex());
		iterator.getBeginIndex();
		assertEquals(3, iterator.getIndex());
	}
	
	// getEndIndex tests ------------------------------------------------------
	
	@Test
	public void getEndIndexWithChar()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(1, iterator.getEndIndex());
	}
	
	@Test
	public void getEndIndexWithString()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(3, iterator.getEndIndex());
	}
	
	@Test
	public void getEndIndexWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(0, iterator.getEndIndex());
	}
	
	@Test
	public void getEndIndexWithMultipleTextNodes()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(2, iterator.getEndIndex());
	}
	
	@Test
	public void getEndIndexWithElements()
	{
		Element element = getDocument().createElement("element");
		Element element1 = getDocument().createElement("element");
		element1.appendChild(getDocument().createTextNode("a"));
		Element element2 = getDocument().createElement("element");
		element2.appendChild(getDocument().createTextNode("b"));
		element.appendChild(element1);
		element.appendChild(element2);
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(2, iterator.getEndIndex());
	}
	
	@Test
	public void getEndIndexPreservePosition()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("ab"));
		element.appendChild(getDocument().createTextNode("cd"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.first();
		assertEquals(0, iterator.getIndex());
		iterator.getEndIndex();
		assertEquals(0, iterator.getIndex());
	}
	
	// getIndex tests ---------------------------------------------------------
	
	@Test
	public void getIndexAtStart()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.first();
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void getIndexAtEnd()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("abc"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals(2, iterator.getIndex());
	}
	
	@Test
	public void getIndexWithEmpty()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode(""));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		assertEquals(0, iterator.getIndex());
	}
	
	@Test
	public void getIndexWithMultipleTextNodes()
	{
		Element element = getDocument().createElement("element");
		element.appendChild(getDocument().createTextNode("a"));
		element.appendChild(getDocument().createTextNode("b"));
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals(1, iterator.getIndex());
	}
	
	@Test
	public void getIndexWithElements()
	{
		Element element = getDocument().createElement("element");
		Element element1 = getDocument().createElement("element");
		element1.appendChild(getDocument().createTextNode("a"));
		Element element2 = getDocument().createElement("element");
		element2.appendChild(getDocument().createTextNode("b"));
		element.appendChild(element1);
		element.appendChild(element2);
		getDocument().appendChild(element);
		
		DOMCharacterIterator iterator = new DOMCharacterIterator(getDocument());
		iterator.last();
		assertEquals(1, iterator.getIndex());
	}
}
