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
package org.hobsoft.symmetry.ui.html;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.ui.Window;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests {@code HtmlComponentVisitor}.
 */
public class HtmlComponentVisitorTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private HtmlComponentVisitor visitor;
	
	private XMLStreamWriter writer;
	
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		visitor = new HtmlComponentVisitor();
		writer = mock(XMLStreamWriter.class);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void visitWindowWritesHtml() throws XMLStreamException
	{
		Window window = new Window();
		
		visitor.visit(window, writer);
		
		verify(writer).writeStartElement("html");
		verify(writer).writeStartElement("body");
	}
	
	@Test
	public void endVisitWindowWritesHtml() throws XMLStreamException
	{
		Window window = new Window();
		
		visitor.endVisit(window, writer);
		
		verify(writer, times(2)).writeEndElement();
	}
}
