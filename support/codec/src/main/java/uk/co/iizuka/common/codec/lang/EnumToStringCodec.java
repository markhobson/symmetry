/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/EnumToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import org.apache.commons.lang.Validate;

import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes an enum to a string and vice-versa.
 * 
 * @author Mark Hobson
 * @version $Id: EnumToStringCodec.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 * @param <E>
 *            the enum type that this codec can encode
 */
class EnumToStringCodec<E extends Enum<E>> implements Codec<E, String>
{
	// fields -----------------------------------------------------------------
	
	private final Class<E> enumType;

	// constructors -----------------------------------------------------------
	
	public EnumToStringCodec(Class<E> enumType)
	{
		Validate.notNull(enumType, "enumType cannot be null");
		
		this.enumType = enumType;
	}

	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(E object) throws EncoderException
	{
		return ObjectToStringEncoder.get().encode(object);
	}

	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public E decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		try
		{
			return Enum.valueOf(enumType, string);
		}
		catch (IllegalArgumentException exception)
		{
			throw new DecoderException("Cannot parse enum: " + string);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public static <E extends Enum<E>> EnumToStringCodec<E> get(Class<E> enumType)
	{
		return new EnumToStringCodec<E>(enumType);
	}
}
