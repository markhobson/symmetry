/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/InverseCodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.ABCodec;
import org.hobsoft.symmetry.support.codec.test.B;
import org.hobsoft.symmetry.support.codec.test.BCCodec;
import org.hobsoft.symmetry.support.codec.test.C;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests <code>InverseCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: InverseCodecTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		InverseCodec
 */
public class InverseCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Codec<A, B> codec;
	
	private Codec<B, A> inverseCodec;
	
	private Codec<B, A> inverseCodec2;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		codec = new ABCodec();

		inverseCodec = new InverseCodec<B, A>(codec);
		inverseCodec2 = new InverseCodec<B, A>(codec);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodecNull()
	{
		try
		{
			new InverseCodec<B, A>(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals("Encoded object", new A("b"), inverseCodec.encode(new B("b")));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals("Decoded object", new B("a"), inverseCodec.decode(new A("a")));
	}
	
	@Test
	public void hashCodeTest()
	{
		assertEquals("Hash code", inverseCodec.hashCode(), inverseCodec2.hashCode());
	}
	
	@Test
	public void equals()
	{
		assertTrue("Expected equal", inverseCodec.equals(inverseCodec2));
	}
	
	@Test
	public void equalsWhenUnequal()
	{
		InverseCodec<C, B> inverseCodec3 = new InverseCodec<C, B>(new BCCodec());
		
		assertFalse("Expected unequal", inverseCodec.equals(inverseCodec3));
	}
}
