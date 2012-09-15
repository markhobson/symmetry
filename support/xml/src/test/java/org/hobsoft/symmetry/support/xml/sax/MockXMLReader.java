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
