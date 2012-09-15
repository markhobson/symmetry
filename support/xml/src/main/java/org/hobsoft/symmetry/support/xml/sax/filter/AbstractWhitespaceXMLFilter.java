/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/AbstractWhitespaceXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax.filter;

import java.util.Stack;

import org.hobsoft.symmetry.support.xml.dtd.DTD;
import org.hobsoft.symmetry.support.xml.dtd.DTDAnyContent;
import org.hobsoft.symmetry.support.xml.dtd.DTDContent;
import org.hobsoft.symmetry.support.xml.dtd.DTDElement;
import org.hobsoft.symmetry.support.xml.dtd.DTDMixedContent;
import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class AbstractWhitespaceXMLFilter extends XMLFilterImpl
{
	// TODO: reuse element stack implementation from AbstractSAXHandler
	
	// fields -----------------------------------------------------------------

	private final DTDProvider dtdProvider;

	private final Stack<String> elements;
	
	private final StringBuilder buffer;

	// constructors -----------------------------------------------------------
	
	public AbstractWhitespaceXMLFilter(XMLReader parent)
	{
		this(parent, null);
	}

	public AbstractWhitespaceXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent);
		
		this.dtdProvider = dtdProvider;
		
		elements = new Stack<String>();
		buffer = new StringBuilder();
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();

		elements.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		flushCharacterBuffer();

		super.endDocument();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		flushCharacterBuffer();
		
		elements.push(qName);

		super.startElement(uri, localName, qName, attributes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		flushCharacterBuffer();
		
		elements.pop();

		super.endElement(uri, localName, qName);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		buffer.append(ch, start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
	{
		buffer.append(ch, start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException
	{
		flushCharacterBuffer();
		
		super.processingInstruction(target, data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void skippedEntity(String name) throws SAXException
	{
		flushCharacterBuffer();
		
		super.skippedEntity(name);
	}
	
	// protected methods ------------------------------------------------------
	
	protected int getElementDepth()
	{
		return elements.size();
	}
	
	protected void ignorableWhitespaceBypass(char[] ch, int start, int length) throws SAXException
	{
		super.ignorableWhitespace(ch, start, length);
	}

	protected void flushCharacterBuffer() throws SAXException
	{
		processCharacterBuffer();
		
		buffer.delete(0, buffer.length());
	}
	
	protected void processCharacterBuffer() throws SAXException
	{
		int length = buffer.length();

		if (length > 0)
		{
			char[] chars = new char[length];
			
			buffer.getChars(0, length, chars, 0);
			
			super.characters(chars, 0, length);
		}
	}
	
	protected boolean isWhitespaceSignificant()
	{
		if (elements.isEmpty())
		{
			return false;
		}
		
		String elementName = elements.peek();
		
		return isWhitespaceSignificant(elementName);
	}
	
	protected boolean isBufferWhitespace()
	{
		return isWhitespace(buffer);
	}
	
	protected boolean isWhitespace(CharSequence chars)
	{
		for (int i = 0; i < chars.length(); i++)
		{
			if (!Character.isWhitespace(chars.charAt(i)))
			{
				return false;
			}
		}
		
		return true;
	}

	// private methods --------------------------------------------------------
	
	private boolean isWhitespaceSignificant(String elementName)
	{
		DTD dtd = getDTD();
		
		if (dtd == null)
		{
			return false;
		}
		
		DTDElement element = dtd.getElement(elementName);
		if (element == null)
		{
			return false;
		}
		
		DTDContent content = element.getContent();
		if (content == DTDAnyContent.INSTANCE || content instanceof DTDMixedContent)
		{
			return true;
		}
		
		return false;
	}
	
	private DTD getDTD()
	{
		return (dtdProvider != null) ? dtdProvider.getDTD() : null;
	}
}
