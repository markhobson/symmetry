/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/ShowFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.hobsoft.symmetry.support.xml.dom.TraversalUtils;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ShowFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private final int whatToShow;
	
	// constructors -----------------------------------------------------------
	
	public ShowFilter(int whatToShow)
	{
		this.whatToShow = whatToShow;
	}
	
	// NodeFilter methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		return TraversalUtils.isVisible(node, whatToShow) ? FILTER_ACCEPT : FILTER_SKIP;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "show[" + TraversalUtils.getWhatToShowName(whatToShow) + "]";
	}
}
