/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/filter/ExplicitDTDXMLFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests <code>ExplicitDTDXMLFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: ExplicitDTDXMLFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see ExplicitDTDXMLFilter
 */
public class ExplicitDTDXMLFilterTest extends AbstractXMLFilterTest
{
	// AbstractXMLFilterTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected XMLFilter createXMLFilter(XMLReader reader)
	{
		return new ExplicitDTDXMLFilter(reader, getDTDProvider());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void elementWithRequiredAttribute() throws SAXException
	{
		try
		{
			startDocument();
			attributeDTD("element", "name", "CDATA", "#REQUIRED", "");
			emptyElement("element");
			endDocument();
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("Attribute is #REQUIRED: name", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void elementWithRequiredAttributeWhenSpecified() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "value");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", "#REQUIRED", "");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"value\"/>");
	}
	
	@Test
	public void elementWithImpliedAttribute() throws SAXException
	{
		startDocument();
		attributeDTD("element", "name", "CDATA", "#IMPLIED", "");
		emptyElement("element");
		endDocument();
		
		assertXML("<element name=\"\"/>");
	}
	
	@Test
	public void elementWithImpliedAttributeWhenSpecifiedEmpty() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", "#IMPLIED", "");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"\"/>");
	}
	
	@Test
	public void elementWithImpliedAttributeWhenSpecified() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "value");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", "#IMPLIED", "");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"value\"/>");
	}
	
	@Test
	public void elementWithFixedAttribute() throws SAXException
	{
		startDocument();
		attributeDTD("element", "name", "CDATA", "#FIXED", "fixedValue");
		emptyElement("element");
		endDocument();
		
		assertXML("<element name=\"fixedValue\"/>");
	}
	
	@Test
	public void elementWithFixedAttributeWhenSpecified() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "value");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", "#FIXED", "fixedValue");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"fixedValue\"/>");
	}
	
	@Test
	public void elementWithDefaultAttribute() throws SAXException
	{
		startDocument();
		attributeDTD("element", "name", "CDATA", null, "defaultValue");
		emptyElement("element");
		endDocument();
		
		assertXML("<element name=\"defaultValue\"/>");
	}
	
	@Test
	public void elementWithDefaultAttributeWhenDefaultSpecified() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "defaultValue");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", null, "defaultValue");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"defaultValue\"/>");
	}
	
	@Test
	public void elementWithDefaultAttributeWhenSpecified() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "name", "name", "CDATA", "value");
		
		startDocument();
		attributeDTD("element", "name", "CDATA", null, "defaultValue");
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element name=\"value\"/>");
	}
	
	@Test
	public void elementAttributeOrder() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "first", "first", "CDATA", "firstValue");
		attributes.addAttribute("", "second", "second", "CDATA", "secondValue");
		
		startDocument();
		startDTD("element");
		attributeDecl("element", "first", "CDATA", null, "");
		attributeDecl("element", "second", "CDATA", null, "");
		endDTD();
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element first=\"firstValue\" second=\"secondValue\"/>");
	}
	
	@Test
	public void elementAttributeOrderWhenSpecifiedInReverse() throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "second", "second", "CDATA", "secondValue");
		attributes.addAttribute("", "first", "first", "CDATA", "firstValue");
		
		startDocument();
		startDTD("element");
		attributeDecl("element", "first", "CDATA", null, "");
		attributeDecl("element", "second", "CDATA", null, "");
		endDTD();
		emptyElement("element", attributes);
		endDocument();
		
		assertXML("<element first=\"firstValue\" second=\"secondValue\"/>");
	}
	
	@Test
	public void undefinedElement() throws SAXException
	{
		startDocument();
		emptyElement("element");
		endDocument();
		
		assertXML("<element/>");
	}
	
	// private methods --------------------------------------------------------
	
	private void startDocument() throws SAXException
	{
		getContentHandler().startDocument();
	}
	
	private void endDocument() throws SAXException
	{
		getContentHandler().endDocument();
	}
	
	private void attributeDTD(String eName, String aName, String type, String mode, String value) throws SAXException
	{
		startDTD(eName);
		attributeDecl(eName, aName, type, mode, value);
		endDTD();
	}
	
	private void startDTD(String root) throws SAXException
	{
		getLexicalHandler().startDTD(root, "publicId", "systemId");
	}
	
	private void endDTD() throws SAXException
	{
		getLexicalHandler().endDTD();
	}
	
	private void attributeDecl(String eName, String aName, String type, String mode, String value) throws SAXException
	{
		getDeclHandler().attributeDecl(eName, aName, type, mode, value);
	}
	
	private void emptyElement(String localName) throws SAXException
	{
		emptyElement(localName, EMPTY_ATTRIBUTES);
	}
	
	private void emptyElement(String localName, Attributes attributes) throws SAXException
	{
		getContentHandler().startElement("", localName, localName, attributes);
		getContentHandler().endElement("", localName, localName);
	}
}
