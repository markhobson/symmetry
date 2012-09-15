/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/lang/ByteArrayToStreamCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code ByteArrayToStreamCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: ByteArrayToStreamCodecTest.java 75589 2010-08-02 20:54:55Z mark@IIZUKA.CO.UK $
 * @see ByteArrayToStreamCodec
 */
public class ByteArrayToStreamCodecTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayToStreamCodec codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new ByteArrayToStreamCodec();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertArrayEquals(new byte[] {1, 2, 3}, IOUtils.toByteArray(codec.encode(new byte[] {1, 2, 3})));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		codec.encodeTo(new byte[] {1, 2, 3}, out);
		
		assertArrayEquals(new byte[] {1, 2, 3}, out.toByteArray());
	}

	@Test
	public void encodeToWithNull() throws EncoderException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		codec.encodeTo(null, out);
		
		assertArrayEquals(new byte[0], out.toByteArray());
	}
	
	@Test
	public void decode() throws DecoderException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(new byte[] {1, 2, 3});
		
		assertArrayEquals(new byte[] {1, 2, 3}, codec.decode(in));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
