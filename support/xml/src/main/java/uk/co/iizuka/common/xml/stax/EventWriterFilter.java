/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/EventWriterFilter.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax;

import javanet.staxutils.helpers.EventWriterDelegate;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * A XML event writer that delegates events to another XML event writer that are accepted by an event filter. 
 *
 * @author	Mark Hobson
 * @version	$Id: EventWriterFilter.java 88477 2011-05-25 10:34:47Z mark@IIZUKA.CO.UK $
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
