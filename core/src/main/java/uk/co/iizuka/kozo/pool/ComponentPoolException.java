/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/ComponentPoolException.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.pool;

import uk.co.iizuka.kozo.KozoException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentPoolException.java 95220 2011-11-16 15:04:18Z mark@IIZUKA.CO.UK $
 */
public class ComponentPoolException extends KozoException
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 5836476041196557338L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code ComponentPoolException} with no message or cause.
	 */
	public ComponentPoolException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public ComponentPoolException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public ComponentPoolException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public ComponentPoolException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
