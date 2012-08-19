/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDEnumAttrType.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * A DTD attribute type that represents an enumeration of values.
 * 
 * @author	Mark Hobson
 * @version	$Id: DTDEnumAttrType.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDEnumAttrType extends DTDValuedAttrType
{
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
		if (!(object instanceof DTDEnumAttrType))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
