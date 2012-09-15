/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/CodecException.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

/**
 * Convenience base class for all codec exceptions.
 * 
 * @author	Mark Hobson
 * @version	$Id: CodecException.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public abstract class CodecException extends Exception
{
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a codec exception with the specified message.
	 * 
	 * @param	message
	 * 				the message for this exception
	 */
	public CodecException(String message)
	{
		super(message);
	}
}
