/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAttrDecls.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * An enumeration of the basic DTD attribute declarations.
 * 
 * @author Mark Hobson
 */
public enum DTDAttrDecls implements DTDAttrDecl
{
	// constants --------------------------------------------------------------
	
	/**
	 * A DTD attribute declaration that requires the attribute to always be provided. This corresponds to the
	 * <code>#REQUIRED</code> declaration in DTD.
	 */
	REQUIRED("#REQUIRED"),
	
	/**
	 * A DTD attribute declaration that provides no default attribute value. This corresponds to the
	 * <code>#IMPLIED</code> declaration in DTD.
	 */
	IMPLIED("#IMPLIED");

	// fields -----------------------------------------------------------------
	
	private final String name;

	// constructors -----------------------------------------------------------
	
	private DTDAttrDecls(String name)
	{
		this.name = name;
	}

	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name;
	}
}
