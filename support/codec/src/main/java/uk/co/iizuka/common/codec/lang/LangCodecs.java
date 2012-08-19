/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/LangCodecs.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;

import uk.co.iizuka.common.codec.ByteStreamCodec;
import uk.co.iizuka.common.codec.CharStreamCodec;
import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.Codecs;

/**
 * Factory class for building lang codecs.
 * 
 * @author Mark Hobson
 * @version $Id: LangCodecs.java 75588 2010-08-02 20:52:50Z mark@IIZUKA.CO.UK $
 */
public final class LangCodecs
{
	// constructors -----------------------------------------------------------
	
	private LangCodecs()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Codec<Boolean, String> booleanToString()
	{
		return booleanToString(true);
	}

	public static Codec<Boolean, String> booleanToString(boolean lenient)
	{
		return lenient ? BooleanToStringCodec.LENIENT_INSTANCE : BooleanToStringCodec.STRICT_INSTANCE;
	}
	
	public static Codec<Byte, String> byteToString()
	{
		return ByteToStringCodec.INSTANCE;
	}
	
	public static Codec<Character, String> characterToString()
	{
		return CharacterToStringCodec.INSTANCE;
	}
	
	public static Codec<Double, String> doubleToString()
	{
		return DoubleToStringCodec.INSTANCE;
	}
	
	public static Codec<Float, String> floatToString()
	{
		return FloatToStringCodec.INSTANCE;
	}
	
	public static Codec<Integer, String> integerToString()
	{
		return IntegerToStringCodec.INSTANCE;
	}
	
	public static Codec<Long, String> longToString()
	{
		return LongToStringCodec.INSTANCE;
	}
	
	public static Codec<Short, String> shortToString()
	{
		return ShortToStringCodec.INSTANCE;
	}
	
	public static ByteStreamCodec<byte[]> byteArrayToStream()
	{
		return ByteArrayToStreamCodec.INSTANCE;
	}
	
	public static CharStreamCodec<char[]> charArrayToStream()
	{
		return CharArrayToStreamCodec.INSTANCE;
	}
	
	public static CharStreamCodec<String> stringToCharStream()
	{
		return StringToCharStreamCodec.INSTANCE;
	}
	
	public static ByteStreamCodec<String> stringToByteStream()
	{
		return Codecs.asByteStream(stringToCharStream());
	}
	
	public static ByteStreamCodec<String> stringToByteStream(String charsetName)
	{
		return Codecs.asByteStream(stringToCharStream(), charsetName);
	}
	
	public static ByteStreamCodec<String> stringToByteStream(Charset charset)
	{
		return Codecs.asByteStream(stringToCharStream(), charset);
	}
	
	public static <E extends Enum<E>> Codec<E, String> enumToString(Class<E> enumType)
	{
		return EnumToStringCodec.get(enumType);
	}
	
	public static Codec<Date, String> dateToString()
	{
		return DateToStringCodec.INSTANCE;
	}
	
	public static Codec<Date, String> dateToString(String pattern)
	{
		return new DateToStringCodec(pattern);
	}
	
	public static Codec<Date, String> dateToString(DateFormat dateFormat)
	{
		return new DateToStringCodec(dateFormat);
	}
}
