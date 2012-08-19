/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/CompoundXMLReaderTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests <code>CompoundXMLReader</code>.
 * 
 * @author Mark Hobson
 * @version $Id: CompoundXMLReaderTest.java 69823 2010-01-21 17:58:46Z mark@IIZUKA.CO.UK $
 * @see CompoundXMLReader
 */
public class CompoundXMLReaderTest
{
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private CompoundXMLReader reader;
	
	private MockSAX sax;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		sax = new MockSAX();
		
		reader = new CompoundXMLReader();
		reader.setContentHandler(sax.getContentHandler());
	}
	
	@After
	public void tearDown()
	{
		sax.verify();
	}
	
	// tests ------------------------------------------------------------------
	
	/**
	 * Tests using a single <code>XMLReader</code>.
	 * 
	 * @throws SAXException
	 *             if a SAX error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Test
	public void singleXMLReader() throws SAXException, IOException
	{
		reader.addXMLReader(new MockXMLReader(), new InputSource("a"));
		
		sax.startDocument();
		sax.startElement("", "document", "document", EMPTY_ATTRIBUTES);
		sax.startElement("", "a", "a", EMPTY_ATTRIBUTES);
		sax.endElement("", "a", "a");
		sax.endElement("", "document", "document");
		sax.endDocument();
		
		reader.parse("");
	}
	
	/**
	 * Tests using a multiple <code>XMLReader</code>s.
	 * 
	 * @throws SAXException
	 *             if a SAX error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Test
	public void multipleXMLReaders() throws SAXException, IOException
	{
		reader.addXMLReader(new MockXMLReader(), new InputSource("a"));
		reader.addXMLReader(new MockXMLReader(), new InputSource("b"));
		reader.addXMLReader(new MockXMLReader(), new InputSource("c"));
		
		sax.startDocument();
		sax.startElement("", "document", "document", EMPTY_ATTRIBUTES);
		sax.startElement("", "a", "a", EMPTY_ATTRIBUTES);
		sax.endElement("", "a", "a");
		sax.startElement("", "b", "b", EMPTY_ATTRIBUTES);
		sax.endElement("", "b", "b");
		sax.startElement("", "c", "c", EMPTY_ATTRIBUTES);
		sax.endElement("", "c", "c");
		sax.endElement("", "document", "document");
		sax.endDocument();
		
		reader.parse("");
	}
}
