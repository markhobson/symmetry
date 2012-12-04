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
package org.hobsoft.symmetry.support.io;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code StringDataInput}.
 * 
 * @author Mark Hobson
 * @see StringDataInput
 */
public class StringDataInputTest
{
	// fields -----------------------------------------------------------------
	
	private StringDataInput in;
	
	// TODO: readFully tests
	
	// TODO: skipBytes tests
	
	// constructor ------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void newStringDataInputWithNullInput()
	{
		new StringDataInput(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void newStringDataInputWithNullDelimiter()
	{
		new StringDataInput("x", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newStringDataInputWithEmptyDelimiter()
	{
		new StringDataInput("x", "");
	}
	
	// readBoolean tests ------------------------------------------------------
	
	@Test
	public void readBooleanTrue() throws IOException
	{
		in = new StringDataInput("true");
		
		assertTrue(in.readBoolean());
	}
	
	@Test
	public void readBooleanFalse() throws IOException
	{
		in = new StringDataInput("false");
		
		assertFalse(in.readBoolean());
	}
	
	@Test
	public void readBooleanDelimiter() throws IOException
	{
		in = new StringDataInput("true,true");
		
		assertTrue(in.readBoolean());
		assertTrue(in.readBoolean());
	}
	
	@Test(expected = EOFException.class)
	public void readBooleanEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readBoolean();
	}
	
	// readByte tests ---------------------------------------------------------
	
	@Test
	public void readByte() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals((byte) 123, in.readByte());
	}
	
	@Test
	public void readByteHighBit() throws IOException
	{
		in = new StringDataInput("-1");
		
		assertEquals((byte) -1, in.readByte());
	}
	
	@Test(expected = IOException.class)
	public void readByteTooSmall() throws IOException
	{
		in = new StringDataInput("-129");
		
		in.readByte();
	}
	
	@Test(expected = IOException.class)
	public void readByteTooLarge() throws IOException
	{
		in = new StringDataInput("128");
		
		in.readByte();
	}
	
	@Test(expected = IOException.class)
	public void readByteInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readByte();
	}
	
	@Test
	public void readByteDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals((byte) 123, in.readByte());
		assertEquals((byte) 123, in.readByte());
	}
	
