/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/RangeNodeFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.NodeUtils;
import uk.co.iizuka.common.xml.dom.RangeUtils;

/**
 * A node filter that accepts nodes that are selected within a range.
 * <p>
 * More specifically, this filter returns the following:
 * </p>
 * <ul>
 * <li><code>FILTER_ACCEPT</code> if the node is fully selected by the range</li>
 * <li><code>FILTER_REJECT</code> if the node is not selected by the range</li>
 * <li><code>FILTER_SKIP</code> if the node is partially selected by the range</li>
 * </ul>
 * 
 * @author Mark Hobson
 * @version $Id: RangeNodeFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class RangeNodeFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The range being filtered against.
	 */
	private Range range;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>RangeNodeFilter</code> for the specified range.
	 * 
	 * @param range
	 *            the range to filter against
	 */
	public RangeNodeFilter(Range range)
	{
		this.range = range;
	}
	
	// NodeFilter methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		// obtain node start boundary-point
		Node container = node.getParentNode();
		int offset = NodeUtils.indexOf(node);
		
		// skip nodes that cannot ever be selected by a range
		if (container == null)
		{
			return FILTER_SKIP;
		}
		
		// obtain range boundary-points
		Node startContainer = range.getStartContainer();
		int startOffset = range.getStartOffset();
		Node endContainer = range.getEndContainer();
		int endOffset = range.getEndOffset();
		
		// widen start boundary-point to previous non-text container
		if (RangeUtils.isTextContainer(startContainer))
		{
			startOffset = NodeUtils.indexOf(startContainer);
			startContainer = startContainer.getParentNode();
		}
		
		// widen end boundary-point to next non-text container
		if (RangeUtils.isTextContainer(endContainer))
		{
			endOffset = NodeUtils.indexOf(endContainer) + 1;
			endContainer = endContainer.getParentNode();
		}
		
		// determine if node is ancestor container of each boundary-point
		boolean startAncestorContainer = NodeUtils.isAncestorOrSelf(node, startContainer);
		boolean endAncestorContainer = NodeUtils.isAncestorOrSelf(node, endContainer);
		
		// skip partially selected nodes
		if (startAncestorContainer ^ endAncestorContainer)
		{
			return FILTER_SKIP;
		}
		
		// compare node boundary-points with range start boundary-point
		int startBeforeRange = NodeUtils.compare(container, offset, startContainer, startOffset);
		int endBeforeRange = NodeUtils.compare(container, offset + 1, startContainer, startOffset);
		boolean beforeRange = (startBeforeRange <= 0) && (endBeforeRange <= 0);
		
		// reject unselected nodes before start boundary-point
		if (beforeRange)
		{
			return FILTER_REJECT;
		}
		
		// compare node boundary-points with range end boundary-point
		int startAfterRange = NodeUtils.compare(container, offset, endContainer, endOffset);
		int endAfterRange = NodeUtils.compare(container, offset + 1, endContainer, endOffset);
		boolean afterRange = (startAfterRange >= 0) && (endAfterRange >= 0);
		
		// reject unselected nodes after end boundary-point
		if (afterRange)
		{
			return FILTER_REJECT;
		}
		
		// skip unselected nodes above boundary-points
		if (startBeforeRange < 0 && endAfterRange > 0)
		{
			return FILTER_SKIP;
		}
		
		// accept fully selected nodes
		return FILTER_ACCEPT;
	}
}
