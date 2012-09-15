/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/transform/NamespaceEventTransformer.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
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
