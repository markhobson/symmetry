/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/CompoundCodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.codec.test.A;
import uk.co.iizuka.common.codec.test.ABCodec;
import uk.co.iizuka.common.codec.test.B;
import uk.co.iizuka.common.codec.test.BCCodec;
import uk.co.iizuka.common.codec.test.C;

/**
 * Tests <code>CompoundCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: CompoundCodecTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		CompoundCodec
 */
public class CompoundCodecTest
{
	// fields -----------------------------------------------------------------
	
	private Codec<A, B> codec1;
	
	private Codec<B, C> codec2;
	
	private Codec<A, C> compoundCodec;
	
	private Codec<A, C> compoundCodec2;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		codec1 = new ABCodec();
		codec2 = new BCCodec();
		
		compoundCodec = new CompoundCodec<A, B, C>(codec1, codec2);
		compoundCodec2 = new CompoundCodec<A, B, C>(codec1, codec2);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodec1Null()
	{
		try
		{
			new CompoundCodec<A, B, C>(null, codec2);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec1 cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCodec2Null()
	{
		try
		{
			new CompoundCodec<A, B, C>(codec1, null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("codec2 cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		assertEquals(new C("a"), compoundCodec.encode(new A("a")));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		assertEquals(new A("c"), compoundCodec.decode(new C("c")));
	}
	
	@Test
	public void hashCodeTest()
	{
		assertEquals("Hash code", compoundCodec.hashCode(), compoundCodec2.hashCode());
	}
	
	@Test
	public void equals()
	{
		assertTrue("Expected equal", compoundCodec.equals(compoundCodec2));
	}
	
	@Test
	public void equalsWhenUnequal()
	{
		Codec<B, A> codec3 = new InverseCodec<B, A>(codec1);
		CompoundCodec<A, B, A> compoundCodec3 = new CompoundCodec<A, B, A>(codec1, codec3);
		
		assertFalse("Expected unequal", compoundCodec.equals(compoundCodec3));
	}
}
