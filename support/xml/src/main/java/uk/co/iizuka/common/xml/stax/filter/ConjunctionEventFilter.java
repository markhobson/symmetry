/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/ConjunctionEventFilter.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax.filter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ConjunctionEventFilter.java 88550 2011-05-26 10:11:55Z mark@IIZUKA.CO.UK $
 */
class ConjunctionEventFilter extends AbstractCompositeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public ConjunctionEventFilter(EventFilter... filters)
	{
		super(filters);
	}
	
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		EventFilter[] filters = getEventFilters();
		
		boolean accept = (filters.length > 0);
		
		for (int i = 0; i < filters.length && accept; i++)
		{
			accept &= filters[i].accept(event);
		}
		
		return accept;
	}
}
