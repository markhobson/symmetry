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