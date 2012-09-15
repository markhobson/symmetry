/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/EncoderException.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Indicates an error occurred when encoding an object.
 * 
 * @author	Mark Hobson
 * @version	$Id: EncoderException.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public class EncoderException extends CodecException
{
	// constants --------------------------------------------------------------

	/**
	 * The serial version id.
	 */
	private static final long serialVersionUID = 5342437922597611761L;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a encoder exception with the specified message.
	 * 
	 * @param	message
	 * 				the message for this exception
	 */
	public EncoderException(String message)
	{
		super(message);
	}
}
