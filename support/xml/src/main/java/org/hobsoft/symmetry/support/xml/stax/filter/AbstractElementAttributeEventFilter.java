/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/AbstractElementAttributeEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import java.util.Iterator;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
abstract class AbstractElementAttributeEventFilter extends DelegatingEventFilter
{
	// constructors -----------------------------------------------------------
	
	public AbstractElementAttributeEventFilter(EventFilter attributeFilter)
	{
		super(attributeFilter);
	}

	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean accept(XMLEvent event)
	{
		boolean accept;
		
		if (event.isStartElement())
		{
			accept = accept(event.asStartElement().getAttributes());
		}
		else
		{
			accept = false;
		}
		
		return accept;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract boolean accept(Iterator<Attribute> attributes);
}
