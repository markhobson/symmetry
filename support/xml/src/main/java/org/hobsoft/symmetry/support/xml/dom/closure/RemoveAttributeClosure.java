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
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A node closure that removes a named attribute from elements.
 * 
 * @author Mark Hobson
 */
public class RemoveAttributeClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	private final String namespaceURI;
	
	private final String localName;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>RemoveAttributeClosure</code> that will remove the specified attribute.
	 * 
	 * @param name
	 *            the name of the attribute to remove
	 */
	public RemoveAttributeClosure(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("name is null");
		}
		
		this.name = name;
		
		namespaceURI = null;
		localName = null;
	}
	
	/**
	 * Creates a new <code>RemoveAttributeClosure</code> that will remove the specified namespaced attribute.
	 * 
	 * @param namespaceURI
	 *            the namespace URI of the attribute to remove
	 * @param localName
	 *            the local name of the attribute to remove
	 */
	public RemoveAttributeClosure(String namespaceURI, String localName)
	{
		if (localName == null)
		{
			throw new IllegalArgumentException("localName is null");
		}

		this.namespaceURI = namespaceURI;
		this.localName = localName;
		
		name = null;
	}
	
	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Removes the attribute from the specified node.
	 * 
	 * Only element nodes are considered, all other node types are ignored.
	 * 
	 * @param node
	 *            the node to remove the attribute from
	 */
	public void execute(Node node)
	{
		if (node instanceof Element)
		{
			Element element = (Element) node;
			
			if (name != null)
			{
				element.removeAttribute(name);
			}
			else
			{
				element.removeAttributeNS(namespaceURI, localName);
			}
		}
	}
}
