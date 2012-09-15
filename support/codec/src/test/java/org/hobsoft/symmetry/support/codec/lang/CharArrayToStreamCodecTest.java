/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/CharArrayToStreamCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code CharArrayToStreamCodec}.
 * 
 * @author Mark Hobson
 * @see CharArrayToStreamCodec
 */
public class CharArrayToStreamCodecTest
{
	// fields -----------------------------------------------------------------
	
	private CharArrayToStreamCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new CharArrayToStreamCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertArrayEquals(new char[] {'a', 'b', 'c'}, IOUtils.toCharArray(codec.encode(new char[] {'a', 'b', 'c'})));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		codec.encodeTo(new char[] {'a', 'b', 'c'}, writer);
		
		assertArrayEquals(new char[] {'a', 'b', 'c'}, writer.toCharArray());
	}
	
	@Test
	public void encodeToWithNull() throws EncoderException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		codec.encodeTo(null, writer);
		
		assertArrayEquals(new char[0], writer.toCharArray());
	}
	
	@Test
	public void decode() throws DecoderException
	{
		CharArrayReader reader = new CharArrayReader(new char[] {'a', 'b', 'c'});
		
		assertArrayEquals(new char[] {'a', 'b', 'c'}, codec.decode(reader));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
