/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/filter/IgnoreWhitespaceXMLFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax.filter;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

/**
 * Tests <code>IgnoreWhitespaceXMLFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: IgnoreWhitespaceXMLFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see IgnoreWhitespaceXMLFilter
 */
public class IgnoreWhitespaceXMLFilterTest extends AbstractXMLFilterTest
{
	// AbstractXMLFilterTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected XMLFilter createXMLFilter(XMLReader reader)
	{
		return new IgnoreWhitespaceXMLFilter(reader, getDTDProvider());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void emptyElement() throws SAXException, IOException
	{
		assertXML("<element/>", "<element/>");
	}
	
	@Test
	public void elementWithWhitespaceSpace() throws SAXException, IOException
	{
		assertXML("<element/>", "<element> </element>");
	}
	
	@Test
	public void elementWithWhitespaceSpaceMultiple() throws SAXException, IOException
	{
		assertXML("<element/>", "<element>   </element>");
	}
	
	@Test
	public void elementWithWhitespaceTab() throws SAXException, IOException
	{
		assertXML("<element/>", "<element>\t</element>");
	}
	
	@Test
	public void elementWithWhitespaceTabMultiple() throws SAXException, IOException
	{
		assertXML("<element/>", "<element>\t\t\t</element>");
	}
	
	@Test
	public void elementWithWhitespaceSpaceTab() throws SAXException, IOException
	{
		assertXML("<element/>", "<element> \t</element>");
	}
	
	@Test
	public void elementWithText() throws SAXException, IOException
	{
		assertXML("<element>text</element>", "<element>text</element>");
	}
	
	@Test
	public void elementWithTextAndWhitespace() throws SAXException, IOException
	{
		assertXML("<element> text </element>", "<element> text </element>");
	}

	@Test
	public void elementWithAnyDeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element> </element>", "<!DOCTYPE element [<!ELEMENT element ANY>]><element> </element>");
	}

	@Test
	public void elementWithChoiceDeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element/>", "<!DOCTYPE element [<!ELEMENT element (a|b)*>]><element> </element>");
	}

	@Test
	public void elementWithPCDATADeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element> </element>", "<!DOCTYPE element [<!ELEMENT element (#PCDATA)>]><element> </element>");
	}

	@Test
	public void elementWithChildWithPCDATADeclWithWhitespace() throws SAXException, IOException
	{
		assertXML("<element><child>text</child></element>", "<!DOCTYPE element [<!ELEMENT child (#PCDATA)>]>"
			+ "<element> <child>text</child> </element>");
	}
}
