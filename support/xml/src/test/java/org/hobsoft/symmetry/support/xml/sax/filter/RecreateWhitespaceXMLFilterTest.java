/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
