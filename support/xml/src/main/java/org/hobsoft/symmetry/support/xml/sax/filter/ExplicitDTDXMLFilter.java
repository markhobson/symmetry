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

import org.hobsoft.symmetry.support.xml.dtd.DTDAttrDecl;
import org.hobsoft.symmetry.support.xml.dtd.DTDAttrDecls;
import org.hobsoft.symmetry.support.xml.dtd.DTDAttribute;
import org.hobsoft.symmetry.support.xml.dtd.DTDDefaultAttrDecl;
import org.hobsoft.symmetry.support.xml.dtd.DTDFixedAttrDecl;
import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.XMLReader;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ExplicitDTDXMLFilter extends AbstractDTDXMLFilter
{
	// constructors -----------------------------------------------------------
	
	public ExplicitDTDXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent, dtdProvider);
	}
	
	// AbstractDTDXMLFilter methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String qualifyAttributeValue(DTDAttribute attribute, String value)
	{
		DTDAttrDecl declaration = attribute.getDeclaration();
		
		if (declaration == DTDAttrDecls.REQUIRED)
		{
			if (value == null)
			{
				throw new IllegalArgumentException("Attribute is " + declaration + ": " + attribute.getName());
			}
		}
		else if (declaration == DTDAttrDecls.IMPLIED)
		{
			if (value == null)
			{
				value = "";
			}
		}
		else if (declaration instanceof DTDFixedAttrDecl)
		{
			value = ((DTDFixedAttrDecl) declaration).getValue();
		}
		else if (declaration instanceof DTDDefaultAttrDecl)
		{
			if (value == null)
			{
				value = ((DTDDefaultAttrDecl) declaration).getValue();
			}
		}
		
		return value;
	}
}
