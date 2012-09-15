/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDDefaultAttrDecl.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A DTD attribute declaration that provides a default attribute value. This corresponds to the default declaration in
 * DTD.
 * 
 * @author Mark Hobson
 */
public class DTDDefaultAttrDecl extends DTDValuedAttrDecl
{
	// constructors -----------------------------------------------------------
	
	public DTDDefaultAttrDecl(String value)
	{
		super(value);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDDefaultAttrDecl))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
