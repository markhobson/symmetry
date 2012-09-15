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
 * A node closure that sets a named attribute on elements.
 * 
 * @author Mark Hobson
 */
public class SetAttributeClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	private final String namespaceURI;
	
	private final String localName;
	
	private final String value;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>SetAttributeClosure</code> that will set the specified attribute.
	 * 
	 * @param name
	 *            the name of the attribute to set
	 * @param value
	 *            the value of the attribute to set
	 */
	public SetAttributeClosure(String name, String value)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("name is null");
		}
		
		if (value == null)
		{
			throw new IllegalArgumentException("value is null");
		}
		
		this.name = name;
		this.value = value;
		
		namespaceURI = null;
		localName = null;
	}
	
	/**
	 * Creates a new <code>SetAttributeClosure</code> that will set the specified namespaced attribute.
	 * 
	 * @param namespaceURI
	 *            the namespace URI of the attribute to set
	 * @param localName
	 *            the local name of the attribute to set
	 * @param value
	 *            the value of the attribute to set
	 */
	public SetAttributeClosure(String namespaceURI, String localName, String value)
	{
		if (localName == null)
		{
			throw new IllegalArgumentException("localName is null");
		}
		
		if (value == null)
		{
			throw new IllegalArgumentException("value is null");
		}

		this.namespaceURI = namespaceURI;
		this.localName = localName;
		this.value = value;
		
		name = null;
	}
	
	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Sets the attribute on the specified node.
	 * 
	 * Only element nodes are considered, all other node types are ignored.
	 * 
	 * @param node
	 *            the node to set the attribute on
	 */
	public void execute(Node node)
	{
		if (node instanceof Element)
		{
			Element element = (Element) node;
			
			if (name != null)
			{
				element.setAttribute(name, value);
			}
			else
			{
				element.setAttributeNS(namespaceURI, localName, value);
			}
		}
	}
}
