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

import org.hobsoft.symmetry.support.xml.dom.visitor.ClosureNodeVisitor;

/**
 * A factory to build various node visitors.
 * 
 * @author Mark Hobson
 */
public final class NodeVisitors
{
	// constructors -----------------------------------------------------------
	
	private NodeVisitors()
	{
		// private constructor for utility class
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets a node visitor that executes the specified closure on visited nodes and their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @return the node visitor
	 */
	public static ClosureNodeVisitor closure(NodeClosure closure)
	{
		return closure(closure, true);
	}
	
	/**
	 * Gets a node visitor that executes the specified closure on visited nodes and optionally their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @param deep
	 *            whether to execute the closure on visited node's descendants
	 * @return the node visitor
	 */
	public static ClosureNodeVisitor closure(NodeClosure closure, boolean deep)
	{
		return new ClosureNodeVisitor(closure, deep);
	}
}
