/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/MockXMLReader.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A mock <code>XMLReader</code> reader that produces an expected series of SAX events to be used for testing.
 * <p>
 * More specifically, upon calling <code>XMLReader.parse()</code> this reader will produce the events for the document
 * <code>&lt;document&gt;&lt;<i>name</i>/&gt;&lt;document&gt;</code> where <i>name</i> is the systemId of the
 * <code>InputSource</code>.
 * </p>
 * 
 * @author Mark Hobson
 * @version $Id: MockXMLReader.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class MockXMLReader extends AbstractXMLReader
{
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	private static final String DOCUMENT_ELEMENT_NAME = "document";
	
	// XMLReader methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void parse(InputSource input) throws IOException, SAXException
	{
		String name = input.getSystemId();
		
		ContentHandler handler = getContentHandler();
		
		handler.startDocument();
		handler.startElement("", DOCUMENT_ELEMENT_NAME, DOCUMENT_ELEMENT_NAME, EMPTY_ATTRIBUTES);
		handler.startElement("", name, name, EMPTY_ATTRIBUTES);
		handler.endElement("", name, name);
		handler.endElement("", DOCUMENT_ELEMENT_NAME, DOCUMENT_ELEMENT_NAME);
		handler.endDocument();
	}
}
