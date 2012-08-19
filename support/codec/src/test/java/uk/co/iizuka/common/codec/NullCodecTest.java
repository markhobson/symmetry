/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/NullCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code NullCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: NullCodecTest.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @see NullCodec
 */
public class NullCodecTest
{
	// fields -----------------------------------------------------------------
	
	private NullCodec<Object, Object> codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = NullCodec.get();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void get()
	{
		assertEquals(codec, NullCodec.get());
	}
	
	@Test
	public void getReturnsSameInstance()
	{
		assertSame(codec, NullCodec.get());
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertNull(codec.encode(new Object()));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertNull(codec.decode(new Object()));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
	
	// TODO: hashCode
	// TODO: equals
}
