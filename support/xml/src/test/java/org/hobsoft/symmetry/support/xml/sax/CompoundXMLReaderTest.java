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
package org.hobsoft.symmetry.support.xml.sax;

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
