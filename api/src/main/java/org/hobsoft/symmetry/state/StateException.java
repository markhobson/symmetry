/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/StateException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import org.hobsoft.symmetry.KozoException;

/**
 * Indicates that a serious problem was encountered whilst encoding or decoding a bean's state.
 * 
 * @author Mark Hobson
 */
public class StateException extends KozoException
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 3616727192764299056L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code StateException} with no message or cause.
	 */
	public StateException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code StateException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public StateException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code StateException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public StateException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code StateException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public StateException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
