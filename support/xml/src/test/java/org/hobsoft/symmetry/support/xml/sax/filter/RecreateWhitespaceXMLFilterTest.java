/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/filter/RecreateWhitespaceXMLFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax.filter;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

/**
 * Tests <code>RecreateWhitespaceXMLFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: RecreateWhitespaceXMLFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see RecreateWhitespaceXMLFilter
 */
public class RecreateWhitespaceXMLFilterTest extends AbstractXMLFilterTest
{
	// TODO: complete tests
	
	// AbstractXMLFilterTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected XMLFilter createXMLFilter(XMLReader reader)
	{
		return new RecreateWhitespaceXMLFilter(reader, getDTDProvider());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void emptyElement() throws SAXException, IOException
	{
		assertXML("<element/>\r\n", "<element/>");
	}
	
	@Test
	public void elementWithText() throws SAXException, IOException
	{
		assertXML("<element>\r\n   text\r\n</element>\r\n", "<element>text</element>");
	}
	
	@Test
	public void elementWithEmptyChild() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child/>\r\n</element>\r\n", "<element><child/></element>");
	}
	
	@Test
	public void elementWithTwoEmptyChildren() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child/>\r\n   <child/>\r\n</element>\r\n", "<element><child/><child/></element>");
	}
	
	@Test
	public void elementWithChildWithText() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child>\r\n      text\r\n   </child>\r\n</element>\r\n",
			"<element><child>text</child></element>");
	}
	
	@Test
	public void elementWithTwoChildrenWithText() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child>\r\n      text\r\n   </child>\r\n   <child>\r\n      text\r\n   </child>\r\n"
			+ "</element>\r\n", "<element><child>text</child><child>text</child></element>");
	}
	
	@Test
	public void elementWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element/>\r\n", "<element> </element>");
	}
	
	@Test
	public void elementWithAnyDeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element> </element>\r\n", "<!DOCTYPE element [<!ELEMENT element ANY>]><element> </element>");
	}
	
	@Test
	public void elementWithChoiceDeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element/>\r\n", "<!DOCTYPE element [<!ELEMENT element (a|b)*>]><element> </element>");
	}
	
	@Test
	public void elementWithPCDATADeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element> </element>\r\n", "<!DOCTYPE element [<!ELEMENT element (#PCDATA)>]><element> </element>");
	}
	
	@Test
	public void elementWithPCDATADecl() throws SAXException, IOException
	{
		assertXML("<element>text</element>\r\n", "<!DOCTYPE element [<!ELEMENT element (#PCDATA)>]>"
			+ "<element>text</element>");
	}
	
	@Test
	public void elementWithChildWithPCDATADecl() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child>text</child>\r\n</element>\r\n",
			"<!DOCTYPE element [<!ELEMENT child (#PCDATA)>]><element><child>text</child></element>");
	}
	
	@Test
	public void elementWithTwoChildrenWithPCDATADecl() throws SAXException, IOException
	{
		assertXML("<element>\r\n   <child>text</child>\r\n   <child>text</child>\r\n</element>\r\n",
			"<!DOCTYPE element [<!ELEMENT child (#PCDATA)>]><element><child>text</child><child>text</child></element>");
	}
}
