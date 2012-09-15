/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/ImplicitDTDXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
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
public class ImplicitDTDXMLFilter extends AbstractDTDXMLFilter
{
	// constructors -----------------------------------------------------------
	
	public ImplicitDTDXMLFilter(XMLReader parent, DTDProvider dtdProvider)
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
			if ("".equals(value))
			{
				value = null;
			}
		}
		else if (declaration instanceof DTDFixedAttrDecl)
		{
			value = null;
		}
		else if (declaration instanceof DTDDefaultAttrDecl)
		{
			String defaultValue = ((DTDDefaultAttrDecl) declaration).getValue();
			
			if (defaultValue.equals(value))
			{
				value = null;
			}
		}
		
		return value;
	}
}
