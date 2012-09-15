/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/StubEventFilter.java $
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
public class StubEventFilter implements EventFilter
{
	// fields -----------------------------------------------------------------
	
	private final boolean[] accepts;
	
	private int index;
	
	// constructors -----------------------------------------------------------
	
	public StubEventFilter(boolean... accepts)
	{
		this.accepts = accepts;
		
		index = 0;
	}

	// EventFilter methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		return accepts[index++];
	}
}
