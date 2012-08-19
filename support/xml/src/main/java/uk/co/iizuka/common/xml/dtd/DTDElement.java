/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDElement.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents an element type declaration within a DTD.
 * 
 * @author Mark Hobson
 * @version $Id: DTDElement.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/REC-xml/#elemdecls">3.2 Element Type Declarations</a>
 */
public class DTDElement implements Iterable<DTDAttribute>
{
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	private final DTDContent content;
	
	private final Map<String, DTDAttribute> attributes;
	
	private final String newLine;
	
	// constructors -----------------------------------------------------------
	
	public DTDElement(String name)
	{
		this(name, DTDEmptyContent.INSTANCE);
	}
	
	public DTDElement(String name, String content) throws DTDParseException
	{
		this(name, DTDContentParser.parse(content));
	}
	
	public DTDElement(String name, DTDContent content)
	{
		this.name = name;
		this.content = content;
		
		attributes = new LinkedHashMap<String, DTDAttribute>();
		
		newLine = System.getProperty("line.separator");
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<DTDAttribute> iterator()
	{
		return getAttributes().iterator();
	}
	
	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public DTDContent getContent()
	{
		return content;
	}
	
	public DTDAttribute addAttribute(String name, String type, String mode, String value) throws DTDParseException
	{
		DTDAttribute attribute = new DTDAttribute(this, name, type, mode, value);
		addAttribute(attribute);
		return attribute;
	}
	
	public void addAttribute(DTDAttribute attribute)
	{
		attributes.put(attribute.getName(), attribute);
	}
	
	public void removeAttribute(DTDAttribute attribute)
	{
		attributes.remove(attribute.getName());
	}
	
	public DTDAttribute getAttribute(Attr attr)
	{
		return getAttribute(attr.getName());
	}
	
	public DTDAttribute getAttribute(String name)
	{
		return attributes.get(name);
	}
	
	public int getAttributeCount()
	{
		return attributes.size();
	}
	
	public Collection<DTDAttribute> getAttributes()
	{
		return Collections.unmodifiableCollection(attributes.values());
	}
	
	public boolean validateContent(Node... nodes)
	{
		String[] elements = new String[nodes.length];
		
		for (int i = 0; i < nodes.length; i++)
		{
			Node node = nodes[i];
			
			if (node instanceof Element)
			{
				elements[i] = ((Element) node).getTagName();
			}
		}
		
		return validateContent(elements);
	}
	
	public boolean validateContent(String... elements)
	{
		int index = getContent().validate(0, elements);
		
		return (index == elements.length);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("<!ELEMENT ").append(name).append(" ").append(content).append(">");
		builder.append(newLine);
		
		if (getAttributeCount() > 0)
		{
			builder.append("<!ATTLIST ").append(name).append(newLine);
			
			for (DTDAttribute attribute : this)
			{
				builder.append('\t');
				attribute.appendAttribute(builder);
				builder.append(newLine);
			}
			
			builder.append(">").append(newLine);
		}
		
		return builder.toString();
	}
}
