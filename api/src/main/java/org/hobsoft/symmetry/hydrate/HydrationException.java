/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/HydrationException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import org.hobsoft.symmetry.KozoException;

/**
 * Indicates that a serious problem was encountered during hydration.
 * 
 * @author Mark Hobson
 * @since 0.1
 */
public class HydrationException extends KozoException
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 3617007555394812213L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code HydrationException} with no message or cause.
	 */
	public HydrationException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code HydrationException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public HydrationException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code HydrationException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public HydrationException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code HydrationException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public HydrationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
