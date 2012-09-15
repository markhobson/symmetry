/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/NegationFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A node filter that logically negates another node filter.
 * <p>
 * This is implemented by performing ternary logic negation on the specified filter. More specifically, the negation of
 * a node filter that returns a value of <em>p</em> is:
 * </p>
 * <ul>
 * <li><code>FILTER_ACCEPT</code> if <em>p</em> equals <code>FILTER_REJECT</code></li>
 * <li><code>FILTER_REJECT</code> if <em>p</em> equals <code>FILTER_ACCEPT</code></li>
 * <li><code>FILTER_SKIP</code> otherwise</li>
 * </ul>
 * 
 * @author Mark Hobson
 * @version $Id: NegationFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://en.wikipedia.org/wiki/Ternary_logic">Ternary logic</a>
 */
public class NegationFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private NodeFilter filter;
	
	// constructors -----------------------------------------------------------
	
	public NegationFilter(NodeFilter filter)
	{
		this.filter = filter;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		short result = filter.acceptNode(node);

		return negate(result);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "not " + filter;
	}
	
	// private methods --------------------------------------------------------
	
	private short negate(short p)
	{
		// TODO: resolve ternary vs boolean logic
//		if (p == FILTER_ACCEPT)
//		{
//			p = FILTER_REJECT;
//		}
//		else if (p == FILTER_REJECT)
//		{
//			p = FILTER_ACCEPT;
//		}
		
		if (p == FILTER_ACCEPT)
		{
			p = FILTER_SKIP;
		}
		else if (p == FILTER_SKIP)
		{
			p = FILTER_ACCEPT;
		}
		
		return p;
	}
}
