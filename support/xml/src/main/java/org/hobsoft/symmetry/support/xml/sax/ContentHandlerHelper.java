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

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Wraps a content handler to provide convenience methods. 
 * 
 * @author Mark Hobson
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
