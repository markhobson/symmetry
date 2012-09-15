/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/closure/RemoveAttributeClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
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
