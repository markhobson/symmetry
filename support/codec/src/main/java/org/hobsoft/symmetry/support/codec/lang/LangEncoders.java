/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/LangEncoders.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Encoder;

/**
 * Factory class for building lang encoders.
 * 
 * @author Mark Hobson
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
