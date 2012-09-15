/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/ParentFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A <code>NodeFilter</code> that accepts a node if and only if a given filter accepts its parent node.
 * 
 * @author Mark Hobson
 * @version $Id: ParentFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class ParentFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The filter to apply to the parent node.
	 */
	private NodeFilter filter;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a <code>NodeFilter</code> that accepts a node if and only if the specified filter accepts its parent
	 * node.
	 * 
	 * @param filter
	 *            the filter to apply to the parent node
	 */
	public ParentFilter(NodeFilter filter)
	{
		this.filter = filter;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		Node parent = node.getParentNode();
		
		if (parent == null)
		{
			return NodeFilter.FILTER_SKIP;
		}
		
		return filter.acceptNode(parent);
	}
}
