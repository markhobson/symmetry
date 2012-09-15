/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/AbstractSAXHandler.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractSAXHandler implements ContentHandler, LexicalHandler
{
	// classes ----------------------------------------------------------------
	
	private class Element
	{
		private String uri;
		private String localName;
		private String qName;
		
		public Element(String uri, String localName, String qName)
		{
			this.uri = uri;
			this.localName = localName;
			this.qName = qName;
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof Element))
			{
				return false;
			}
			
			Element element = (Element) object;
			return equals(element.uri, element.localName, element.qName);
		}
		
		public boolean equals(String uri, String localName, String qName)
		{
			return this.uri.equals(uri) && this.localName.equals(localName) && this.qName.equals(qName);
		}
		
		@Override
		public int hashCode()
		{
			return ((uri.hashCode() * 31) + localName.hashCode()) * 31 + qName.hashCode();
		}
		
		@Override
		public String toString()
		{
			return getClass().getName() + "[uri=" + uri + ",localName=" + localName + ",qName=" + qName + "]";
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private Locator locator;

	private Stack<Element> elements;

	private Map<String, String> prefixes;

	private boolean inDocument;

	private boolean inDTD;

	private boolean inCDATA;
	
	// constructors -----------------------------------------------------------
	
	public AbstractSAXHandler()
	{
		elements = new Stack<Element>();
		prefixes = new LinkedHashMap<String, String>();
		inDocument = false;
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void setDocumentLocator(Locator locator)
	{
		this.locator = locator;
	}

	/**
	 * {@inheritDoc}
	 */
	public void startDocument() throws SAXException
	{
		inDocument = true;
		inDTD = false;
		inCDATA = false;
		
		prefixes.clear();
		elements.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public void endDocument() throws SAXException
	{
		ensureDocumentStarted();
	}

	/**
	 * {@inheritDoc}
	 */
	public void startPrefixMapping(String prefix, String uri) throws SAXException
	{
		ensureDocumentStarted();

		prefixes.put(prefix, uri);
	}

	/**
	 * {@inheritDoc}
	 */
	public void endPrefixMapping(String prefix) throws SAXException
	{
		ensureDocumentStarted();
		
		prefixes.remove(prefix);
	}

	/**
	 * {@inheritDoc}
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if (uri == null)
		{
			throw new SAXException("uri cannot be null");
		}
		
		if (localName == null)
		{
			throw new SAXException("localName cannot be null");
		}
		
		if (qName == null)
		{
			throw new SAXException("qName cannot be null");
		}
		
		if (attributes == null)
		{
			throw new SAXException("attributes cannot be null");
		}
		
		ensureDocumentStarted();
		
		elements.push(new Element(uri, localName, qName));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		ensureDocumentStarted();
		
		if (elements.empty())
		{
			throw new SAXException("Element not started");
		}
		
		Element element = elements.pop();
		
		if (!element.equals(uri, localName, qName))
		{
			throw new SAXException("End element does not match start element");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		ensureDocumentStarted();
	}

	/**
	 * {@inheritDoc}
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
	{
		ensureDocumentStarted();
	}

	/**
	 * {@inheritDoc}
	 */
	public void processingInstruction(String target, String data) throws SAXException
	{
		ensureDocumentStarted();
	}

	/**
	 * {@inheritDoc}
	 */
	public void skippedEntity(String name) throws SAXException
	{
		ensureDocumentStarted();
	}

	// LexicalHandler methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public void startDTD(String name, String publicId, String systemId) throws SAXException
	{
		inDTD = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void endDTD() throws SAXException
	{
		inDTD = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void startEntity(String name) throws SAXException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	public void endEntity(String name) throws SAXException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	public void startCDATA() throws SAXException
	{
		ensureDocumentStarted();

		inCDATA = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void endCDATA() throws SAXException
	{
		ensureDocumentStarted();

		if (!inCDATA)
		{
			throw new SAXException("CDATA section not started");
		}

		inCDATA = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void comment(char[] ch, int start, int length) throws SAXException
	{
		ensureDocumentStarted();
	}
	
	// public methods ---------------------------------------------------------
	
	public Locator getDocumentLocator()
	{
		return locator;
	}

	// protected methods ------------------------------------------------------
	
	protected boolean inDTD()
	{
		return inDTD;
	}
	
	protected boolean inCDATA()
	{
		return inCDATA;
	}
	
	protected Map<String, String> getPrefixMap()
	{
		return prefixes;
	}
	
	// private methods --------------------------------------------------------
	
	private void ensureDocumentStarted() throws SAXException
	{
		if (!inDocument)
		{
			throw new SAXException("Document not started");
		}
	}
}
