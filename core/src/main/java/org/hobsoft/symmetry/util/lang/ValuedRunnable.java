/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/lang/ValuedRunnable.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.lang;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class ValuedRunnable implements Runnable
{
	// TODO: replace with Callable when upgrading to jdk1.5
	
	// fields -----------------------------------------------------------------
	
	private Object value;
	
	// public methods ---------------------------------------------------------
	
	public Object getValue()
	{
		return value;
	}
	
	// public methods ---------------------------------------------------------
	
	protected void setValue(Object value)
	{
		this.value = value;
	}
}
