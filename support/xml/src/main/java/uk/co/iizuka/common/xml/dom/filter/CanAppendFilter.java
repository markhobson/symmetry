/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/CanAppendFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.validation.NodeEditVAL;

import uk.co.iizuka.common.xml.dom.ValidationUtils;

/**
 * 
 *
 * @author	Mark Hobson
 * @version	$Id: CanAppendFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class CanAppendFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private final Node newNode;

	// constructors -----------------------------------------------------------
	
	public CanAppendFilter(Node newNode)
	{
		this.newNode = newNode;
	}

	// NodeFilter methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		if (!ValidationUtils.hasValidation3(node))
		{
			return FILTER_ACCEPT;
		}
		
		NodeEditVAL nodeVAL = (NodeEditVAL) node;
		short canAppend = nodeVAL.canAppendChild(newNode);
		
		return (canAppend != NodeEditVAL.VAL_FALSE) ? FILTER_ACCEPT : FILTER_SKIP;
	}
}
