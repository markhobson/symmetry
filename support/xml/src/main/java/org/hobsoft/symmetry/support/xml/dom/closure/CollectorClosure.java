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
package org.hobsoft.symmetry.support.xml.dom.closure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.hobsoft.symmetry.support.xml.dom.NodeVisitor;
import org.w3c.dom.Node;

/**
 * A node closure that collects nodes and makes them accessible for further processing.
 * 
 * @author Mark Hobson
 */
public class CollectorClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The list of collected nodes.
	 */
	private List<Node> nodes;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>CollectorClosure</code>.
	 */
	public CollectorClosure()
	{
		nodes = new ArrayList<Node>();
	}

	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Collects the specified node.
	 * 
	 * @param node
	 *            the node to collect
	 */
	public void execute(Node node)
	{
		nodes.add(node);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the collected nodes.
	 * 
	 * @return a read-only collection of nodes that have been visited
	 */
	public List<Node> getNodes()
	{
		return Collections.unmodifiableList(nodes);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the collected nodes.
	 * 
	 * @param visitor
	 *            the node visitor to apply
	 */
	public void accept(NodeVisitor visitor)
	{
		for (Node node : nodes)
		{
			if (!NodeUtils.accept(node, visitor))
			{
				break;
			}
		}
	}
}
