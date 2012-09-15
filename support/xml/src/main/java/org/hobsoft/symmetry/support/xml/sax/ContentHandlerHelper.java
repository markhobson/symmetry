/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/ContentHandlerHelper.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Wraps a content handler to provide convenience methods. 
 * 
 * @author Mark Hobson
 * @version $Id: ContentHandlerHelper.java 51307 2008-07-31 16:13:42Z mark@IIZUKA.CO.UK $
 */
public class ContentHandlerHelper extends DelegatingContentHandler
{
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private final String defaultUri;

	// constructors -----------------------------------------------------------
	
	public ContentHandlerHelper(ContentHandler handler)
	{
		this(handler, null);
	}
	
	public ContentHandlerHelper(ContentHandler handler, String defaultUri)
	{
		super(handler);
		
		if (defaultUri == null)
		{
			defaultUri = "";
		}
		
		this.defaultUri = defaultUri;
	}
	
	// public methods ---------------------------------------------------------
	
	public void startElement(String elementName) throws SAXException
	{
		startElement(elementName, EMPTY_ATTRIBUTES);
	}
	
	public void startElement(String elementName, String attributeName, String attributeValue) throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		addAttribute(attributes, attributeName, attributeValue);
		
		startElement(elementName, attributes);
	}
	
	public void startElement(String elementName, Attributes attributes) throws SAXException
	{
		startElement(defaultUri, elementName, elementName, attributes);
	}
	
	public void endElement(String elementName) throws SAXException
	{
		endElement(defaultUri, elementName, elementName);
	}

	public void emptyElement(String elementName) throws SAXException
	{
		emptyElement(elementName, EMPTY_ATTRIBUTES);
	}
	
	public void emptyElement(String elementName, Attributes attributes) throws SAXException
	{
		startElement(elementName, attributes);
		endElement(elementName);
	}
	
	public void textElement(String elementName, String text) throws SAXException
	{
		textElement(elementName, EMPTY_ATTRIBUTES, text);
	}
	
	public void textElement(String elementName, Attributes attributes, String text) throws SAXException
	{
		startElement(elementName, attributes);
		characters(text);
		endElement(elementName);
	}
	
	public void characters(String text) throws SAXException
	{
		characters(text.toCharArray(), 0, text.length());
	}
	
	// private methods --------------------------------------------------------
	
	private static void addAttribute(AttributesImpl attributes, String name, String value)
	{
		attributes.addAttribute("", name, name, "CDATA", value);
	}
}
