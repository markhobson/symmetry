/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/filter/AbstractXMLFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax.filter;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.hobsoft.symmetry.support.xml.sax.DTDDeclHandler;
import org.hobsoft.symmetry.support.xml.sax.SAX;
import org.hobsoft.symmetry.support.xml.sax.SAX2Stream;
import org.junit.Before;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractXMLFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractXMLFilterTest
{
	// constants --------------------------------------------------------------
	
	protected static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private XMLReader reader;
	
	private XMLFilter filter;
	
	private DTDDeclHandler dtdDeclHandler;
	
	private ByteArrayOutputStream out;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpXMLFilter() throws ParserConfigurationException, SAXException
	{
		out = new ByteArrayOutputStream();
		
		SAX2Stream sax2stream = new SAX2Stream(out);
		sax2stream.setOmitXMLDeclaration(true);
		sax2stream.setOmitDocType(true);
		
		dtdDeclHandler = new DTDDeclHandler();
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		reader = parser.getXMLReader();
		
		filter = createXMLFilter(reader);

		filter.setContentHandler(sax2stream);
		filter.setProperty(SAX.Property.LEXICAL_HANDLER, sax2stream);
		filter.setProperty(SAX.Property.DECLARATION_HANDLER, dtdDeclHandler);
		
		initFilters(filter);
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract XMLFilter createXMLFilter(XMLReader reader);
	
	protected ContentHandler getContentHandler()
	{
		return reader.getContentHandler();
	}
	
	protected DTDHandler getDTDHandler()
	{
		return reader.getDTDHandler();
	}
	
	protected EntityResolver getEntityResolver()
	{
		return reader.getEntityResolver();
	}
	
	protected ErrorHandler getErrorHandler()
	{
		return reader.getErrorHandler();
	}
	
	protected LexicalHandler getLexicalHandler() throws SAXNotRecognizedException, SAXNotSupportedException
	{
		return (LexicalHandler) reader.getProperty(SAX.Property.LEXICAL_HANDLER);
	}
	
	protected DeclHandler getDeclHandler() throws SAXNotRecognizedException, SAXNotSupportedException
	{
		return (DeclHandler) reader.getProperty(SAX.Property.DECLARATION_HANDLER);
	}
	
	protected DTDProvider getDTDProvider()
	{
		return dtdDeclHandler;
	}
	
	protected void assertXML(String expected, String xml) throws SAXException, IOException
	{
		filter.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));
		
		assertXML(expected);
	}

	protected void assertXML(String expected)
	{
		assertEquals(expected, out.toString());
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Chains <code>XMLFilter</code>s together according to their hierarchy.
	 * 
	 * This allows filters to be used with having to call <code>parse()</code> first.
	 * 
	 * See {@code org.xml.sax.helpers.XMLFilterImpl#setupParse()}.
	 * 
	 * @param filter
	 *            the filter to chain together
	 */
	private void initFilters(XMLFilter filter)
	{
		XMLReader parent = filter.getParent();
		
		if (parent != null)
		{
			if (filter instanceof EntityResolver)
			{
				parent.setEntityResolver((EntityResolver) filter);
			}
			
			if (filter instanceof DTDHandler)
			{
				parent.setDTDHandler((DTDHandler) filter);
			}
			
			if (filter instanceof ContentHandler)
			{
				parent.setContentHandler((ContentHandler) filter);
			}
			
			if (filter instanceof ErrorHandler)
			{
				parent.setErrorHandler((ErrorHandler) filter);
			}
			
			if (parent instanceof XMLFilter)
			{
				initFilters((XMLFilter) parent);
			}
		}
	}
}
