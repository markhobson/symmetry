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
package org.hobsoft.symmetry.support.xml.sax;

import org.hobsoft.symmetry.support.xml.dtd.DTD;
import org.hobsoft.symmetry.support.xml.dtd.DTDElement;
import org.hobsoft.symmetry.support.xml.dtd.DTDParseException;
import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DTDDeclHandler implements DeclHandler, DTDProvider
{
	// fields -----------------------------------------------------------------
	
	private final DTD dtd;
	
	// constructors -----------------------------------------------------------
	
	public DTDDeclHandler()
	{
		dtd = new DTD();
	}
	
	// DeclHandler methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public void elementDecl(String name, String model) throws SAXException
	{
		// workaround for bug #4519431 in crimson for jdk1.4
		if (model.startsWith("(#PCDATA") && !model.endsWith("*"))
		{
			model += "*";
		}
		
		try
		{
			dtd.addElement(name, model);
		}
		catch (DTDParseException exception)
		{
			throw new SAXException(exception);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void attributeDecl(String eName, String aName, String type, String mode, String value) throws SAXException
	{
		// workaround for bug in crimson for jdk1.4
		if (!"CDATA".equals(type)
			&& !"ID".equals(type)
			&& !"IDREF".equals(type)
			&& !"IDREFS".equals(type)
			&& !"ENTITY".equals(type)
			&& !"ENTITIES".equals(type)
			&& !"NMTOKEN".equals(type)
			&& !"NMTOKENS".equals(type)
			&& !type.startsWith("("))
		{
			type = "(" + type + ")";
		}
		
		DTDElement element = dtd.getElement(eName);
		if (element == null)
		{
			element = new DTDElement(eName);
			dtd.addElement(element);
		}
		
		try
		{
			element.addAttribute(aName, type, mode, value);
		}
		catch (DTDParseException exception)
		{
			throw new SAXException(exception);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void internalEntityDecl(String name, String value) throws SAXException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException
	{
		// no-op
	}
	
	// DTDProvider methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public DTD getDTD()
	{
		return dtd;
	}
}
