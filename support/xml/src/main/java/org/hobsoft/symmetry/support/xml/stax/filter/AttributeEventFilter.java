/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/AttributeEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class AttributeEventFilter implements EventFilter
{
	// TODO: support attribute value
	
	// fields -----------------------------------------------------------------
	
	private final QName name;

	// constructors -----------------------------------------------------------
	
	public AttributeEventFilter(String localName)
	{
		this(new QName(localName));
	}
	
	public AttributeEventFilter(String namespaceUri, String localName)
	{
		this(new QName(namespaceUri, localName));
	}
	
	public AttributeEventFilter(String namespaceUri, String localName, String prefix)
	{
		this(new QName(namespaceUri, localName, prefix));
	}
	
	public AttributeEventFilter(QName name)
	{
		if (name == null)
		{
			throw new NullPointerException("name cannot be null");
		}
		
		this.name = name;
	}

	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		boolean accept;
		
		int eventType = event.getEventType();
		
		if (eventType == XMLStreamConstants.ATTRIBUTE)
		{
			accept = accept((Attribute) event);
		}
		else
		{
			accept = false;
		}
		
		return accept;
	}
	
	// private methods --------------------------------------------------------
	
	private boolean accept(Attribute attribute)
	{
		return QNameUtils.matches(name, attribute.getName());
	}
}
