/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/NotNullCodecTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.codec.test.A;
import uk.co.iizuka.common.codec.test.ABCodec;
import uk.co.iizuka.common.codec.test.B;

/**
 * Tests {@code NotNullCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: NotNullCodecTest.java 75370 2010-07-26 10:10:09Z mark@IIZUKA.CO.UK $
 * @see NotNullCodec
 */
public class NotNullCodecTest
{
	// fields -----------------------------------------------------------------
	
	private NotNullCodec<A, B> codec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new NotNullCodec<A, B>(new ABCodec());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals(new B("a"), codec.encode(new A("a")));
	}
	
	@Test(expected = EncoderException.class)
	public void encodeWithNull() throws EncoderException
	{
		try
		{
			codec.encode(null);
		}
		catch (EncoderException exception)
		{
			assertEquals("Object cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(new A("b"), codec.decode(new B("b")));
	}
	
	@Test(expected = DecoderException.class)
	public void decodeWithNull() throws DecoderException
	{
		try
		{
			codec.decode(null);
		}
		catch (DecoderException exception)
		{
			assertEquals("Object cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
}
