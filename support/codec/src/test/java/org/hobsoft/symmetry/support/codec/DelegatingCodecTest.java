/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/DelegatingCodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.B;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests <code>DelegatingCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: DelegatingCodecTest.java 75367 2010-07-23 17:59:07Z mark@IIZUKA.CO.UK $
 * @see		DelegatingCodec
 */
@RunWith(JMock.class)
public class DelegatingCodecTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery context = new JUnit4Mockery();

	private Codec<A, B> mockCodec;

	private DelegatingCodec<A, B> codec;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: can we avoid this cast warning without @SuppressWarnings
		@SuppressWarnings("unchecked")
		Codec<A, B> uncheckedMockCodec = context.mock(Codec.class);
		
		mockCodec = uncheckedMockCodec;
		
		codec = new DelegatingCodec<A, B>(mockCodec);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNull()
	{
		new DelegatingCodec<A, B>(null);
	}
	
	@Test
	public void encode() throws EncoderException
	{
		final A a = new A("a");
		
		context.checking(new Expectations() { {
			one(mockCodec).encode(a);
		} });
		
		codec.encode(a);
	}
	
	@Test
	public void decode() throws DecoderException
	{
		final B b = new B("b");
		
		context.checking(new Expectations() { {
			one(mockCodec).decode(b);
		} });
		
		codec.decode(b);
	}
	
	@Test
	public void getCodec()
	{
		assertEquals(mockCodec, codec.getCodec());
	}
}
