/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/AbstractHierarchicalEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
abstract class AbstractHierarchicalEventFilter implements EventFilter
{
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean accept(XMLEvent event)
	{
		if (event.isEndDocument() || event.isEndElement())
		{
			end(event);
		}
		
		boolean accept = acceptImpl(event);
		
		if (event.isStartDocument() || event.isStartElement())
		{
			start(event);
		}
		
		return accept;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract void start(XMLEvent event);

	protected abstract void end(XMLEvent event);

	protected abstract boolean acceptImpl(XMLEvent event);
}
