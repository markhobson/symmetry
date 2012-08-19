/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/DisjunctionElementAttributeEventFilter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.stax.filter;

import java.util.Iterator;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.Attribute;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DisjunctionElementAttributeEventFilter.java 88560 2011-05-26 13:50:49Z mark@IIZUKA.CO.UK $
 */
class DisjunctionElementAttributeEventFilter extends AbstractElementAttributeEventFilter
{
	// constructors -----------------------------------------------------------
	
	public DisjunctionElementAttributeEventFilter(EventFilter attributeFilter)
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
		boolean accept = false;
		
		while (attributes.hasNext() && !accept)
		{
			Attribute attribute = attributes.next();
			
			accept = getDelegate().accept(attribute);
		}
		
		return accept;
	}
}
