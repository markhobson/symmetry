/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/SAX2DOMTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import org.hobsoft.symmetry.support.xml.Namespaces;
import org.hobsoft.symmetry.support.xml.dom.AbstractDOMTestCase;
import org.hobsoft.symmetry.support.xml.dom.DOMAssert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests <code>SAX2DOM</code>.
 * 
 * @author Mark Hobson
 * @see SAX2DOM
 */
public class SAX2DOMTest extends AbstractDOMTestCase
{
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private SAX2DOM sax;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		sax = new SAX2DOM(getDocument().getImplementation());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void document() throws SAXException
	{
		sax.startDocument();
		sax.endDocument();
		
		assertDOM(getDocument());
	}
	
	@Test
	public void emptyElement() throws SAXException
	{
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}
	
	@Test
	public void emptyNamespacedElement() throws SAXException
	{
		sax.startDocument();
		sax.startPrefixMapping("", "uri");
		sax.startElement("uri", "root", "root", EMPTY_ATTRIBUTES);
		sax.endElement("uri", "root", "root");
		sax.endPrefixMapping("");
		sax.endDocument();
		
		Element element = getDocument().createElementNS("uri", "root");
		element.setAttributeNS(Namespaces.XMLNS, Namespaces.PREFIX, "uri");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}
	
	@Test
	public void emptyPrefixedNamespacedElement() throws SAXException
	{
		sax.startDocument();
		sax.startPrefixMapping("p", "uri");
		sax.startElement("uri", "root", "p:root", EMPTY_ATTRIBUTES);
		sax.endElement("uri", "root", "p:root");
		sax.endPrefixMapping("p");
		sax.endDocument();
		
		Element element = getDocument().createElementNS("uri", "p:root");
		element.setAttributeNS(Namespaces.XMLNS, Namespaces.PREFIX + ":p", "uri");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}
	
	@Test
	public void emptyElementWithAttribute() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		element.setAttribute("name", "value");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}

	@Test
	public void emptyElementWithNamespacedAttribute() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("uri", "name", "name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		element.setAttributeNS("uri", "name", "value");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}

	@Test
	public void emptyElementWithPrefixedNamespacedAttribute() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("uri", "name", "p:name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		element.setAttributeNS("uri", "p:name", "value");
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}

	@Test
	public void elementWithText() throws SAXException
	{
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.characters("text".toCharArray(), 0, 4);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		element.appendChild(getDocument().createTextNode("text"));
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}

	@Test
	public void elementWithCDATA() throws SAXException
	{
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.startCDATA();
		sax.characters("text".toCharArray(), 0, 4);
		sax.endCDATA();
		sax.endElement("", "", "root");
		sax.endDocument();
		
		Element element = getDocument().createElement("root");
		element.appendChild(getDocument().createCDATASection("text"));
		getDocument().appendChild(element);
		
		assertDOM(getDocument());
	}

	// private methods --------------------------------------------------------
	
	private void assertDOM(Node expected)
	{
		DOMAssert.assertEquals(expected, sax.getDocument());
	}
}
