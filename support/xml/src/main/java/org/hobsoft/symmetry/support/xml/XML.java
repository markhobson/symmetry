/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/XML.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class XML
{
	// constructors -----------------------------------------------------------
	
	private XML()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Determines whether the specified character is a valid XML character.
	 * 
	 * @param c
	 *            the character
	 * @return {@code true} if the specified character is a valid XML character
	 * @see <a href="http://www.w3.org/TR/2006/REC-xml-20060816/#charsets">Extensible Markup Language (XML) 1.0 (Fourth
	 *      Edition)</a>
	 */
	public static boolean isValidChar(char c)
	{
		if (c == 0x9 || c == 0xA || c == 0xD)
		{
			return true;
		}
		
		if (c >= 0x20 && c <= 0xD7FF)
		{
			return true;
		}
		
		if (c >= 0xE000 && c <= 0xFFFD)
		{
			return true;
		}
		
		if (c >= 0x10000 && c <= 0x10FFFF)
		{
			return true;
		}
		
		return false;
	}
}
