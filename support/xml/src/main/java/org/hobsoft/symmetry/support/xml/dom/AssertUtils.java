/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/AssertUtils.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

/**
 * Provides methods to validate arguments.
 * 
 * @author Mark Hobson
 * @version $Id: AssertUtils.java 69825 2010-01-21 18:03:52Z mark@IIZUKA.CO.UK $
 */
final class AssertUtils
{
	// constructors -----------------------------------------------------------
	
	private AssertUtils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static void assertNotNull(String name, Object value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException(name + " cannot be null");
		}
	}
}
