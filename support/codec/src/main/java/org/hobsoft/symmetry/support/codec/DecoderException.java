/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/DecoderException.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Indicates an error occurred when decoding an object.
 * 
 * @author	Mark Hobson
 * @version	$Id: DecoderException.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public class DecoderException extends CodecException
{
	// constants --------------------------------------------------------------

	/**
	 * The serial version id.
	 */
	private static final long serialVersionUID = -6035083662374721429L;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a decoder exception with the specified message.
	 * 
	 * @param	message
	 * 				the message for this exception
	 */
	public DecoderException(String message)
	{
		super(message);
	}
}
