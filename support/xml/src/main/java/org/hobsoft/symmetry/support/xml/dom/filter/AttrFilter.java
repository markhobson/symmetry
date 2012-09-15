/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/AttrFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A node filter that accepts specific attributes and skips everything else.
 * 
 * @author	Mark Hobson
 * @version	$Id: AttrFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class AttrFilter implements NodeFilter
{
	// TODO: add support for non-namespaced attributes
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The attribute namespace URI to accept.
	 */
	private String namespaceURI;

	/**
	 * The attribute local name to accept.
	 */
	private String localName;

	/**
	 * The attribute value to accept.
	 */
	private String value;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>AttrFilter</code> that only accepts attributes with the specified local name.
	 * 
	 * @param localName
	 *            the attribute local name to accept
	 */
	public AttrFilter(String localName)
	{
		this(null, localName);
	}
	
	/**
	 * Creates a new <code>AttrFilter</code> that only accepts attributes within the specified namespace with the
	 * specified local name.
	 * 
	 * @param namespaceURI
	 *            the attribute namespace URI to accept
	 * @param localName
	 *            the attribute local name to accept
	 */
	public AttrFilter(String namespaceURI, String localName)
	{
		this(namespaceURI, localName, null);
	}
	
	/**
	 * Creates a new <code>AttrFilter</code> that only accepts attributes within the specified namespace with the
	 * specified local name and value.
	 * 
	 * @param namespaceURI
	 *            the attribute namespace URI to accept
	 * @param localName
	 *            the attribute local name to accept
	 * @param value
	 *            the attribute value to accept
	 */
	public AttrFilter(String namespaceURI, String localName, String value)
	{
		this.namespaceURI = namespaceURI;
		this.localName = localName;
		this.value = value;
	}
	
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		if (node instanceof Attr)
		{
			if (acceptAttr((Attr) node))
			{
				return FILTER_ACCEPT;
			}
		}
		else if (node instanceof Element)
		{
			NamedNodeMap attributes = node.getAttributes();
			
			for (int i = 0; i < attributes.getLength(); i++)
			{
				Attr attr = (Attr) attributes.item(i);
				
				if (acceptAttr(attr))
				{
					return FILTER_ACCEPT;
				}
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
		
		builder.append("uri=").append(namespaceURI);
		builder.append(",name=").append(localName);
		
		if (value != null)
		{
			builder.append(",value=").append(value);
		}
		
		builder.insert(0, "attr[");
		builder.append(']');
		
		return builder.toString();
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Gets whether to accept the specified attribute.
	 * 
	 * @param attr
	 *            the attribute node
	 * @return <code>true</code> to accept this attribute
	 */
	protected boolean acceptAttr(Attr attr)
	{
		return NodeUtils.isAttr(attr, namespaceURI, localName, value);
	}
}
