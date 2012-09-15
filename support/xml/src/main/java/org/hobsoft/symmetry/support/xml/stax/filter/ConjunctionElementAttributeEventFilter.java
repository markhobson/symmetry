/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/ConjunctionElementAttributeEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import java.util.Iterator;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.Attribute;

/**
 * 
 * 
 * @author Mark Hobson
 */
class ConjunctionElementAttributeEventFilter extends AbstractElementAttributeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public ConjunctionElementAttributeEventFilter(EventFilter attributeFilter)
	{
		super(attributeFilter);
	}

	// AbstractElementAttributeEventFilter methods ----------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean accept(Iterator<Attribute> attributes)
	{
		boolean accept = attributes.hasNext();
		
		while (attributes.hasNext() && accept)
		{
			Attribute attribute = attributes.next();
			
			accept &= getDelegate().accept(attribute);
		}
		
		return accept;
	}
}
