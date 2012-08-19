/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/Utilities.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Utilities.java 97397 2011-12-30 11:53:48Z mark@IIZUKA.CO.UK $
 */
final class Utilities
{
	// constructors -----------------------------------------------------------
	
	private Utilities()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> T checkNotNull(T object, String name)
	{
		if (object == null)
		{
			throw new NullPointerException(name + " cannot be null");
		}
		
		return object;
	}
	
	public static boolean nullEquals(Object a, Object b)
	{
		return (a == null) ? (b == null) : a.equals(b);
	}
}
