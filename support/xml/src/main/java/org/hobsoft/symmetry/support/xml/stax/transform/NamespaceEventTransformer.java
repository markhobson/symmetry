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
package org.hobsoft.symmetry.support.xml.stax.transform;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.hobsoft.symmetry.support.xml.stax.EventTransformer;

/**
 * 
 * 
 * @author Mark Hobson
 */
class NamespaceEventTransformer implements EventTransformer
{
	// fields -----------------------------------------------------------------
	
	private final String targetNamespaceUri;
	
	private final XMLEventFactory factory;
	
	// constructors -----------------------------------------------------------
	
	public NamespaceEventTransformer(String targetNamespaceUri)
	{
		this.targetNamespaceUri = targetNamespaceUri;
		
		factory = XMLEventFactory.newInstance();
	}
	
	// EventTransformer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public XMLEvent transform(XMLEvent event)
	{
		XMLEvent transformedEvent;
		
		if (event == null)
		{
			transformedEvent = null;
		}
		else if (event.getEventType() == XMLStreamConstants.START_ELEMENT)
		{
			transformedEvent = transform(event.asStartElement());
		}
		else if (event.getEventType() == XMLStreamConstants.END_ELEMENT)
		{
			transformedEvent = transform(event.asEndElement());
		}
		else
		{
			transformedEvent = event;
		}
		
		return transformedEvent;
	}

	// public methods ---------------------------------------------------------
	
	public String getNamespaceURI()
	{
		return targetNamespaceUri;
	}
	
	// private methods --------------------------------------------------------
	
	private StartElement transform(StartElement event)
	{
		QName name = event.getName();
		
		if (name.getNamespaceURI().length() == 0)
		{
			event = factory.createStartElement(name.getPrefix(), targetNamespaceUri, name.getLocalPart(),
				event.getAttributes(), event.getNamespaces(), event.getNamespaceContext());
		}
		
		return event;
	}
	
	private EndElement transform(EndElement event)
	{
		QName name = event.getName();
		
		if (name.getNamespaceURI().length() == 0)
		{
			event = factory.createEndElement(name.getPrefix(), targetNamespaceUri, name.getLocalPart(),
				event.getNamespaces());
		}
		
		return event;
	}
}
