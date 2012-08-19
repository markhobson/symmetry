/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/PathEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax.filter;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PathEventFilter.java 88632 2011-05-31 15:54:50Z mark@IIZUKA.CO.UK $
 */
class PathEventFilter extends AbstractDepthEventFilter
{
	// fields -----------------------------------------------------------------
	
	private final List<EventFilter> filters;
	
	private final Stack<XMLEvent> events;
	
	// constructors -----------------------------------------------------------
	
	public PathEventFilter(EventFilter... filters)
	{
		this.filters = Arrays.asList(filters);
		
		events = new Stack<XMLEvent>();
	}

	// AbstractDepthEventFilter methods ---------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void start(XMLEvent event)
	{
		// save storing entire path as we're only interested in the start
		if (getDepth() < filters.size())
		{
			events.push(event);
		}
		
		super.start(event);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void end(XMLEvent event)
	{
		super.end(event);
		
		if (getDepth() < filters.size())
		{
			events.pop();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean acceptImpl(XMLEvent event)
	{
		if (events.size() < filters.size())
		{
			return false;
		}
		
		for (int i = 0; i < filters.size(); i++)
		{
			EventFilter filter = filters.get(i);
			XMLEvent parentEvent = events.get(i);
			
			if (!filter.accept(parentEvent))
			{
				return false;
			}
		}
		
		return true;
	}
}
