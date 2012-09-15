/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAttrDeclParser.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * Builds <code>DTDAttrDecl</code> instances from their DTD string representations.
 * 
 * @author Mark Hobson
 * @version $Id: DTDAttrDeclParser.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see DTDAttrDecl
 */
public final class DTDAttrDeclParser
{
	// constructors -----------------------------------------------------------
	
	private DTDAttrDeclParser()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static DTDAttrDecl parse(String mode, String value)
	{
		if ("#REQUIRED".equals(mode))
		{
			return DTDAttrDecls.REQUIRED;
		}
		
		if ("#IMPLIED".equals(mode))
		{
			return DTDAttrDecls.IMPLIED;
		}
		
		if ("#FIXED".equals(mode))
		{
			return new DTDFixedAttrDecl(value);
		}
		
		return new DTDDefaultAttrDecl(value);
	}
}
