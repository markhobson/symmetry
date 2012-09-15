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

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class EventReaderTransformer extends EventReaderDelegate
{
	// fields -----------------------------------------------------------------
	
	private final EventTransformer transformer;

	// constructors -----------------------------------------------------------
	
	public EventReaderTransformer(XMLEventReader reader, EventTransformer transformer)
	{
		super(reader);
		
		this.transformer = transformer;
	}
	
	// XMLEventReader methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public XMLEvent nextEvent() throws XMLStreamException
	{
		return transform(super.nextEvent());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public XMLEvent peek() throws XMLStreamException
	{
		XMLEvent event = super.peek();
		
		if (event == null)
		{
			return null;
		}
		
		return transform(event);
	}
	
	// TODO: transform getElementText?
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public XMLEvent nextTag() throws XMLStreamException
	{
		return transform(super.nextTag());
	}
	
	// Iterator methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object next()
	{
		return transform((XMLEvent) super.next());
	}

	// public methods ---------------------------------------------------------
	
	public EventTransformer getEventTransformer()
	{
		return transformer;
	}
	
	// protected methods ------------------------------------------------------
	
	protected XMLEvent transform(XMLEvent event)
	{
		return getEventTransformer().transform(event);
	}
}
