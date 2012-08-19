/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAnyContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * A DTD element content model that allows all elements and character data.
 * 
 * @author Mark Hobson
 * @version $Id: DTDAnyContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public final class DTDAnyContent implements DTDContent
{
	// constants --------------------------------------------------------------
	
	public static final DTDAnyContent INSTANCE = new DTDAnyContent();
	
	// constructors -----------------------------------------------------------
	
	private DTDAnyContent()
	{
		// private constructor for singleton
	}

	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		// accept all elements
		return elements.length;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "ANY";
	}
}
