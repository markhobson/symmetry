/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDEmptyContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * A DTD element content model that disallows any content.
 * 
 * @author Mark Hobson
 * @version $Id: DTDEmptyContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public final class DTDEmptyContent implements DTDContent
{
	// constants --------------------------------------------------------------
	
	public static final DTDEmptyContent INSTANCE = new DTDEmptyContent();
	
	// constructors -----------------------------------------------------------
	
	private DTDEmptyContent()
	{
		// private constructor for singleton
	}

	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		return (index < elements.length) ? -1 : elements.length;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "EMPTY";
	}
}
