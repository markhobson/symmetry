/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/math/MathCodecs.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.hobsoft.symmetry.support.codec.Codec;

/**
 * Factory class for building math codecs.
 * 
 * @author Mark Hobson
 * @version $Id: MathCodecs.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
public final class MathCodecs
{
	// constructors -----------------------------------------------------------
	
	private MathCodecs()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Codec<BigDecimal, String> bigDecimalToString()
	{
		return BigDecimalToStringCodec.INSTANCE;
	}
	
	public static Codec<BigInteger, String> bigIntegerToString()
	{
		return BigIntegerToStringCodec.INSTANCE;
	}
}
