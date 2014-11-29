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

import java.io.IOException;
import java.io.Writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ComponentVisitor;

/**
 * XML reflector for Symmetry UI components.
 */
public class XmlComponentReflector implements Reflector<Component>
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ComponentVisitor<XMLStreamWriter, XMLStreamException> visitor;
	
	private final String contentType;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public XmlComponentReflector(ComponentVisitor<XMLStreamWriter, XMLStreamException> visitor, String contentType)
	{
		this.visitor = visitor;
		this.contentType = contentType;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Reflector methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public Class<Component> getComponentType()
	{
		return Component.class;
	}
	
	@Override
	public String getContentType()
	{
		return contentType;
	}
	
	@Override
	public void reflect(Component component, Writer writer) throws IOException, ReflectorException
	{
		XMLOutputFactory factory = XMLOutputFactory.newFactory();
		
		try
		{
			XMLStreamWriter xmlWriter = factory.createXMLStreamWriter(writer);
			component.accept(visitor, xmlWriter);
		}
		catch (XMLStreamException exception)
		{
			throw new ReflectorException("Error reflecting component", exception);
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public ComponentVisitor<XMLStreamWriter, XMLStreamException> getComponentVisitor()
	{
		return visitor;
	}
}
