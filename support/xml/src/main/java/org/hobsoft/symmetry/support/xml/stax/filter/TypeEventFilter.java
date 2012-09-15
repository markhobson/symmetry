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
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

import static javax.xml.stream.XMLStreamConstants.ATTRIBUTE;
import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.COMMENT;
import static javax.xml.stream.XMLStreamConstants.DTD;
import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.ENTITY_DECLARATION;
import static javax.xml.stream.XMLStreamConstants.ENTITY_REFERENCE;
import static javax.xml.stream.XMLStreamConstants.NAMESPACE;
import static javax.xml.stream.XMLStreamConstants.NOTATION_DECLARATION;
import static javax.xml.stream.XMLStreamConstants.PROCESSING_INSTRUCTION;
import static javax.xml.stream.XMLStreamConstants.SPACE;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

/**
 * An event filter that accepts events of a specific type.
 * 
 * @author Mark Hobson
 */
class TypeEventFilter implements EventFilter
{
	// fields -----------------------------------------------------------------
	
	private final int[] eventTypes;

	// constructors -----------------------------------------------------------
	
	public TypeEventFilter(int... eventTypes)
	{
		for (int eventType : eventTypes)
		{
			validateEventType(eventType);
		}
		
		this.eventTypes = eventTypes;
	}
	
	// EventFilter ------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		boolean accept = false;

		for (int i = 0; i < eventTypes.length && !accept; i++)
		{
			if (eventTypes[i] == event.getEventType())
			{
				accept = true;
			}
		}
		
		return accept;
	}

	// public methods ---------------------------------------------------------
	
	public int[] getEventTypes()
	{
		return eventTypes.clone();
	}
	
	// private methods --------------------------------------------------------
	
	private void validateEventType(int eventType)
	{
		if (eventType != START_ELEMENT
			&& eventType != END_ELEMENT
			&& eventType != PROCESSING_INSTRUCTION
			&& eventType != CHARACTERS
			&& eventType != COMMENT
			&& eventType != SPACE
			&& eventType != START_DOCUMENT
			&& eventType != END_DOCUMENT
			&& eventType != ENTITY_REFERENCE
			&& eventType != ATTRIBUTE
			&& eventType != DTD
			&& eventType != CDATA
			&& eventType != NAMESPACE
			&& eventType != NOTATION_DECLARATION
			&& eventType != ENTITY_DECLARATION)
		{
			throw new IllegalArgumentException("Unknown event type: " + eventType);
		}
	}
}
