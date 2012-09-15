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
 * Tests <code>IgnoreWhitespaceXMLFilter</code>.
 * 
 * @author Mark Hobson
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
