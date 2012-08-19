/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/AbstractCompoundFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.w3c.dom.traversal.NodeFilter;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractCompoundFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractCompoundFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private final NodeFilter[] filters;
	
	// constructors -----------------------------------------------------------
	
	public AbstractCompoundFilter(NodeFilter... filters)
	{
		this.filters = filters;
	}
	
	// public methods ---------------------------------------------------------
	
	public NodeFilter[] getNodeFilters()
	{
		// NOTE: this returns the internal array, and thus can be modified
		
		return filters;
	}
	
	// protected methods ------------------------------------------------------
	
	protected void join(StringBuilder builder, String separator)
	{
		for (int i = 0; i < filters.length; i++)
		{
			if (i > 0)
			{
				builder.append(separator);
			}
			
			builder.append(filters[i]);
		}
	}
}
