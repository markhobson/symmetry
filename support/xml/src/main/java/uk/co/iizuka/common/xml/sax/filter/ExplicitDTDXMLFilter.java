/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/filter/ExplicitDTDXMLFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax.filter;

import org.xml.sax.XMLReader;

import uk.co.iizuka.common.xml.dtd.DTDAttrDecl;
import uk.co.iizuka.common.xml.dtd.DTDAttrDecls;
import uk.co.iizuka.common.xml.dtd.DTDAttribute;
import uk.co.iizuka.common.xml.dtd.DTDDefaultAttrDecl;
import uk.co.iizuka.common.xml.dtd.DTDFixedAttrDecl;
import uk.co.iizuka.common.xml.dtd.DTDProvider;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ExplicitDTDXMLFilter.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
