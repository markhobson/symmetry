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
 * A <code>NodeFilter</code> that accepts a node if and only if a given filter accepts its parent node.
 * 
 * @author Mark Hobson
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
