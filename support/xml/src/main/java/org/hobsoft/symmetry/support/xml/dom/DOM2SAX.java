/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/DOM2SAX.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import java.io.IOException;

import org.hobsoft.symmetry.support.xml.sax.AbstractXMLReader;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DOM2SAX extends AbstractXMLReader
{
	// constants --------------------------------------------------------------
	
	public static final String NODE_PROPERTY = "http://www.iizuka.co.uk/sax/properties/dom2sax-node";
	
	// fields -----------------------------------------------------------------
	
	private final AttributesImpl attributes;

	private Node node;

	// constructors -----------------------------------------------------------
	
	public DOM2SAX()
	{
		attributes = new AttributesImpl();
	}
	
	// XMLReader methods ------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public void parse(InputSource input) throws IOException, SAXException
	{
		Node node = (Node) getProperty(NODE_PROPERTY);
		
		parse(node);
	}
	
	// AbstractXMLReader methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		if (NODE_PROPERTY.equals(name))
		{
			return node;
		}
		
		return super.getProperty(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		if (NODE_PROPERTY.equals(name))
		{
			if (!(value instanceof Node))
			{
				throw new SAXNotSupportedException("Node property value must be an instance of Node");
			}
			
			this.node = (Node) value;
		}
		else
		{
			super.setProperty(name, value);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private void parse(Node node) throws SAXException
	{
		attributes.clear();
		
		parseNode(node);
	}
	
	private void parseNode(Node node) throws SAXException
	{
		if (node == null)
		{
			return;
		}
		
		switch (node.getNodeType())
		{
			case Node.ATTRIBUTE_NODE:
				parseAttr((Attr) node);
				break;
				
			case Node.CDATA_SECTION_NODE:
				parseCDATASection((CDATASection) node);
				break;
				
			case Node.COMMENT_NODE:
				parseComment((Comment) node);
				break;
				
			case Node.DOCUMENT_FRAGMENT_NODE:
				parseDocumentFragment((DocumentFragment) node);
				break;
				
			case Node.DOCUMENT_NODE:
				parseDocument((Document) node);
				break;
				
			case Node.DOCUMENT_TYPE_NODE:
				parseDocumentType((DocumentType) node);
				break;
				
			case Node.ELEMENT_NODE:
				parseElement((Element) node);
				break;
				
			case Node.ENTITY_NODE:
				parseEntity((Entity) node);
				break;
				
			case Node.ENTITY_REFERENCE_NODE:
				parseEntityReference((EntityReference) node);
				break;
				
			case Node.NOTATION_NODE:
				parseNotation((Notation) node);
				break;
				
			case Node.PROCESSING_INSTRUCTION_NODE:
				parseProcessingInstruction((ProcessingInstruction) node);
				break;
				
			case Node.TEXT_NODE:
				parseText((Text) node);
				break;
			
			default:
				break;
		}
	}
	
	@SuppressWarnings("unused")
	private void parseAttr(Attr attr) throws SAXException
	{
		// TODO: implement
		
		throw new UnsupportedOperationException();
	}
	
	private void parseCDATASection(CDATASection cdata) throws SAXException
	{
		if (getLexicalHandler() != null)
		{
			getLexicalHandler().startCDATA();
		}
		
		char[] chars = cdata.getData().toCharArray();
		getContentHandler().characters(chars, 0, chars.length);
		
		if (getLexicalHandler() != null)
		{
			getLexicalHandler().endCDATA();
		}
	}
	
	private void parseComment(Comment comment) throws SAXException
	{
		if (getLexicalHandler() != null)
		{
			char[] chars = comment.getData().toCharArray();
			getLexicalHandler().comment(chars, 0, chars.length);
		}
	}

	private void parseDocumentFragment(DocumentFragment fragment) throws SAXException
	{
		// start document
		getContentHandler().startDocument();
		
		// parse children
		parseNodeList(fragment.getChildNodes());
		
		// end document
		getContentHandler().endDocument();
	}
		
	private void parseDocument(Document document) throws SAXException
	{
		// start document
		getContentHandler().startDocument();
		
		NamedNodeMap attrMap = document.getAttributes();
		
		if (attrMap != null)
		{
			parseDocumentType((DocumentType) attrMap.getNamedItem("doctype"));
		}
		
		// parse children
		parseNodeList(document.getChildNodes());
		
		// end document
		getContentHandler().endDocument();
	}
	
	private void parseDocumentType(DocumentType doctype) throws SAXException
	{
		if (getLexicalHandler() != null && doctype != null)
		{
			getLexicalHandler().startDTD(doctype.getName(), doctype.getPublicId(), doctype.getSystemId());
			getLexicalHandler().endDTD();
		}
	}
	
	private void parseElement(Element element) throws SAXException
	{
		// TODO: write xmlns attributes as prefix mappings first
		
		// create attributes
		attributes.clear();
		NamedNodeMap attrMap = element.getAttributes();
		for (int i = 0; i < attrMap.getLength(); i++)
		{
			Attr attr = (Attr) attrMap.item(i);
			String attrURI = unnull(attr.getNamespaceURI());
			String attrLocalName = unnull(attr.getLocalName());
			String attrQName = attr.getName();
			// FIXME: supply proper attribute type
			String attrType = "CDATA";
			String attrValue = attr.getValue();
			attributes.addAttribute(attrURI, attrLocalName, attrQName, attrType, attrValue);
		}
		
		// start element
		String uri = unnull(element.getNamespaceURI());
		String localName = unnull(element.getLocalName());
		String qName = unnull(element.getTagName());
		getContentHandler().startElement(uri, localName, qName, attributes);

		// parse children
		parseNodeList(element.getChildNodes());
		
		// end children
		getContentHandler().endElement(uri, localName, qName);
	}
	
	@SuppressWarnings("unused")
	private void parseEntity(Entity entity) throws SAXException
	{
		// TODO: implement
		
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("unused")
	private void parseEntityReference(EntityReference entityref) throws SAXException
	{
		// TODO: implement
		
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("unused")
	private void parseNotation(Notation notation) throws SAXException
	{
		// TODO: implement
		
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("unused")
	private void parseProcessingInstruction(ProcessingInstruction pi) throws SAXException
	{
		// TODO: implement
		
		throw new UnsupportedOperationException();
	}
	
	private void parseText(Text text) throws SAXException
	{
		char[] chars = text.getData().toCharArray();
		getContentHandler().characters(chars, 0, chars.length);
	}
	
	private void parseNodeList(NodeList nodeList) throws SAXException
	{
		int length = nodeList.getLength();
		
		for (int i = 0; i < length; i++)
		{
			parseNode(nodeList.item(i));
		}
	}
	
	private String unnull(String string)
	{
		return (string == null) ? "" : string;
	}
}
