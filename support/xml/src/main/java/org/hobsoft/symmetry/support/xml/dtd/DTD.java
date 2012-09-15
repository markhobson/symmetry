/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTD.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Represents a XML document type definition that provides a grammar for a class of documents.
 * 
 * @author Mark Hobson
 * @version $Id: DTD.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/REC-xml/#sec-prolog-dtd">2.8 Prolog and Document Type Declaration</a>
 * @see <a href="http://www.w3.org/TR/REC-xml/#elemdecls">3.2 Element Type Declarations</a>
 * @see <a href="http://www.w3.org/TR/REC-xml/#attdecls">3.3 Attribute-List Declarations</a>
 */
public class DTD implements Iterable<DTDElement>
{
	// fields -----------------------------------------------------------------
	
	private final Map<String, DTDElement> elements;
	
	// constructors -----------------------------------------------------------
	
	public DTD()
	{
		elements = new LinkedHashMap<String, DTDElement>();
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<DTDElement> iterator()
	{
		return getElements().iterator();
	}
	
	// public methods ---------------------------------------------------------
	
	public DTDElement addElement(String name, String model) throws DTDParseException
	{
		DTDElement element = new DTDElement(name, model);
		addElement(element);
		return element;
	}
	
	public void addElement(DTDElement element)
	{
		elements.put(element.getName(), element);
	}
	
	public void removeElement(DTDElement element)
	{
		elements.remove(element.getName());
	}
	
	public DTDElement getElement(Element element)
	{
		return getElement(element.getTagName());
	}
	
	public DTDElement getElement(String name)
	{
		return elements.get(name);
	}
	
	public Collection<DTDElement> getElements()
	{
		return Collections.unmodifiableCollection(elements.values());
	}
	
	public DTDAttribute getAttribute(Attr attribute)
	{
		Element element = attribute.getOwnerElement();
		return (element != null) ? getAttribute(element, attribute) : null;
	}
	
	public DTDAttribute getAttribute(Element element, Attr attribute)
	{
		DTDElement dtdElement = getElement(element);
		return (dtdElement != null) ? dtdElement.getAttribute(attribute) : null;
	}
	
	public DTDAttribute getAttribute(String elementName, String attributeName)
	{
		DTDElement element = getElement(elementName);
		return (element != null) ? element.getAttribute(attributeName) : null;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return elements.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTD))
		{
			return false;
		}
		
		DTD dtd = (DTD) object;
		
		return elements.equals(dtd.elements);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for (DTDElement element : this)
		{
			builder.append(element);
		}
		
		return builder.toString();
	}
}
