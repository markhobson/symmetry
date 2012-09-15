/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/DisjunctionEventFilter.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class DisjunctionEventFilter extends AbstractCompositeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public DisjunctionEventFilter(EventFilter... filters)
	{
		super(filters);
	}
	
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		boolean accept = false;
		
		EventFilter[] filters = getEventFilters();
		
		for (int i = 0; i < filters.length && !accept; i++)
		{
			accept = filters[i].accept(event);
		}
		
		return accept;
	}
}
