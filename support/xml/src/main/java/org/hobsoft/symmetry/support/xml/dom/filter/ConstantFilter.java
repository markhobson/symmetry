/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/ConstantFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A <code>NodeFilter</code> that always returns the same filter result for all nodes.
 * 
 * @author Mark Hobson
 * @version $Id: ConstantFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class ConstantFilter implements NodeFilter
{
	// constants --------------------------------------------------------------
	
	public static final ConstantFilter ACCEPT = new ConstantFilter(FILTER_ACCEPT);
	
	public static final ConstantFilter REJECT = new ConstantFilter(FILTER_REJECT);
	
	public static final ConstantFilter SKIP = new ConstantFilter(FILTER_SKIP);
	
	private static final String[] STRING_VALUES = {
		"accept",
		"reject",
		"skip",
	};
	
	// fields -----------------------------------------------------------------
	
	private final short result;
	
	// constructors -----------------------------------------------------------
	
	protected ConstantFilter(short result)
	{
		if (result != NodeFilter.FILTER_ACCEPT && result != NodeFilter.FILTER_REJECT
			&& result != NodeFilter.FILTER_SKIP)
		{
			throw new IllegalArgumentException("result must be FILTER_ACCEPT, FILTER_REJECT or FILTER_SKIP");
		}
		
		this.result = result;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		return result;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return STRING_VALUES[result - 1];
	}
}
