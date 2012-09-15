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
package org.hobsoft.symmetry.support.xml.dom;

import org.w3c.dom.Node;

/**
 * Defines a closure that operates upon nodes.
 * 
 * @author Mark Hobson
 */
public interface NodeClosure
{
	/**
	 * Executes this closure for the specified node.
	 * 
	 * @param node
	 *            the node to execute this closure upon
	 */
	void execute(Node node);
}
