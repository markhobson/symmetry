/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/AbstractCompositeEventFilter.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.EventFilter;

/**
 * 
 * 
 * @author Mark Hobson
 */
abstract class AbstractCompositeEventFilter implements EventFilter
{
	// fields -----------------------------------------------------------------
	
	private final EventFilter[] filters;

	// constructors -----------------------------------------------------------
	
	public AbstractCompositeEventFilter(EventFilter... filters)
	{
		if (filters == null)
		{
			throw new IllegalArgumentException("filters cannot be null");
		}
		
		this.filters = filters;
	}

	// public methods ---------------------------------------------------------
	
	public EventFilter[] getEventFilters()
	{
		// NOTE: returns internal array
		
		return filters;
	}
}
