/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/Utilities.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class Utilities
{
	// TODO: centralise
	
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
}
