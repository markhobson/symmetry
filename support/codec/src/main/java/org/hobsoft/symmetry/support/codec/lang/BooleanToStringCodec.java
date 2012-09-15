/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/BooleanToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a boolean to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: BooleanToStringCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
class BooleanToStringCodec implements Codec<Boolean, String>
{
	// constants --------------------------------------------------------------
	
	public static final BooleanToStringCodec LENIENT_INSTANCE = new BooleanToStringCodec(true);
	
	public static final BooleanToStringCodec STRICT_INSTANCE = new BooleanToStringCodec(false);
	
	private static final boolean DEFAULT_LENIENT = true;
	
	private static final String TRUE_TOKEN = Boolean.TRUE.toString();
	
	private static final String FALSE_TOKEN = Boolean.FALSE.toString();
	
	// fields -----------------------------------------------------------------
	
	private final boolean lenient;
	
	// constructors -----------------------------------------------------------
	
	public BooleanToStringCodec()
	{
		this(DEFAULT_LENIENT);
	}

	public BooleanToStringCodec(boolean lenient)
	{
		this.lenient = lenient;
	}
	
	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Boolean object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}

		if (!lenient && !TRUE_TOKEN.equalsIgnoreCase(string) && !FALSE_TOKEN.equalsIgnoreCase(string))
		{
			throw new DecoderException("Cannot parse boolean: " + string);
		}
		
		return Boolean.valueOf(string);
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean isLenient()
	{
		return lenient;
	}
}
