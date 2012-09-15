/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/IdentityCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code IdentityCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: IdentityCodecTest.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @see IdentityCodec
 */
public class IdentityCodecTest
{
	// fields -----------------------------------------------------------------
	
	private IdentityCodec<Object> codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = IdentityCodec.get();
	}

	// tests ------------------------------------------------------------------
	
	// TODO: get

	@Test
	public void encode() throws EncoderException
	{
		Object object = new Object();
		
		assertSame(object, codec.encode(object));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		Object object = new Object();
		
		assertSame(object, codec.decode(object));
	}
	
	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
	
	// TODO: hashCode
	// TODO: equals
}
