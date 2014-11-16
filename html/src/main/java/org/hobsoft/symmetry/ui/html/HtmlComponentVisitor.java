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

import org.hobsoft.symmetry.ui.ComponentVisitor;
import org.hobsoft.symmetry.ui.Window;

/**
 * Visitor that dehydrates UI components to HTML.
 */
public class HtmlComponentVisitor implements ComponentVisitor<XMLStreamWriter, XMLStreamException>
{
	// ----------------------------------------------------------------------------------------------------------------
	// ComponentVisitor methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public void visit(Window window, XMLStreamWriter writer) throws XMLStreamException
	{
		writer.writeStartElement("html");
		writer.writeStartElement("body");
		writer.flush();
	}

	@Override
	public void endVisit(Window window, XMLStreamWriter writer) throws XMLStreamException
	{
		// body
		writer.writeEndElement();
		// html
		writer.writeEndElement();
		writer.flush();
	}
}
