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
package org.hobsoft.symmetry.support.xml.sax.filter;

import org.hobsoft.symmetry.support.xml.dtd.DTD;
import org.hobsoft.symmetry.support.xml.dtd.DTDAttribute;
import org.hobsoft.symmetry.support.xml.dtd.DTDElement;
import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * 
 * 
 * @author	Mark Hobson
 */
public abstract class AbstractDTDXMLFilter extends XMLFilterImpl
{
	// fields -----------------------------------------------------------------
	
	private DTDProvider dtdProvider;
	
	// constructors -----------------------------------------------------------
	
	public AbstractDTDXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent);
		
		if (dtdProvider == null)
		{
			throw new NullPointerException("dtdProvider");
		}
		
		this.dtdProvider = dtdProvider;
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		DTD dtd = dtdProvider.getDTD();
		
		if (dtd != null)
		{
			DTDElement dtdElement = dtd.getElement(qName);
			
			if (dtdElement != null)
			{
				attributes = qualifyAttributes(attributes, dtdElement);
			}
		}
		
		super.startElement(uri, localName, qName, attributes);
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract String qualifyAttributeValue(DTDAttribute attribute, String value);
	
	// private methods --------------------------------------------------------
	
	private Attributes qualifyAttributes(Attributes attributes, DTDElement element)
	{
		AttributesImpl qualifiedAttributes = new AttributesImpl();
		
		for (DTDAttribute attribute : element)
		{
			String name = attribute.getName();
			String type = attribute.getType().toString();
			String value = attributes.getValue(name);
			
			String qualifiedValue = qualifyAttributeValue(attribute, value);
			
			if (qualifiedValue != null)
			{
				qualifiedAttributes.addAttribute("", name, name, type, qualifiedValue);
			}
		}
		
		return qualifiedAttributes;
	}
}
