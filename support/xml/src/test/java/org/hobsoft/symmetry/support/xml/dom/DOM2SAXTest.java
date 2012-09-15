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

import org.hobsoft.symmetry.support.xml.sax.MockSAX;
import org.hobsoft.symmetry.support.xml.sax.SAX;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests <code>DOM2SAX</code>.
 * 
 * @author Mark Hobson
 * @see DOM2SAX
 */
public class DOM2SAXTest extends AbstractDOMTestCase
{
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private MockSAX sax;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		sax = new MockSAX();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void document() throws SAXException, IOException
	{
		sax.startDocument();
		sax.endDocument();
		
		assertSAX(getDocument());
	}
	
	@Test
	public void emptyElement() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		getDocument().appendChild(element);
		
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

//	@Test
//	public void emptyNamespacedElement() throws SAXException, IOException
//	{
//		Element element = getDocument().createElementNS("uri", "root");
//		getDocument().appendChild(element);
//		
//		sax.startDocument();
//		sax.startPrefixMapping("", "uri");
//		sax.startElement("uri", "root", "root", EMPTY_ATTRIBUTES);
//		sax.endElement("uri", "root", "root");
//		sax.endPrefixMapping("");
//		sax.endDocument();
//		
//		assertSAX(getDocument());
//	}

//	@Test
//	public void emptyPrefixedNamespacedElement() throws SAXException, IOException
//	{
//		Element element = getDocument().createElementNS("uri", "p:root");
//		getDocument().appendChild(element);
//
//		sax.startDocument();
//		sax.startPrefixMapping("p", "uri");
//		sax.startElement("uri", "root", "p:root", EMPTY_ATTRIBUTES);
//		sax.endElement("uri", "root", "p:root");
//		sax.endPrefixMapping("p");
//		sax.endDocument();
//		
//		assertSAX(getDocument());
//	}

	@Test
	public void emptyElementWithAttribute() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		element.setAttribute("name", "value");
		getDocument().appendChild(element);
		
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

	@Test
	public void emptyElementWithNamespacedAttribute() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		element.setAttributeNS("uri", "name", "value");
		getDocument().appendChild(element);
		
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("uri", "name", "name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

	@Test
	public void emptyElementWithPrefixedNamespacedAttribute() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		element.setAttributeNS("uri", "p:name", "value");
		getDocument().appendChild(element);
		
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("uri", "name", "p:name", "CDATA", "value");
		
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

	@Test
	public void elementWithText() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		element.appendChild(getDocument().createTextNode("text"));
		getDocument().appendChild(element);
		
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.characters("text".toCharArray(), 0, 4);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

	@Test
	public void elementWithCDATA() throws SAXException, IOException
	{
		Element element = getDocument().createElement("root");
		element.appendChild(getDocument().createCDATASection("text"));
		getDocument().appendChild(element);
		
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.startCDATA();
		sax.characters("text".toCharArray(), 0, 4);
		sax.endCDATA();
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertSAX(getDocument());
	}

	// private methods --------------------------------------------------------
	
	private void assertSAX(Node node) throws SAXException, IOException
	{
		DOM2SAX dom = new DOM2SAX();
		
		dom.setContentHandler(sax.getContentHandler());
		dom.setProperty(DOM2SAX.NODE_PROPERTY, node);
		dom.setProperty(SAX.Property.LEXICAL_HANDLER, sax.getLexicalHandler());
		
		dom.parse("");
		
		sax.verify();
	}
}
