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

import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

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