	@Test(expected = EOFException.class)
	public void readByteEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readByte();
	}
	
	// readUnsignedByte tests -------------------------------------------------
	
	@Test
	public void readUnsignedByte() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals(123, in.readUnsignedByte());
	}
	
	@Test
	public void readUnsignedByteHighBit() throws IOException
	{
		in = new StringDataInput("255");
		
		assertEquals(255, in.readUnsignedByte());
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedByteTooSmall() throws IOException
	{
		in = new StringDataInput("-1");
		
		in.readUnsignedByte();
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedByteTooLarge() throws IOException
	{
		in = new StringDataInput("256");
		
		in.readUnsignedByte();
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedByteInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readUnsignedByte();
	}
	
	@Test
	public void readUnsignedByteDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals(123, in.readUnsignedByte());
		assertEquals(123, in.readUnsignedByte());
	}
	
	@Test(expected = EOFException.class)
	public void readUnsignedByteEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readUnsignedByte();
	}
	
	// readShort tests ---------------------------------------------------------
	
	@Test
	public void readShort() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals((short) 123, in.readShort());
	}
	
	@Test
	public void readShortHighBit() throws IOException
	{
		in = new StringDataInput("-1");
		
		assertEquals((short) -1, in.readShort());
	}
	
	@Test(expected = IOException.class)
	public void readShortTooSmall() throws IOException
	{
		in = new StringDataInput("-32769");
		
		in.readShort();
	}
	
	@Test(expected = IOException.class)
	public void readShortTooLarge() throws IOException
	{
		in = new StringDataInput("32768");
		
		in.readShort();
	}
	
	@Test(expected = IOException.class)
	public void readShortInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readShort();
	}
	
	@Test
	public void readShortDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals((short) 123, in.readShort());
		assertEquals((short) 123, in.readShort());
	}
	
	@Test(expected = EOFException.class)
	public void readShortEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readShort();
	}
	
	// readUnsignedShort tests ------------------------------------------------
	
	@Test
	public void readUnsignedShort() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals(123, in.readUnsignedShort());
	}
	
	@Test
	public void readUnsignedShortHighBit() throws IOException
	{
		in = new StringDataInput("65535");
		
		assertEquals(65535, in.readUnsignedShort());
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedShortTooSmall() throws IOException
	{
		in = new StringDataInput("-1");
		
		in.readUnsignedShort();
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedShortTooLarge() throws IOException
	{
		in = new StringDataInput("65536");

		in.readUnsignedShort();
	}
	
	@Test(expected = IOException.class)
	public void readUnsignedShortInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readUnsignedShort();
	}
	
	@Test
	public void readUnsignedShortDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals(123, in.readUnsignedShort());
		assertEquals(123, in.readUnsignedShort());
	}
	
	@Test(expected = EOFException.class)
	public void readUnsignedShortEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readUnsignedShort();
	}
	
	// readChar tests ---------------------------------------------------------
	
	@Test
	public void readChar() throws IOException
	{
		in = new StringDataInput("a");
		
		assertEquals('a', in.readChar());
	}
	
	@Test(expected = IOException.class)
	public void readCharInvalid() throws IOException
	{
		in = new StringDataInput("ab");
		
		in.readChar();
	}
	
	@Test
	public void readCharDelimiter() throws IOException
	{
		in = new StringDataInput("a,a");
		
		assertEquals('a', in.readChar());
		assertEquals('a', in.readChar());
	}
	
	@Test(expected = EOFException.class)
	public void readUnsignedCharEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readChar();
	}
	
	// readInt tests ----------------------------------------------------------
	
	@Test
	public void readInt() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals(123, in.readInt());
	}
	
	@Test(expected = IOException.class)
	public void readIntTooSmall() throws IOException
	{
		in = new StringDataInput("-2147483649");
		
		in.readInt();
	}
	
	@Test(expected = IOException.class)
	public void readIntTooLarge() throws IOException
	{
		in = new StringDataInput("2147483648");
		
		in.readInt();
	}
	
	@Test(expected = IOException.class)
	public void readIntInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readInt();
	}
	
	@Test
	public void readIntDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals(123, in.readInt());
		assertEquals(123, in.readInt());
	}
	
	@Test(expected = EOFException.class)
	public void readIntEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readInt();
	}
	
	// readLong tests ---------------------------------------------------------
	
	@Test
	public void readLong() throws IOException
	{
		in = new StringDataInput("123");
		
		assertEquals(123L, in.readLong());
	}
	
	@Test(expected = IOException.class)
	public void readLongTooSmall() throws IOException
	{
		in = new StringDataInput("-9223372036854775809");
		
		in.readLong();
	}
	
	@Test(expected = IOException.class)
	public void readLongTooLarge() throws IOException
	{
		in = new StringDataInput("9223372036854775808");
		
		in.readLong();
	}
	
	@Test(expected = IOException.class)
	public void readLongInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readLong();
	}
	
	@Test
	public void readLongDelimiter() throws IOException
	{
		in = new StringDataInput("123,123");
		
		assertEquals(123L, in.readLong());
		assertEquals(123L, in.readLong());
	}
	
	@Test(expected = EOFException.class)
	public void readLongEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readLong();
	}
	
	// readFloat tests --------------------------------------------------------
	
	@Test
	public void readFloat() throws IOException
	{
		in = new StringDataInput("123.0");
		
		assertEquals(123f, in.readFloat(), 0);
	}
	
	@Test(expected = IOException.class)
	public void readFloatInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readFloat();
	}
	
	@Test
	public void readFloatDelimiter() throws IOException
	{
		in = new StringDataInput("123.0,123.0");
		
		assertEquals(123f, in.readFloat(), 0);
		assertEquals(123f, in.readFloat(), 0);
	}
	
	@Test(expected = EOFException.class)
	public void readFloatEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readFloat();
	}
	
	// readDouble tests -------------------------------------------------------
	
	@Test
	public void readDouble() throws IOException
	{
		in = new StringDataInput("123.0");
		
		assertEquals(123d, in.readDouble(), 0);
	}
	
	@Test(expected = IOException.class)
	public void readDoubleInvalid() throws IOException
	{
		in = new StringDataInput("a");
		
		in.readDouble();
	}
	
	@Test
	public void readDoubleDelimiter() throws IOException
	{
		in = new StringDataInput("123.0,123.0");
		
		assertEquals(123d, in.readDouble(), 0);
		assertEquals(123d, in.readDouble(), 0);
	}
	
	@Test(expected = EOFException.class)
	public void readDoubleEOF() throws IOException
	{
		in = new StringDataInput("");
		
		in.readDouble();
	}
	
	// TODO: readLine tests
	
	// readUTF tests ----------------------------------------------------------
	
	@Test
	public void readUTF() throws IOException
	{
		in = new StringDataInput("abc");
		
		assertEquals("abc", in.readUTF());
	}
	
	@Test
	public void readUTFDelimiter() throws IOException
	{
		in = new StringDataInput("abc,abc");
		
		assertEquals("abc", in.readUTF());
		assertEquals("abc", in.readUTF());
	}
	
	@Test
	public void readUTFEmpty() throws IOException
	{
		in = new StringDataInput("");
		
		assertEquals("", in.readUTF());
	}
}
