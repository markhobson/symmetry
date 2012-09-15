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
package org.hobsoft.symmetry.xml;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.support.xml.stax.filter.EventFilters;
import org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code XmlUtils}.
 * 
 * @author Mark Hobson
 * @see XmlUtils
 */
@RunWith(JMock.class)
public class XmlUtilsTest extends AbstractStaxTest
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();

	// createFilteredWriter tests ---------------------------------------------
	
	@Test
	public void createFilteredWriter() throws XMLStreamException
	{
		final XMLStreamWriter writer = mockery.mock(XMLStreamWriter.class);
		
		mockery.checking(new Expectations() { {
			one(writer).writeStartDocument("UTF-8", "1.0");
			ignoring(writer).getNamespaceContext();
		} });
		
		EventFilter filter = TypeEventFilters.startDocument();
		
		XMLStreamWriter filteredWriter = XmlUtils.createFilteredWriter(writer, filter);
		filteredWriter.writeStartDocument("UTF-8", "1.0");
		filteredWriter.writeEndDocument();
	}
	
	@Test
	public void createFilteredWriterWithElement() throws XMLStreamException
	{
		final XMLStreamWriter writer = mockery.mock(XMLStreamWriter.class);
		
		mockery.checking(new Expectations() { {
			one(writer).writeEmptyElement("a");
			ignoring(writer).getNamespaceContext();
		} });
		
		EventFilter filter = EventFilters.element("a");
		
		XMLStreamWriter filteredWriter = XmlUtils.createFilteredWriter(writer, filter);
		filteredWriter.writeStartDocument("1.0");
		filteredWriter.writeStartElement("a");
		filteredWriter.writeStartElement("b");
		// b
		filteredWriter.writeEndElement();
		// a
		filteredWriter.writeEndElement();
		filteredWriter.writeEndDocument();
	}
	
	// TODO: investigate and fix - default namespace URI appears not to be honoured by XMLStreamEventWriter
	@Ignore
	@Test
	public void createFilteredWriterWithDefaultNamespacedElement() throws XMLStreamException
	{
		EventFilter filter = EventFilters.not(EventFilters.element("ns", "b"));
		
		XMLStreamWriter filteredWriter = XmlUtils.createFilteredWriter(getXMLStreamWriter(), filter);
		filteredWriter.setDefaultNamespace("ns");
		filteredWriter.writeStartElement("a");
		filteredWriter.writeStartElement("b");
		// ns:b
		filteredWriter.writeEndElement();
		// ns:a
		filteredWriter.writeEndElement();
		filteredWriter.writeEndDocument();
		
		assertXml("<a/>");
	}
	
	// writeXmlFragment tests -------------------------------------------------
	
	@Test
	public void writeXmlFragmentContent() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "text");
		assertXml("text");
	}
	
	@Test
	public void writeXmlFragmentSelfClosing() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a/>");
		assertXml("<a/>");
	}
	
	@Test
	public void writeXmlFragmentStartEnd() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a></a>");
		assertXml("<a/>");
	}
	
	@Test
	public void writeXmlFragmentStartContentEnd() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a>text</a>");
		assertXml("<a>text</a>");
	}
	
	@Test
	public void writeXmlFragmentAttribute() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a b=\"c\">text</a>");
		assertXml("<a b=\"c\">text</a>");
	}
	
	@Test
	public void writeXmlFragmentChildElement() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a><b>text</b></a>");
		assertXml("<a><b>text</b></a>");
	}
	
	@Test
	public void writeXmlFragmentPrecedingText() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "text<a>text</a>");
		assertXml("text<a>text</a>");
	}
	
	@Test
	public void writeXmlFragmentFollowingText() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<a>text</a>text");
		assertXml("<a>text</a>text");
	}
	
	@Test
	public void writeXmlFragmentPrecedingFollowingText() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "text<a>text</a>text");
		assertXml("text<a>text</a>text");
	}
	
	@Test
	public void writeXmlFragmentEntityReference() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "&lt;");
		assertXml("&lt;");
	}
	
	@Test
	public void writeXmlFragmentDtd() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<!DOCTYPE a><a/>");
		assertXml("<a/>");
	}
	
	@Test
	public void writeXmlFragmentProcessingInstruction() throws XMLStreamException
	{
		XmlUtils.writeXmlFragment(getXMLStreamWriter(), "<?a?><a/>");
		assertXml("<a/>");
	}
}