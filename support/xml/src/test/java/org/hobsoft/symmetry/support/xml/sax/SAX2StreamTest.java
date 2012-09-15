/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/SAX2StreamTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>SAX2Stream</code>.
 * 
 * @author Mark Hobson
 * @see SAX2Stream
 */
public class SAX2StreamTest
{
	// TODO: complete tests
	
	// constants --------------------------------------------------------------
	
	private static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();
	
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream out;
	
	private SAX2Stream sax;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		out = new ByteArrayOutputStream();
		sax = new SAX2Stream(out);
	}
	
	// document tests ---------------------------------------------------------
	
	@Test
	public void document() throws SAXException
	{
		sax.startDocument();
		sax.endDocument();
		
		assertXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
	}
	
	@Test
	public void documentWithCharset() throws SAXException
	{
		sax.setEncodingCharset("ISO-8859-1");
		sax.startDocument();
		sax.endDocument();
		
		assertXML("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n");
	}
	
	@Test
	public void documentOmitXMLDeclaration() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.endDocument();
		
		assertXML("");
	}
	
	@Test(expected = SAXException.class)
	public void documentMissingStart() throws SAXException
	{
		try
		{
			sax.endDocument();
		}
		catch (SAXException exception)
		{
			assertEquals("Document not started", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void documentWithDoctype() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startDTD("root", "publicId", "systemId");
		sax.endDTD();
		sax.endDocument();
		
		assertXML("<!DOCTYPE root PUBLIC \"publicId\" \"systemId\">\r\n");
	}
	
	// element tests ----------------------------------------------------------
	
	@Test
	public void emptyElement() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertXML("<root/>");
	}
	
	@Test
	public void emptyNamespacedElement() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startPrefixMapping("", "uri");
		sax.startElement("uri", "root", "root", EMPTY_ATTRIBUTES);
		sax.endElement("uri", "root", "root");
		sax.endPrefixMapping("");
		sax.endDocument();
		
		assertXML("<root xmlns=\"uri\"/>");
	}
	
	@Test
	public void emptyPrefixedNamespacedElement() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startPrefixMapping("p", "uri");
		sax.startElement("uri", "root", "p:root", EMPTY_ATTRIBUTES);
		sax.endElement("uri", "root", "p:root");
		sax.endPrefixMapping("p");
		sax.endDocument();
		
		assertXML("<p:root xmlns:p=\"uri\"/>");
	}
	
	@Test
	public void emptyElementWithAttribute() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "name", "CDATA", "value");
		
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", attributes);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertXML("<root name=\"value\"/>");
	}

	// TODO: this should write out a xmlns prefix for the attribute
//	@Test
//	public void emptyElementWithNamespacedAttribute() throws SAXException
//	{
//		AttributesImpl attributes = new AttributesImpl();
//		attributes.addAttribute("uri", "name", "name", "CDATA", "value");
//		
//		sax.setOmitXMLDeclaration(true);
//		sax.startDocument();
//		sax.startElement("", "", "root", attributes);
//		sax.endElement("", "", "root");
//		sax.endDocument();
//		
//		assertXML("<root xmlns:ns0=\"uri\" ns0:name=\"value\"/>");
//	}

	// TODO: this should write out a xmlns declaration for the prefix
//	@Test	
//	public void emptyElementWithPrefixedNamespacedAttribute() throws SAXException
//	{
//		AttributesImpl attributes = new AttributesImpl();
//		attributes.addAttribute("uri", "name", "p:name", "CDATA", "value");
//		
//		sax.setOmitXMLDeclaration(true);
//		sax.startDocument();
//		sax.startElement("", "", "root", attributes);
//		sax.endElement("", "", "root");
//		sax.endDocument();
//		
//		assertXML("<root xmlns:p=\"uri\" p:name=\"value\"/>");
//	}

	@Test
	public void elementWithText() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.characters("text".toCharArray(), 0, 4);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertXML("<root>text</root>");
	}
	
	@Test
	public void elementWithIllegalText() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.characters("<>&\"".toCharArray(), 0, 4);
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertXML("<root>&lt;&gt;&amp;&quot;</root>");
	}
	
	@Test(expected = SAXException.class)
	public void elementWithInvalidCharacter() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		
		try
		{
			sax.characters(new char[] {0}, 0, 1);
		}
		catch (SAXException exception)
		{
			assertEquals(exception.getMessage(), "Invalid XML character: 0x0");
			
			throw exception;
		}
	}
	
	@Test
	public void elementWithCDATA() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "", "root", EMPTY_ATTRIBUTES);
		sax.startCDATA();
		sax.characters("text".toCharArray(), 0, 4);
		sax.endCDATA();
		sax.endElement("", "", "root");
		sax.endDocument();
		
		assertXML("<root><![CDATA[text]]></root>");
	}
	
	@Test(expected = SAXException.class)
	public void elementMissingStart() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();

		try
		{
			sax.endElement("", "", "root");
		}
		catch (SAXException exception)
		{
			assertEquals("Element not started", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = SAXException.class)
	public void nonWellFormedElement() throws SAXException
	{
		sax.setOmitXMLDeclaration(true);
		sax.startDocument();
		sax.startElement("", "start", "start", EMPTY_ATTRIBUTES);

		try
		{
			sax.endElement("", "", "end");
		}
		catch (SAXException exception)
		{
			assertEquals("End element does not match start element", exception.getMessage());
			
			throw exception;
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected void assertXML(String xml)
	{
		assertEquals(xml, out.toString());
	}
}
