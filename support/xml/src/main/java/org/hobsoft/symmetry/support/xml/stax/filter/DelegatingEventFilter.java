/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/DelegatingEventFilter.java $
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
abstract class DelegatingEventFilter implements EventFilter
{
	// fields -----------------------------------------------------------------
	
	private final EventFilter delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingEventFilter(EventFilter delegate)
	{
		if (delegate == null)
		{
			throw new NullPointerException("delegate cannot be null");
		}
		
		this.delegate = delegate;
	}
	
	// EventFilter methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public boolean accept(XMLEvent event)
	{
		return delegate.accept(event);
	}
	
	// protected methods ------------------------------------------------------
	
	protected final EventFilter getDelegate()
	{
		return delegate;
	}
}
