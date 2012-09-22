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
package org.hobsoft.symmetry.support.xml.stax;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import javanet.staxutils.helpers.EventWriterDelegate;

/**
 * A XML event writer that delegates events to another XML event writer that are accepted by an event filter. 
 *
 * @author	Mark Hobson
 * @see		XMLEventWriter
 */
public class EventWriterFilter extends EventWriterDelegate
{
	// fields -----------------------------------------------------------------
	
	private final EventFilter filter;

	// constructors -----------------------------------------------------------
	
	public EventWriterFilter(XMLEventWriter writer, EventFilter filter)
	{
		super(writer);
		
		this.filter = filter;
	}
	
	// XMLEventWriter methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(XMLEvent event) throws XMLStreamException
	{
		if (getEventFilter().accept(event))
		{
			super.add(event);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(XMLEventReader reader) throws XMLStreamException
	{
		while (reader.hasNext())
		{
			add(reader.nextEvent());
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public EventFilter getEventFilter()
	{
		return filter;
	}
}
