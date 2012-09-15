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
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * A content handler that simply delegates to another content handler.
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingContentHandler implements ContentHandler
{
	// fields -----------------------------------------------------------------
	
	private final ContentHandler handler;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingContentHandler(ContentHandler handler)
	{
		if (handler == null)
		{
			throw new NullPointerException("handler cannot be null");
		}
		
		this.handler = handler;
	}

	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void setDocumentLocator(Locator locator)
	{
		handler.setDocumentLocator(locator);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void startDocument() throws SAXException
	{
		handler.startDocument();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void endDocument() throws SAXException
	{
		handler.endDocument();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void startPrefixMapping(String prefix, String uri) throws SAXException
	{
		handler.startPrefixMapping(prefix, uri);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void endPrefixMapping(String prefix) throws SAXException
	{
		handler.endPrefixMapping(prefix);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
	{
		handler.startElement(uri, localName, qName, atts);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		handler.endElement(uri, localName, qName);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		handler.characters(ch, start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
	{
		handler.ignorableWhitespace(ch, start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void processingInstruction(String target, String data) throws SAXException
	{
		handler.processingInstruction(target, data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void skippedEntity(String name) throws SAXException
	{
		handler.skippedEntity(name);
	}
}
