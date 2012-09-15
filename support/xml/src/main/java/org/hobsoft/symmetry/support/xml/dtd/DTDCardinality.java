/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDCardinality.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * An enumeration of the various occurrence types used by DTD content models.
 * 
 * @author	Mark Hobson
 * @version	$Id: DTDCardinality.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public enum DTDCardinality
{
	// constants --------------------------------------------------------------
	
	/**
	 * An occurrence of exactly once. This corresponds to the default syntax used in DTD.
	 */
	ONCE(""),
	
	/**
	 * An occurrence of one or more times. This corresponds to the "<code>+</code>" syntax used in DTD.
	 */
	ONE_OR_MORE("+"),
	
	/**
	 * An occurrence of zero or more times. This corresponds to the "<code>*</code>" syntax used in DTD.
	 */
	ZERO_OR_MORE("*"),
	
	/**
	 * An occurrence of zero or one times. This corresponds to the "<code>?</code>" syntax used in DTD.
	 */
	ZERO_OR_ONE("?");
	
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	// constructors -----------------------------------------------------------
	
	private DTDCardinality(String name)
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
