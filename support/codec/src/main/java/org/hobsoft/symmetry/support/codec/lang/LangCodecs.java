/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.codec.lang;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;

import org.hobsoft.symmetry.support.codec.ByteStreamCodec;
import org.hobsoft.symmetry.support.codec.CharStreamCodec;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.Codecs;

/**
 * Factory class for building lang codecs.
 * 
 * @author Mark Hobson
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
