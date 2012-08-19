/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/ShowFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.TraversalUtils;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ShowFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
