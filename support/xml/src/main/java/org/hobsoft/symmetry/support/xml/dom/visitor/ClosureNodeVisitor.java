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
package org.hobsoft.symmetry.support.xml.dom.visitor;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.w3c.dom.Node;

/**
 * A node visitor that executes a closure on visited nodes.
 * 
 * @author Mark Hobson
 */
public class ClosureNodeVisitor extends AbstractNodeVisitor
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The node closure to execute.
	 */
	private final NodeClosure closure;
	
	/**
	 * Whether to execute the closure on visited node's descendants.
	 */
	private final boolean deep;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a node visitor that executes the specified closure on visited nodes and their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 */
	public ClosureNodeVisitor(NodeClosure closure)
	{
		this(closure, true);
	}
	
	/**
	 * Creates a node visitor that executes the specified closure on visited nodes and optionally their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @param deep
	 *            whether to execute the closure on visited node's descendants
	 */
	public ClosureNodeVisitor(NodeClosure closure, boolean deep)
	{
		this.closure = closure;
		this.deep = deep;
	}

	// AbstractNodeVisitor methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean beginNode(Node node)
	{
		execute(node);
		
		return deep;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean visitNode(Node node)
	{
		execute(node);
		
		return super.visitNode(node);
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Executes the closure on the specified node.
	 * 
	 * @param node
	 *            the node to execute the closure upon
	 */
	protected void execute(Node node)
	{
		closure.execute(node);
	}
}
