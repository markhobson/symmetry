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
package org.hobsoft.symmetry.support.xml.sax;

import org.hobsoft.symmetry.support.xml.Namespaces;
import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SAX2DOM extends AbstractSAXHandler
{
	// fields -----------------------------------------------------------------
	
	private final Document document;

	private final DOMImplementation domImpl;

	private final StringBuilder builder;

	private Element documentElement;

	private Node currentNode;

	// constructors -----------------------------------------------------------
	
	public SAX2DOM(DOMImplementation domImpl)
	{
		this(domImpl.createDocument(null, null, null));
	}
	
	public SAX2DOM(Node node)
	{
		document = NodeUtils.getDocument(node);
		domImpl = document.getImplementation();
		builder = new StringBuilder();
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		super.endDocument();
		
		if (documentElement != null)
		{
			document.appendChild(documentElement);
		}

		// unset current node
		currentNode = null;
		documentElement = null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, qName, attributes);
		
		flushBuffer(Node.TEXT_NODE);
		
		// create element
		Element element = (uri.length() == 0) ? document.createElement(qName) : document.createElementNS(uri, qName);
		if (documentElement == null)
		{
			documentElement = element;
		}
		
		// add namespaces
		for (String nsPrefix : getPrefixMap().keySet())
		{
			String nsQName = (nsPrefix.length() == 0) ? Namespaces.PREFIX : Namespaces.PREFIX + ":" + nsPrefix;
			String nsURI = getPrefixMap().get(nsPrefix);
			element.setAttributeNS(Namespaces.XMLNS, nsQName, nsURI);
		}
		getPrefixMap().clear();

		// add attributes
		for (int i = 0; i < attributes.getLength(); i++)
		{
			String attrURI = attributes.getURI(i);
			String attrQName = attributes.getQName(i);
			String attrValue = attributes.getValue(i);
			element.setAttributeNS(attrURI, attrQName, attrValue);
		}
		
		// add element to node
		if (currentNode != null)
		{
			currentNode.appendChild(element);
		}
		
		// move to new element
		currentNode = element;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		super.endElement(uri, localName, qName);
		
		flushBuffer(Node.TEXT_NODE);
		
		// move to parent node
		currentNode = currentNode.getParentNode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		
		// append to character data buffer
		builder.append(ch, start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
	{
		super.ignorableWhitespace(ch, start, length);
		
		// append to character data buffer
		builder.append(ch, start, length);
	}
	
	// LexicalHandler methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException
	{
		super.startDTD(name, publicId, systemId);
		
		DocumentType doctype = domImpl.createDocumentType(name, publicId, systemId);
		NamedNodeMap attributes = document.getAttributes();
		if (attributes != null)
		{
			attributes.setNamedItem(doctype);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startCDATA() throws SAXException
	{
		super.startCDATA();
		
		flushBuffer(Node.TEXT_NODE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endCDATA() throws SAXException
	{
		super.endCDATA();
		
		flushBuffer(Node.CDATA_SECTION_NODE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void comment(char[] ch, int start, int length) throws SAXException
	{
		super.comment(ch, start, length);
		
		if (!inDTD())
		{
			currentNode.appendChild(document.createComment(new String(ch, start, length)));
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public Document getDocument()
	{
		return document;
	}
	
	// private methods --------------------------------------------------------
	
	private void flushBuffer(short nodeType)
	{
		int length = builder.length();
		if (length == 0)
		{
			return;
		}

		String data = builder.toString();
		
		// add character data node
		Node node;
		switch (nodeType)
		{
			case Node.TEXT_NODE:
				node = document.createTextNode(data);
				break;
				
			case Node.CDATA_SECTION_NODE:
				node = document.createCDATASection(data);
				break;
				
			default:
				throw new IllegalArgumentException("Unknown node type: " + nodeType);
		}
		currentNode.appendChild(node);
		
		// clear character data buffer
		builder.delete(0, length);
	}
}
