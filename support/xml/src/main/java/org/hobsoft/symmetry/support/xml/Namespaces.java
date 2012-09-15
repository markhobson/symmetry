/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/Namespaces.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml;

/**
 * Provides constants related to the W3C XML Namespaces specification.
 * 
 * @author Mark Hobson
 * @version $Id: Namespaces.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/REC-xml-names/">Namespaces in XML</a>
 */
public final class Namespaces
{
	// constants --------------------------------------------------------------
	
	/**
	 * The XML Namespaces namespace URI.
	 */
	public static final String XMLNS = "http://www.w3.org/2000/xmlns/";
	
	/**
	 * The XML Namespaces prefix.
	 */
	public static final String PREFIX = "xmlns";
	
	// constructors -----------------------------------------------------------
	
	private Namespaces()
	{
		throw new AssertionError();
	}
}
