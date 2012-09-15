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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A node filter that accepts specific elements and skips everything else.
 * 
 * @author Mark Hobson
 */
public class ElementFilter implements NodeFilter
{
	// TODO: add support for non-namespaced elements
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The element namespace URI to accept.
	 */
	private String namespaceURI;

	/**
	 * The element local names to accept.
	 */
	private String[] localNames;

	/**
	 * Whether to accept all element namespace URIs.
	 */
	private boolean allNamespaceURIs;

	/**
	 * Whether to accept all element local names.
	 */
	private boolean allLocalNames;
	
	// constructors -----------------------------------------------------------

	/**
	 * Creates a new <code>ElementFilter</code> that accepts all element nodes.
	 */
	public ElementFilter()
	{
		this("*");
	}
	
	/**
	 * Creates a new <code>ElementFilter</code> that only accepts elements within the specified namespace.
	 * 
	 * @param namespaceURI
	 *            the element namespace URI to accept, or <code>"*"</code> to accept all namespace URIs
	 */
	public ElementFilter(String namespaceURI)
	{
		this(namespaceURI, "*");
	}
	
	/**
	 * Creates a new <code>ElementFilter</code> that only accepts elements within the specified namespace with the
	 * specified local names.
	 * 
	 * @param namespaceURI
	 *            the element namespace URI to accept, or <code>"*"</code> to accept all namespace URIs
	 * @param localNames
	 *            the element local names to accept, or a <code>{"*"}</code> to accept all local names
	 */
	public ElementFilter(String namespaceURI, String... localNames)
	{
		this.namespaceURI = namespaceURI;
		this.localNames = localNames;
		
		allNamespaceURIs = "*".equals(namespaceURI);
		allLocalNames = (localNames.length == 1) && "*".equals(localNames[0]);
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		if (node == null)
		{
			return FILTER_SKIP;
		}
		
		if (!(node instanceof Element))
		{
			return FILTER_SKIP;
		}
		
		if (!allNamespaceURIs && !nullEquals(namespaceURI, node.getNamespaceURI()))
		{
			return FILTER_SKIP;
		}
		
		if (allLocalNames)
		{
			return FILTER_ACCEPT;
		}

		String localName = node.getLocalName();
		
		// support unnamespaced nodes
		if (localName == null)
		{
			localName = node.getNodeName();
		}
		
		for (int i = 0; i < localNames.length; i++)
		{
			if (localNames[i].equals(localName))
			{
				return FILTER_ACCEPT;
			}
		}
		
		return FILTER_SKIP;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("element[");
		builder.append("uri=").append(namespaceURI).append(',');
		
		builder.append("name");
		builder.append(localNames.length > 1 ? "s=[" : "=");
		
		for (int i = 0; i < localNames.length; i++)
		{
			if (i > 0)
			{
				builder.append(',');
			}
			
			builder.append(localNames[i]);
		}
		
		if (localNames.length > 1)
		{
			builder.append(']');
		}
		
		builder.append(']');
		
		return builder.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private boolean nullEquals(Object a, Object b)
	{
		return (a == null || b == null) ? (a == b) : a.equals(b);
	}
}
