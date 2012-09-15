/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDChoiceContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A compound DTD element content model whose comprising content models are mutually-exclusive.
 * 
 * @author Mark Hobson
 * @version $Id: DTDChoiceContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDChoiceContent extends DTDChoiceContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDChoiceContent()
	{
		super();
	}
	
	public DTDChoiceContent(DTDCardinality cardinality)
	{
		super(cardinality);
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
		if (!(object instanceof DTDChoiceContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
