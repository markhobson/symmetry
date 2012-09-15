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

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Provides support for testing classes that use StAX.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractStaxTest
{
	// TODO: avoid duplication with symmetry-xml-test-support's AbstractStaxTest
	
	// fields -----------------------------------------------------------------
	
	private StringWriter writer;
	
	private XMLStreamWriter xmlWriter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpStaxTest() throws XMLStreamException
	{
		writer = new StringWriter();
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		xmlWriter = factory.createXMLStreamWriter(writer);
	}
	
	// protected methods ------------------------------------------------------
	
	protected final XMLStreamWriter getXMLStreamWriter()
	{
		return xmlWriter;
	}
	
	protected final void assertXml(String expected) throws XMLStreamException
	{
		// ensure all start tags are closed
		xmlWriter.writeEndDocument();
		
		xmlWriter.flush();
		xmlWriter.close();
		
		assertEquals(expected, writer.toString());
	}
}
