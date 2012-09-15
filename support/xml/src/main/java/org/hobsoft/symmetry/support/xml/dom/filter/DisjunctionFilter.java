/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/DisjunctionFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A node filter that logically ORs a number of node filters together.
 * <p>
 * This is implemented by performing ternary logic disjunction on the chain of filters in turn. More specifically, the
 * disjunction of two node filters that return values <em>p</em> and <em>q</em> is:
 * </p>
 * <ul>
 * <li><code>FILTER_ACCEPT</code> if either <em>p</em> or <em>q</em> equals <code>FILTER_ACCEPT</code></li>
 * <li><code>FILTER_REJECT</code> if both <em>p</em> and <em>q</em> equal <code>FILTER_REJECT</code></li>
 * <li><code>FILTER_SKIP</code> otherwise</li>
 * </ul>
 * 
 * @author Mark Hobson
 * @see ConjunctionFilter
 * @see <a href="http://en.wikipedia.org/wiki/Ternary_logic">Ternary logic</a>
 */
public class DisjunctionFilter extends AbstractCompoundFilter
{
	// constructors -----------------------------------------------------------

	public DisjunctionFilter(NodeFilter... filters)
	{
		super(filters);
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		NodeFilter[] filters = getNodeFilters();
		
		short result = FILTER_SKIP;
		boolean first = true;
		
		for (NodeFilter filter : filters)
		{
			if (filter != null)
			{
				short nextResult = filter.acceptNode(node);
			
				result = first ? nextResult : disjunction(result, nextResult);
				first = false;
			}
		}
		
		return result;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append('(');
		join(builder, " or ");
		builder.append(')');
		
		return builder.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private short disjunction(short p, short q)
	{
		if (p == FILTER_REJECT && q == FILTER_REJECT)
		{
			return FILTER_REJECT;
		}
		
		if (p == FILTER_ACCEPT || q == FILTER_ACCEPT)
		{
			return FILTER_ACCEPT;
		}
		
		return FILTER_SKIP;
	}
}
