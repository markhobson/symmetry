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
import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.w3c.dom.Node;

/**
 * A node closure that replaces nodes with their children.
 * 
 * @author Mark Hobson
 */
public class UnsurroundClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	/**
	 * Whether to clone the children before replacement.
	 */
	private final boolean clone;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>UnsurroundClosure</code> that does not clone children.
	 */
	public UnsurroundClosure()
	{
		this(false);
	}
	
	/**
	 * Creates a new <code>UnsurroundClosure</code> that clones children if specified.
	 * 
	 * @param clone
	 *            <code>true</code> to clone children before replacement
	 */
	public UnsurroundClosure(boolean clone)
	{
		this.clone = clone;
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Replaces the specified node with its children.
	 * 
	 * @param node
	 *            the node to replace with its children
	 */
	public void execute(Node node)
	{
		NodeUtils.unsurround(node, clone);
	}
}
