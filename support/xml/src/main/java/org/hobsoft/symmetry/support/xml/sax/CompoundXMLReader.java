/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/CompoundXMLReader.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * An <code>XMLReader</code> that can concatenate the SAX events from multiple other <code>XMLReader</code>s whilst
 * keeping the events well-formed.
 * 
 * @author Mark Hobson
 */
public class CompoundXMLReader extends XMLFilterImpl
{
	// TODO: should we extend AbstractXMLReader instead?
	
	// fields -----------------------------------------------------------------
	
	/**
	 * A list of <code>XMLReader</code>s to parse.
	 */
	private List<XMLReader> readers;
	
	/**
	 * A list of <code>InputSource</code>s to pass to the
	 * <code>XMLReader</code>s.
	 */
	private List<InputSource> inputs;
	
	/**
	 * The index of the current <code>XMLReader</code> within the list.
	 */
	private int index;
	
	/**
	 * The total number of <code>XMLReader</code>s at parse time.
	 */
	private int count;
	
	/**
	 * The current element depth, where zero is the document element.
	 */
	private int depth;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a <code>CompoundXMLReader</code>.
	 */
	public CompoundXMLReader()
	{
		readers = new ArrayList<XMLReader>();
		inputs = new ArrayList<InputSource>();
	}
	
	// XMLReader methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		for (XMLReader reader : readers)
		{
			reader.setFeature(name, value);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse(InputSource input) throws SAXException, IOException
	{
		count = readers.size();
		
		for (index = 0; index < count; index++)
		{
			XMLReader reader = readers.get(index);
			InputSource in = inputs.get(index);
			
			// route reader to this filter
			reader.setEntityResolver(this);
			reader.setDTDHandler(this);
			reader.setContentHandler(this);
			reader.setErrorHandler(this);
			
			reader.parse(in);
		}
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		// only start document for first reader
		if (index == 0)
		{
			super.startDocument();
		}

		// reset element depth
		depth = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		// only end document for last reader
		if (index == count - 1)
		{
			super.endDocument();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
	{
		// we want to filter out all start document elements apart from the first readers
		if (index == 0 || depth > 0)
		{
			super.startElement(uri, localName, qName, atts);
		}
		
		// increment element depth
		depth++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		// decrement element depth
		depth--;
		
		// we want to filter out all end document elements apart from the last readers
		if (index == count - 1 || depth > 0)
		{
			super.endElement(uri, localName, qName);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Adds the specified <code>XMLReader</code> to the list of <code>XMLReader</code>s to be concatenated at parse
	 * time.
	 * 
	 * @param reader
	 *            the <code>XMLReader</code> to add
	 * @param input
	 *            the <code>InputSource</code> to pass to the specified <code>XMLReader</code> at parse time
	 */
	public void addXMLReader(XMLReader reader, InputSource input)
	{
		readers.add(reader);
		inputs.add(input);
	}
}
