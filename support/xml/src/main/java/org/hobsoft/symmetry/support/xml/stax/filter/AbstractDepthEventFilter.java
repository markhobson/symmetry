/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/AbstractDepthEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.stream.events.XMLEvent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractDepthEventFilter.java 88632 2011-05-31 15:54:50Z mark@IIZUKA.CO.UK $
 */
abstract class AbstractDepthEventFilter extends AbstractHierarchicalEventFilter
{
	// fields -----------------------------------------------------------------
	
	private int depth;
	
	// constructors -----------------------------------------------------------
	
	public AbstractDepthEventFilter()
	{
		depth = 0;
	}

	// AbstractHierarchicalEventFilter methods --------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void start(XMLEvent event)
	{
		depth++;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void end(XMLEvent event)
	{
		depth--;
	}
	
	// protected methods ------------------------------------------------------
	
	protected final int getDepth()
	{
		return depth;
	}
}
