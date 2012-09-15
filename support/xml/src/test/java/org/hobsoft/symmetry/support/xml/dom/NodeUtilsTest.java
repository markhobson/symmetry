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

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>NodeUtils</code>.
 * 
 * @author Mark Hobson
 * @see NodeUtils
 */
public class NodeUtilsTest extends AbstractDOMTestCase
{
	// getDocument tests ------------------------------------------------------
	
	@Test
	public void getDocumentWithDocument()
	{
		assertEquals(getDocument(), NodeUtils.getDocument(getDocument()));
	}
	
	@Test
	public void getDocumentWithElement()
	{
		Element a = getDocument().createElement("a");
		
		assertEquals(getDocument(), NodeUtils.getDocument(a));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getDocumentWithNull()
	{
		try
		{
			NodeUtils.getDocument(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("node cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	// canAppendChild tests ---------------------------------------------------
	
	@Test
	public void canAppendChildDocumentElement()
	{
		Element a = getDocument().createElement("a");
		
		assertTrue(NodeUtils.canAppendChild(getDocument(), a));
	}
	
	@Test
	public void canAppendChildDocumentElementTwice()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		Element b = getDocument().createElement("b");
		
		assertFalse(NodeUtils.canAppendChild(getDocument(), b));
	}
	
	@Test
	public void canAppendChildDocumentElementDoctypeValid()
	{
		DocumentType doctype = getDocument().getImplementation().createDocumentType("a", null, null);
		getDocument().appendChild(doctype);
		
		Element a = getDocument().createElement("a");
		
		assertTrue(NodeUtils.canAppendChild(getDocument(), a));
	}
	
	@Test
	public void canAppendChildDocumentElementDoctypeInvalid()
	{
		DocumentType doctype = getDocument().getImplementation().createDocumentType("a", null, null);
		getDocument().appendChild(doctype);
		
		Element b = getDocument().createElement("b");
		
		assertFalse(NodeUtils.canAppendChild(getDocument(), b));
	}
	
	@Test
	public void canAppendChildDocumentDocumentType()
	{
		DocumentType a = getDocument().getImplementation().createDocumentType("a", null, null);
		
		assertTrue(NodeUtils.canAppendChild(getDocument(), a));
	}
	
	@Test
	public void canAppendChildDocumentDocumentTypeTwice()
	{
		DocumentType a = getDocument().getImplementation().createDocumentType("a", null, null);
		getDocument().appendChild(a);
		
		DocumentType b = getDocument().getImplementation().createDocumentType("b", null, null);
		
		assertFalse(NodeUtils.canAppendChild(getDocument(), b));
	}
	
	// getRoot tests ----------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void getRootWithNull()
	{
		try
		{
			NodeUtils.getRoot(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("node cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void getRootWithRoot()
	{
		assertEquals(getDocument(), NodeUtils.getRoot(getDocument()));
	}
	
	@Test
	public void getRootWithChild()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals(getDocument(), NodeUtils.getRoot(root));
	}
	
	@Test
	public void getRootWithGrandChild()
	{
		Element root = getDocument().createElement("root");
		Element a = getDocument().createElement("a");
		root.appendChild(a);
		getDocument().appendChild(root);
		
		assertEquals(getDocument(), NodeUtils.getRoot(a));
	}
	
	// isAncestor tests -------------------------------------------------------
	
	@Test
	public void isAncestorWithSelf()
	{
		assertFalse(NodeUtils.isAncestor(getDocument(), getDocument()));
	}
	
	@Test
	public void isAncestorWithParent()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertTrue(NodeUtils.isAncestor(getDocument(), root));
	}
	
	@Test
	public void isAncestorWithGrandParent()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertTrue(NodeUtils.isAncestor(getDocument(), element));
	}
	
	@Test
	public void isAncestorWithNonAncestor()
	{
		Element root = getDocument().createElement("root");
		
		assertFalse(NodeUtils.isAncestor(getDocument(), root));
	}
	
	@Test
	public void isAncestorWithNullNode()
	{
		assertFalse(NodeUtils.isAncestor(getDocument(), null));
	}
	
	@Test
	public void isAncestorWithNullAncestor()
	{
		assertFalse(NodeUtils.isAncestor(null, getDocument()));
	}
	
	@Test
	public void isAncestorWithNullAncestorAndNode()
	{
		assertFalse(NodeUtils.isAncestor(null, null));
	}
	
	// isAncestorOrSelf tests -------------------------------------------------
	
	@Test
	public void isAncestorOrSelfWithSelf()
	{
		assertTrue(NodeUtils.isAncestorOrSelf(getDocument(), getDocument()));
	}
	
	@Test
	public void isAncestorOrSelfWithParent()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertTrue(NodeUtils.isAncestorOrSelf(getDocument(), root));
	}
	
	@Test
	public void isAncestorOrSelfWithGrandParent()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertTrue(NodeUtils.isAncestorOrSelf(getDocument(), element));
	}
	
	@Test
	public void isAncestorOrSelfWithNonAncestor()
	{
		Element root = getDocument().createElement("root");
		
		assertFalse(NodeUtils.isAncestorOrSelf(getDocument(), root));
	}
	
	@Test
	public void isAncestorOrSelfWithNullNode()
	{
		assertFalse(NodeUtils.isAncestorOrSelf(getDocument(), null));
	}
	
	@Test
	public void isAncestorOrSelfWithNullAncestor()
	{
		assertFalse(NodeUtils.isAncestorOrSelf(null, getDocument()));
	}
	
	@Test
	public void isAncestorOrSelfWithNullAncestorAndNode()
	{
		assertFalse(NodeUtils.isAncestorOrSelf(null, null));
	}
	
	// unsurround tests -------------------------------------------------------
	
	@Test
	public void unsurroundWithNoChildren() throws SAXException, IOException
	{
		Document document = parse("<root><element/></root>");
		Element element = (Element) document.getElementsByTagName("element").item(0);
		
		NodeUtils.unsurround(element);
		assertDOM("<root/>", document);
	}
	
	@Test
	public void unsurroundWithOneChild() throws SAXException, IOException
	{
		Document document = parse("<root><element><child/></element></root>");
		Element element = (Element) document.getElementsByTagName("element").item(0);
		Element expectedChild = (Element) document.getElementsByTagName("child").item(0);
		
		NodeUtils.unsurround(element);
		
		Element actualChild = (Element) document.getElementsByTagName("child").item(0);

		assertDOM("<root><child/></root>", document);
		assertSame(expectedChild, actualChild);
	}
	
	@Test
	public void unsurroundWithOneChildCloned() throws SAXException, IOException
	{
		Document document = parse("<root><element><child/></element></root>");
		Element element = (Element) document.getElementsByTagName("element").item(0);
		Element expectedChild = (Element) document.getElementsByTagName("child").item(0);
		
		NodeUtils.unsurround(element, true);

		Element actualChild = (Element) document.getElementsByTagName("child").item(0);

		assertDOM("<root><child/></root>", document);
		assertNotSame(expectedChild, actualChild);
	}
	
	@Test
	public void unsurroundWithMultipleChildren() throws SAXException, IOException
	{
		Document document = parse("<root><element><a/><b/><c/></element></root>");
		Element element = (Element) document.getElementsByTagName("element").item(0);
		Element expectedChild1 = (Element) document.getElementsByTagName("a").item(0);
		Element expectedChild2 = (Element) document.getElementsByTagName("b").item(0);
		Element expectedChild3 = (Element) document.getElementsByTagName("c").item(0);
		
		NodeUtils.unsurround(element);
		
		Element actualChild1 = (Element) document.getElementsByTagName("a").item(0);
		Element actualChild2 = (Element) document.getElementsByTagName("b").item(0);
		Element actualChild3 = (Element) document.getElementsByTagName("c").item(0);
		
		assertDOM("<root><a/><b/><c/></root>", document);
		assertSame(expectedChild1, actualChild1);
		assertSame(expectedChild2, actualChild2);
		assertSame(expectedChild3, actualChild3);
	}
	
	@Test
	public void unsurroundWithMultipleChildrenCloned() throws SAXException, IOException
	{
		Document document = parse("<root><element><a/><b/><c/></element></root>");
		Element element = (Element) document.getElementsByTagName("element").item(0);
		Element expectedChild1 = (Element) document.getElementsByTagName("a").item(0);
		Element expectedChild2 = (Element) document.getElementsByTagName("b").item(0);
		Element expectedChild3 = (Element) document.getElementsByTagName("c").item(0);
		
		NodeUtils.unsurround(element, true);
		
		Element actualChild1 = (Element) document.getElementsByTagName("a").item(0);
		Element actualChild2 = (Element) document.getElementsByTagName("b").item(0);
		Element actualChild3 = (Element) document.getElementsByTagName("c").item(0);
		
		assertDOM("<root><a/><b/><c/></root>", document);
		assertNotSame(expectedChild1, actualChild1);
		assertNotSame(expectedChild2, actualChild2);
		assertNotSame(expectedChild3, actualChild3);
	}
	
	// getPath tests ----------------------------------------------------------
	
	@Test
	public void getPathWithSameNode()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		Node[] actualPath = NodeUtils.getPath(root, root);
		Node[] expectedPath = new Node[0];
		
		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathWithDocumentAndDocumentElement()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Node[] actualPath = NodeUtils.getPath(getDocument(), root);
		Node[] expectedPath = new Node[] {root};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathWithDocumentElementAndElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		Node[] actualPath = NodeUtils.getPath(root, element);
		Node[] expectedPath = new Node[] {element};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathWithDocumentElementAndChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		Node[] actualPath = NodeUtils.getPath(root, child);
		Node[] expectedPath = new Node[] {element, child};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathWithNullParent()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		try
		{
			NodeUtils.getPath(null, root);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("parent cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathWithNullChild()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		try
		{
			NodeUtils.getPath(root, null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("child cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathWithDifferentDocuments()
	{
		Element root1 = getDocument().createElement("root");
		getDocument().appendChild(root1);
		
		Document document2 = getDocumentBuilder().newDocument();
		Element root2 = document2.createElement("root");
		document2.appendChild(root2);

		try
		{
			NodeUtils.getPath(root1, root2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("Nodes are owned by different documents", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathWithParentNotAncestorOfChild()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element1");
		Element element2 = getDocument().createElement("element2");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		try
		{
			NodeUtils.getPath(element1, element2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals(element2 + " is not a child of " + element1, exception.getMessage());
			
			throw exception;
		}
	}
	
	// getPathIndex tests -----------------------------------------------------
	
	@Test
	public void getPathIndexWithSameNode()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		int[] actualPath = NodeUtils.getPathIndex(root, root);
		int[] expectedPath = new int[0];
		
		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathIndexWithDocumentAndDocumentElement()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		int[] actualPath = NodeUtils.getPathIndex(getDocument(), root);
		int[] expectedPath = new int[] {0};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathIndexWithDocumentElementAndElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		int[] actualPath = NodeUtils.getPathIndex(root, element);
		int[] expectedPath = new int[] {0};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathIndexWithDocumentElementAndElementWithSiblings()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element1");
		Element element2 = getDocument().createElement("element2");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		int[] actualPath = NodeUtils.getPathIndex(root, element2);
		int[] expectedPath = new int[] {1};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathIndexWithDocumentElementAndChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		int[] actualPath = NodeUtils.getPathIndex(root, child);
		int[] expectedPath = new int[] {0, 0};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test
	public void getPathIndexWithDocumentElementAndChildElementWithSiblings()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child1 = getDocument().createElement("child1");
		Element child2 = getDocument().createElement("child2");
		element.appendChild(child1);
		element.appendChild(child2);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		int[] actualPath = NodeUtils.getPathIndex(root, child2);
		int[] expectedPath = new int[] {0, 1};

		assertTrue(Arrays.equals(expectedPath, actualPath));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathIndexWithNullParent()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		try
		{
			NodeUtils.getPathIndex(null, root);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("parent cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathIndexWithNullChild()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);

		try
		{
			NodeUtils.getPathIndex(root, null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("child cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathIndexWithDifferentDocuments()
	{
		Element root1 = getDocument().createElement("root");
		getDocument().appendChild(root1);
		
		Document document2 = getDocumentBuilder().newDocument();
		Element root2 = document2.createElement("root");
		document2.appendChild(root2);

		try
		{
			NodeUtils.getPathIndex(root1, root2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("Nodes are owned by different documents", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathIndexWithParentNotAncestorOfChild()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element1");
		Element element2 = getDocument().createElement("element2");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		try
		{
			NodeUtils.getPathIndex(element1, element2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals(element2 + " is not a child of " + element1, exception.getMessage());
			
			throw exception;
		}
	}
	
	// compare tests ----------------------------------------------------------
	
	@Test
	public void compareEqual()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals(0, NodeUtils.compare(getDocument(), 0, getDocument(), 0));
	}
	
	@Test
	public void compareNextOffset()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals(-1, NodeUtils.compare(getDocument(), 0, getDocument(), 1));
	}
	
	@Test
	public void comparePreviousOffset()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals(1, NodeUtils.compare(getDocument(), 1, getDocument(), 0));
	}
	
	@Test
	public void compareFirstChild()
	{
		Element root = getDocument().createElement("root");
		Element child = getDocument().createElement("child");
		root.appendChild(child);
		getDocument().appendChild(root);
		
		assertEquals(-1, NodeUtils.compare(getDocument(), 0, root, 0));
	}
	
	@Test
	public void compareLastChild()
	{
		Element root = getDocument().createElement("root");
		Element child = getDocument().createElement("child");
		root.appendChild(child);
		getDocument().appendChild(root);
		
		assertEquals(1, NodeUtils.compare(getDocument(), 1, root, 1));
	}
	
	// toXPath tests ----------------------------------------------------------
	
	@Test
	public void toXPathDocument()
	{
		assertEquals("/", NodeUtils.toXPath(getDocument()));
	}
	
	@Test
	public void toXPathDocumentElement()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals("/root", NodeUtils.toXPath(root));
	}
	
	@Test
	public void toXPathElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals("/root/element", NodeUtils.toXPath(element));
	}
	
	@Test
	public void toXPathElementWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		root.appendChild(element);
		root.appendChild(other);
		getDocument().appendChild(root);
		
		assertEquals("/root/element", NodeUtils.toXPath(element));
	}
	
	@Test
	public void toXPathFirstElementSibling()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals("/root/element[1]", NodeUtils.toXPath(element1));
	}
	
	@Test
	public void toXPathFirstElementSiblingWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(other);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals("/root/element[1]", NodeUtils.toXPath(element1));
	}
	
	@Test
	public void toXPathSecondElementSibling()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals("/root/element[2]", NodeUtils.toXPath(element2));
	}
	
	@Test
	public void toXPathSecondElementSiblingWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(other);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals("/root/element[2]", NodeUtils.toXPath(element2));
	}
	
	@Test
	public void toXPathChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals("/root/element/child", NodeUtils.toXPath(child));
	}

	@Test
	public void toXPathText()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("text");
		root.appendChild(text);
		getDocument().appendChild(root);
		
		assertEquals("/root/#text", NodeUtils.toXPath(text));
	}

	@Test
	public void toXPathFirstTextSibling()
	{
		Element root = getDocument().createElement("root");
		Text text1 = getDocument().createTextNode("text");
		Text text2 = getDocument().createTextNode("text");
		root.appendChild(text1);
		root.appendChild(text2);
		getDocument().appendChild(root);
		
		assertEquals("/root/#text[1]", NodeUtils.toXPath(text1));
	}

	@Test
	public void toXPathSecondTextSibling()
	{
		Element root = getDocument().createElement("root");
		Text text1 = getDocument().createTextNode("text");
		Text text2 = getDocument().createTextNode("text");
		root.appendChild(text1);
		root.appendChild(text2);
		getDocument().appendChild(root);
		
		assertEquals("/root/#text[2]", NodeUtils.toXPath(text2));
	}

	@Test
	public void toXPathAttribute()
	{
		Element root = getDocument().createElement("root");
		Attr attr = getDocument().createAttribute("name");
		attr.setValue("value");
		root.setAttributeNode(attr);
		getDocument().appendChild(root);
		
		assertEquals("/root/@name", NodeUtils.toXPath(attr));
	}
	
	// evaluateXPath tests ----------------------------------------------------

	@Test
	public void evaluateXPathDocument()
	{
		assertEquals(getDocument(), NodeUtils.evaluateXPath(getDocument(), "/"));
	}

	@Test
	public void evaluateXPathDocumentElement()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertEquals(root, NodeUtils.evaluateXPath(getDocument(), "/root"));
	}

	@Test
	public void evaluateXPathElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals(element, NodeUtils.evaluateXPath(getDocument(), "/root/element"));
	}

	@Test
	public void evaluateXPathElementWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		root.appendChild(element);
		root.appendChild(other);
		getDocument().appendChild(root);
		
		assertEquals(element, NodeUtils.evaluateXPath(getDocument(), "/root/element"));
	}

	@Test
	public void evaluateXPathFirstElementSibling()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals(element1, NodeUtils.evaluateXPath(getDocument(), "/root/element[1]"));
	}

	@Test
	public void evaluateXPathFirstElementSiblingWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(other);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals(element1, NodeUtils.evaluateXPath(getDocument(), "/root/element[1]"));
	}

	@Test
	public void evaluateXPathSecondElementSibling()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals(element2, NodeUtils.evaluateXPath(getDocument(), "/root/element[2]"));
	}

	@Test
	public void evaluateXPathSecondElementSiblingWithOther()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element other = getDocument().createElement("other");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(other);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertEquals(element2, NodeUtils.evaluateXPath(getDocument(), "/root/element[2]"));
	}

	@Test
	public void evaluateXPathAmbiguousElementSibling()
	{
		Element root = getDocument().createElement("root");
		Element element1 = getDocument().createElement("element");
		Element element2 = getDocument().createElement("element");
		root.appendChild(element1);
		root.appendChild(element2);
		getDocument().appendChild(root);
		
		assertNull(NodeUtils.evaluateXPath(getDocument(), "/root/element"));
	}

	@Test
	public void evaluateXPathChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals(child, NodeUtils.evaluateXPath(getDocument(), "/root/element/child"));
	}

	@Test
	public void evaluateXPathRelativeOnChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals(child, NodeUtils.evaluateXPath(element, "child"));
	}

