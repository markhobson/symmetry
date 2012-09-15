/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDValuedAttrDecl.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * An abstract DTD attribute declaration that can hold a string value.
 * 
 * @author	Mark Hobson
 * @version	$Id: DTDValuedAttrDecl.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class DTDValuedAttrDecl implements DTDAttrDecl
{
	// fields -----------------------------------------------------------------
	
	private final String value;
	
	// constructors -----------------------------------------------------------
	
	public DTDValuedAttrDecl(String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value: null");
		}
		
		this.value = value;
	}
	
	// public methods ---------------------------------------------------------
	
	public String getValue()
	{
		return value;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return value.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDValuedAttrDecl))
		{
			return false;
		}
		
		DTDValuedAttrDecl decl = (DTDValuedAttrDecl) object;
		
		return value.equals(decl.getValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "'" + value + "'";
	}
}
