/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/WellFormedEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import java.util.Stack;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
class WellFormedEventFilter extends DelegatingEventFilter
{
	// fields -----------------------------------------------------------------
	
	// TODO: use more efficient data structure
	private final Stack<Boolean> accepts;

	// constructors -----------------------------------------------------------
	
	public WellFormedEventFilter(EventFilter delegate)
	{
		super(delegate);
		
		accepts = new Stack<Boolean>();
	}
	
	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(XMLEvent event)
	{
		boolean accept;
		
		if (event.isStartDocument() || event.isStartElement())
		{
			accept = super.accept(event);
			
			accepts.push(accept);
		}
		else if (event.isEndDocument() || event.isEndElement())
		{
			accept = accepts.pop();
		}
		else
		{
			accept = super.accept(event);
		}
		
		return accept;
	}
}