	@Test
	public void evaluateXPathAbsoluteOnChildElement()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		Element child = getDocument().createElement("child");
		element.appendChild(child);
		root.appendChild(element);
		getDocument().appendChild(root);
		
		assertEquals(child, NodeUtils.evaluateXPath(element, "/root/element/child"));
	}

	@Test
	public void evaluateXPathUnknownElement()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		assertNull(NodeUtils.evaluateXPath(getDocument(), "/root/no/such/element"));
	}

	@Test
	public void evaluateXPathText()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("text");
		root.appendChild(text);
		getDocument().appendChild(root);
		
		assertEquals(text, NodeUtils.evaluateXPath(getDocument(), "/root/#text"));
	}

	@Test
	public void evaluateXPathFirstTextSibling()
	{
		Element root = getDocument().createElement("root");
		Text text1 = getDocument().createTextNode("text");
		Text text2 = getDocument().createTextNode("text");
		root.appendChild(text1);
		root.appendChild(text2);
		getDocument().appendChild(root);
		
		assertEquals(text1, NodeUtils.evaluateXPath(getDocument(), "/root/#text[1]"));
	}

	@Test
	public void evaluateXPathSecondTextSibling()
	{
		Element root = getDocument().createElement("root");
		Text text1 = getDocument().createTextNode("text");
		Text text2 = getDocument().createTextNode("text");
		root.appendChild(text1);
		root.appendChild(text2);
		getDocument().appendChild(root);
		
		assertEquals(text2, NodeUtils.evaluateXPath(getDocument(), "/root/#text[2]"));
	}

	@Test
	public void evaluateXPathAttribute()
	{
		Element root = getDocument().createElement("root");
		Attr attr = getDocument().createAttribute("name");
		attr.setValue("value");
		root.setAttributeNode(attr);
		getDocument().appendChild(root);
		
		assertEquals(attr, NodeUtils.evaluateXPath(getDocument(), "/root/@name"));
	}

	@Test
	public void evaluateXPathUnknownAttribute()
	{
		Element root = getDocument().createElement("root");
		Attr attr = getDocument().createAttribute("name");
		attr.setValue("value");
		root.setAttributeNode(attr);
		getDocument().appendChild(root);
		
		assertNull(NodeUtils.evaluateXPath(getDocument(), "/root/@unknown"));
	}
}
