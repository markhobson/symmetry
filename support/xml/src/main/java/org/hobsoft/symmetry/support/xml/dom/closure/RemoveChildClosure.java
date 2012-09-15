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

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.w3c.dom.Node;

/**
 * A node closure that removes a child from it's parent.
 * 
 * @author Mark Hobson
 */
public class RemoveChildClosure implements NodeClosure
{
	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Removes the specified node from it's parent, if it has one.
	 * 
	 * @param node
	 *            the node to remove from it's parent
	 */
	public void execute(Node node)
	{
		Node parent = node.getParentNode();
		
		if (parent != null)
		{
			parent.removeChild(node);
		}
	}
}
