/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/stax/filter/QNameUtils.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import javax.xml.namespace.QName;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: QNameUtils.java 88560 2011-05-26 13:50:49Z mark@IIZUKA.CO.UK $
 */
final class QNameUtils
{
	// constants --------------------------------------------------------------
	
	private static final String WILDCARD = "*";
	
	// constructors -----------------------------------------------------------
	
	private QNameUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static boolean matches(QName pattern, QName value)
	{
		return matches(pattern.getNamespaceURI(), value.getNamespaceURI())
			&& matches(pattern.getLocalPart(), value.getLocalPart());
	}
	
	public static boolean matchesIgnoreCase(QName pattern, QName value)
	{
		return matches(pattern.getNamespaceURI(), value.getNamespaceURI())
			&& matchesIgnoreCase(pattern.getLocalPart(), value.getLocalPart());
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean matches(String pattern, String value)
	{
		return isWildcard(pattern) || pattern.equals(value);
	}
	
	private static boolean matchesIgnoreCase(String pattern, String value)
	{
		return isWildcard(pattern) || pattern.equalsIgnoreCase(value);
	}
	
	private static boolean isWildcard(String pattern)
	{
		return WILDCARD.equals(pattern);
	}
}
