/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/closure/SetAttributeClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.closure;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A node closure that sets a named attribute on elements.
 * 
 * @author Mark Hobson
 * @version $Id: SetAttributeClosure.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
