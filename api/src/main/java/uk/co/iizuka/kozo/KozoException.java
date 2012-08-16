/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/KozoException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo;

/**
 * The superclass for all Kozo exceptions.
 * 
 * @author Mark Hobson
 * @version $Id: KozoException.java 95220 2011-11-16 15:04:18Z mark@IIZUKA.CO.UK $
 */
public class KozoException extends Exception
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 3258412820075786546L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code KozoException} with no message or cause.
	 */
	public KozoException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code KozoException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public KozoException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code KozoException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public KozoException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code KozoException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public KozoException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
