/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/EventReaderTransformer.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EventReaderTransformer.java 88689 2011-06-02 14:10:49Z mark@IIZUKA.CO.UK $
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
