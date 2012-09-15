/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/CharByteStreamCodecAdapterTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code CharByteStreamCodecAdapter}.
 * 
 * @author Mark Hobson
 * @version $Id: CharByteStreamCodecAdapterTest.java 75587 2010-08-02 20:41:05Z mark@IIZUKA.CO.UK $
 * @see CharByteStreamCodecAdapter
 */
public class CharByteStreamCodecAdapterTest
{
	// fields -----------------------------------------------------------------
	
	private CharByteStreamCodecAdapter<String> adapter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		adapter = new CharByteStreamCodecAdapter<String>(new StubCharStreamCodec());
	}

	// public methods ---------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException, IOException
	{
		assertEquals("a", IOUtils.toString(adapter.encode("a"), "UTF-8"));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(adapter.encode(null));
	}
	
	@Test
	public void encodeTo() throws EncoderException, IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		adapter.encodeTo("a", out);
		
		assertEquals("a", out.toString("UTF-8"));
	}
	
	@Test
	public void decode() throws DecoderException, IOException
	{
		assertEquals("a", adapter.decode(new ByteArrayInputStream("a".getBytes("UTF-8"))));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(adapter.decode(null));
	}
}
