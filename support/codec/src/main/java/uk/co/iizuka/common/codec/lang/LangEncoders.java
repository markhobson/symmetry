/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/LangEncoders.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import uk.co.iizuka.common.codec.Encoder;

/**
 * Factory class for building lang encoders.
 * 
 * @author Mark Hobson
 * @version $Id: LangEncoders.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
public final class LangEncoders
{
	// constructors -----------------------------------------------------------
	
	private LangEncoders()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X> Encoder<X, String> objectToString()
	{
		return ObjectToStringEncoder.get();
	}
}
