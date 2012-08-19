/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/AncestorFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.NodeUtils;

/**
 * A <code>NodeFilter</code> that only accepts nodes that are an ancestor of
 * a given node.
 * 
 * @author	Mark Hobson
 * @version	$Id: AncestorFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class AncestorFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private Node node;
	
	// constructors -----------------------------------------------------------
	
	public AncestorFilter(Node node)
	{
		this.node = node;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		return NodeUtils.isAncestor(node, this.node) ? NodeFilter.FILTER_ACCEPT : NodeFilter.FILTER_SKIP;
	}
}
