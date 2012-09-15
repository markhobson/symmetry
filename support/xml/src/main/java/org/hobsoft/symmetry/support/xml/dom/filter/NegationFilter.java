/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
