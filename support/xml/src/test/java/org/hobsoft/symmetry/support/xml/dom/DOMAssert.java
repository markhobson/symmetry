/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/DOMAssert.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import org.junit.Assert;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Provides methods to assert the equality of DOM Core objects.
 * 
 * @author Mark Hobson
 * @version $Id: DOMAssert.java 69821 2010-01-21 16:46:57Z mark@IIZUKA.CO.UK $
 */
public final class DOMAssert
{
	// TODO: move to a proper test component of common-xml project
	
	// TODO: use XML-based assertion failed messages?
	
	// TODO: write DOMAssertTest
	
	// constructors -----------------------------------------------------------
	
	private DOMAssert()
	{
		// private constructor for utility class
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Asserts whether the specified <code>Node</code>s are equal.
	 * 
	 * @param expected
	 *            the expected <code>Node</code>
	 * @param actual
	 *            the actual <code>Node</code>
	 * @throws AssertionError
	 *             if the <code>Node</code>s are not equal
	 */
	public static void assertEquals(Node expected, Node actual)
	{
		// assert node type
		short type = expected.getNodeType();
		assertNodeTypeEquals(type, actual.getNodeType(), expected);
		
		// assert parameters specific to the node type
		switch (type)
		{
			case Node.ATTRIBUTE_NODE:
				Assert.assertEquals("Attribute name", expected.getNodeName(), actual.getNodeName());
				Assert.assertEquals("Attribute value", expected.getNodeValue(), actual.getNodeValue());
				break;
				
			case Node.CDATA_SECTION_NODE:
				Assert.assertEquals("CDATA section data", expected.getNodeValue(), actual.getNodeValue());
				break;
				
			case Node.COMMENT_NODE:
				Assert.assertEquals("Comment data", expected.getNodeValue(), actual.getNodeValue());
				break;
				
			case Node.DOCUMENT_FRAGMENT_NODE:
				break;
				
			case Node.DOCUMENT_NODE:
				break;
				
			case Node.DOCUMENT_TYPE_NODE:
				throw new UnsupportedOperationException("Unsupported node type: DOCUMENT_TYPE_NODE");
				
			case Node.ELEMENT_NODE:
				Assert.assertEquals("Element namespace URI", expected.getNamespaceURI(), actual.getNamespaceURI());
				Assert.assertEquals("Element prefix", expected.getPrefix(), actual.getPrefix());
				Assert.assertEquals("Element local name", expected.getLocalName(), actual.getLocalName());
				Assert.assertEquals("Element tag name", ((Element) expected).getTagName(),
					((Element) actual).getTagName());
				assertEquals(expected.getAttributes(), actual.getAttributes(), expected);
				break;
				
			case Node.ENTITY_NODE:
				throw new UnsupportedOperationException("Unsupported node type: ENTITY_NODE");
				
			case Node.ENTITY_REFERENCE_NODE:
				throw new UnsupportedOperationException("Unsupported node type: ENTITY_REFERENCE_NODE");
				
			case Node.NOTATION_NODE:
				throw new UnsupportedOperationException("Unsupported node type: NOTATION_NODE");
				
			case Node.PROCESSING_INSTRUCTION_NODE:
				throw new UnsupportedOperationException("Unsupported node type: PROCESSING_INSTRUCTION_NODE");
				
			case Node.TEXT_NODE:
				Assert.assertEquals("Text data", expected.getNodeValue(), actual.getNodeValue());
				break;
				
			default:
				throw new UnsupportedOperationException("Unsupported node type: " + type);
		}

		// assert children
		assertEquals(expected.getChildNodes(), actual.getChildNodes(), expected);
	}
	
	/**
	 * Asserts whether the specified node types are equal.
	 * 
	 * This method calls <code>assertNodeTypeEquals(short, short, Node)</code> with a <code>null</code>
	 * <code>Node</code>.
	 * 
	 * @param expected
	 *            the expected node type
	 * @param actual
	 *            the actual node type
	 * @throws AssertionError
	 *             if the node types are not equal
	 * @see #assertNodeTypeEquals(short, short, Node)
	 */
	public static void assertNodeTypeEquals(short expected, short actual)
	{
		assertNodeTypeEquals(expected, actual, null);
	}
	
	/**
	 * Asserts whether the specified node types are equal.
	 * 
	 * This method is preferable to <code>Assert.assertEquals(short, short)</code> since it translates the node types
	 * into <code>String</code>s upon failure.
	 * 
	 * @param expected
	 *            the expected node type
	 * @param actual
	 *            the actual node type
	 * @param node
	 *            a <code>Node</code> that indicates the current location within the DOM to be used in a possible
	 *            failure message, or <code>null</code> for none
	 * @throws AssertionError
	 *             if the node types are not equal
	 */
	public static void assertNodeTypeEquals(short expected, short actual, Node node)
	{
		if (expected != actual)
		{
			String expectedName = NodeUtils.getNodeTypeName(expected);
			String actualName = NodeUtils.getNodeTypeName(actual);
			
			fail("Node type", expectedName, actualName, node);
		}
	}
	
	/**
	 * Asserts whether the specified <code>NodeList</code>s are equal.
	 * 
	 * This method calls <code>assertEquals(NodeList, NodeList, Node)</code> with a <code>null</code> <code>Node</code>.
	 * 
	 * @param expected
	 *            the expected <code>NodeList</code>, possibly <code>null</code>
	 * @param actual
	 *            the actual <code>NodeList</code>, possibly <code>null</code>
	 * @throws AssertionError
	 *             if the <code>NodeList</code>s are not equal
	 * @see #assertEquals(NodeList, NodeList, Node)
	 */
	public static void assertEquals(NodeList expected, NodeList actual)
	{
		assertEquals(expected, actual, null);
	}
	
	/**
	 * Asserts whether the specified <code>NodeList</code>s are equal.
	 * 
	 * @param expected
	 *            the expected <code>NodeList</code>, possibly <code>null</code>
	 * @param actual
	 *            the actual <code>NodeList</code>, possibly <code>null</code>
	 * @param node
	 *            a <code>Node</code> that indicates the current location within the DOM to be used in a possible
	 *            failure message, or <code>null</code> for none
	 * @throws AssertionError
	 *             if the <code>NodeList</code>s are not equal
	 */
	public static void assertEquals(NodeList expected, NodeList actual, Node node)
	{
		if (expected == null)
		{
			Assert.assertNull(actual);
			return;
		}

		Assert.assertNotNull(actual);

		// assert length
		int length = expected.getLength();
		assertEquals("NodeList length", length, actual.getLength(), node);
		
		// assert nodes
		for (int i = 0; i < length; i++)
		{
			assertEquals(expected.item(i), actual.item(i));
		}
	}
	
	/**
	 * Asserts whether the specified <code>NamedNodeMap</code>s are equal.
	 * 
	 * @param expected
	 *            the expected <code>NamedNodeMap</code>
	 * @param actual
	 *            the actual <code>NamedNodeMap</code>
	 * @param node
	 *            a <code>Node</code> that indicates the current location within the DOM to be used in a possible
	 *            failure message, or <code>null</code> for none
	 * @throws AssertionError
	 *             if the <code>NamedNodeMap</code> are not equal
	 */
	public static void assertEquals(NamedNodeMap expected, NamedNodeMap actual, Node node)
	{
		if (expected == null)
		{
			Assert.assertNull(actual);
			return;
		}
		
		Assert.assertNotNull(actual);
		
		// assert length
		int length = expected.getLength();
		assertEquals("NamedNodeMap length", length, actual.getLength(), node);
		
		// assert nodes
		for (int i = 0; i < length; i++)
		{
			assertEquals(expected.item(i), actual.item(i));
		}
	}
	
	/**
	 * Asserts whether the specified DOM exception has the specified attributes.
	 * 
	 * @param expectedCode
	 *            the expected DOM exception code
	 * @param expectedMessage
	 *            the expected DOM exception message
	 * @param actual
	 *            the actual DOM exception
	 */
	public static void assertDOMException(int expectedCode, String expectedMessage, DOMException actual)
	{
		Assert.assertNotNull("DOMException expected not null", actual);
		Assert.assertEquals("DOMException code", expectedCode, actual.code);
		Assert.assertEquals("DOMException message", expectedMessage, actual.getMessage());
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertEquals(String message, int expected, int actual, Node node)
	{
		if (expected != actual)
		{
			fail(message, expected, actual, node);
		}
	}
	
	private static void fail(String message, Object expected, Object actual, Node node)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(message).append(" expected: ");
		builder.append("<").append(expected).append(">");
		builder.append(" but was: ");
		builder.append("<").append(actual).append(">");
		
		if (node != null)
		{
			builder.append(" at: ").append(NodeUtils.toXPath(node));
		}
		
		Assert.fail(builder.toString());
	}
}
