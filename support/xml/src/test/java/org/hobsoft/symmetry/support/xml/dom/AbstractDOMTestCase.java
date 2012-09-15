/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/AbstractDOMTestCase.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.hobsoft.symmetry.support.xml.dom.visitor.SplitNodeVisitor;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Provides a base test case that provides a DOM document for use in tests.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractDOMTestCase
{
	// constants --------------------------------------------------------------
	
	/**
	 * The default character to split text nodes with.
	 */
	private static final char DEFAULT_SPLIT_CHAR = '|';
	
	// fields -----------------------------------------------------------------

	/**
	 * The document builder to use for the current test.
	 */
	private DocumentBuilder builder;
	
	/**
	 * The document to use for the current test.
	 */
	private Document document;
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Creates the document builder and shared document for use in tests.
	 * 
	 * @throws ParserConfigurationException
	 *             if there was a problem creating the document builder
	 */
	@Before
	public final void setUpDOM() throws ParserConfigurationException
	{
		DocumentBuilderFactory factory = buildDocumentBuilderFactory();
		
		builder = factory.newDocumentBuilder();
		
		document = builder.newDocument();
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Builds the document builder factory that is used to construct documents for use in tests.
	 * <p>
	 * This method can be overridden to allow tests to use a different DOM implementation.
	 * </p>
	 * 
	 * @return the document builder factory to use
	 */
	protected DocumentBuilderFactory buildDocumentBuilderFactory()
	{
		return DocumentBuilderFactory.newInstance();
	}
	
	/**
	 * Gets a shared document builder instance that is local to the current test.
	 * 
	 * @return the document builder
	 */
	protected DocumentBuilder getDocumentBuilder()
	{
		return builder;
	}
	
	/**
	 * Gets a shared document instance that is local to the current test.
	 * 
	 * @return the document
	 */
	protected Document getDocument()
	{
		return document;
	}
	
	/**
	 * Parses the specified XML and splits any text nodes at the '<code>|</code>' character.
	 * 
	 * @param xml
	 *            the XML to parse
	 * @return the document that represents the XML
	 * @throws SAXException
	 *             if a SAX error occurred whilst parsing the XML
	 * @throws IOException
	 *             if an I/O error occurred whilst parsing the XML
	 */
	protected Document split(String xml) throws SAXException, IOException
	{
		return split(xml, DEFAULT_SPLIT_CHAR);
	}
	
	/**
	 * Parses the specified XML and splits any text nodes at the specified split character.
	 * 
	 * @param xml
	 *            the XML to parse
	 * @param splitChar
	 *            the character to split text nodes with
	 * @return the document that represents the XML
	 * @throws SAXException
	 *             if a SAX error occurred whilst parsing the XML
	 * @throws IOException
	 *             if an I/O error occurred whilst parsing the XML
	 */
	protected Document split(String xml, char splitChar) throws SAXException, IOException
	{
		Document document = parse(xml);
		
		split(document, splitChar);
		
		return document;
	}
	
	/**
	 * Splits text nodes within the specified node tree at the specified split character.
	 * 
	 * @param node
	 *            the root node of the tree to split
	 * @param splitChar
	 *            the character to split text nodes with
	 */
	protected void split(Node node, char splitChar)
	{
		NodeUtils.accept(node, new SplitNodeVisitor(splitChar));
	}
	
	/**
	 * Gets a document that represents the specified XML.
	 * 
	 * @param xml
	 *            the XML to parse
	 * @return the document that represents the XML
	 * @throws SAXException
	 *             if a SAX error occurred whilst parsing the XML
	 * @throws IOException
	 *             if an I/O error occurred whilst parsing the XML
	 */
	protected Document parse(String xml) throws SAXException, IOException
	{
		return builder.parse(new InputSource(new StringReader(xml)));
	}
	
	/**
	 * Asserts whether the specified node is equal to the expected XML.
	 * 
	 * @param expected
	 *            the expected XML
	 * @param actual
	 *            the actual node
	 * @throws SAXException
	 *             if a SAX error occurred whilst parsing the XML
	 * @throws IOException
	 *             if an I/O error occurred whilst parsing the XML
	 * @throws AssertionError
	 *             if the node is not equal to the expected XML
	 */
	protected void assertDOM(String expected, Node actual) throws SAXException, IOException
	{
		assertDOM(parse(expected), actual);
	}
	
	/**
	 * Asserts whether the specified nodes are equal.
	 * 
	 * @param expected
	 *            the expected node
	 * @param actual
	 *            the actual node
	 * @throws AssertionError
	 *             if the nodes are not equal
	 */
	protected void assertDOM(Node expected, Node actual)
	{
		DOMAssert.assertEquals(expected, actual);
	}
}
