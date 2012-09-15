/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/DelegatingContentHandler.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: DelegatingContentHandler.java 51272 2008-07-30 14:08:56Z mark@IIZUKA.CO.UK $
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
