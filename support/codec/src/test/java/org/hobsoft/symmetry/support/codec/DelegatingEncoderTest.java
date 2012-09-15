/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/DelegatingEncoderTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.B;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code DelegatingEncoder}.
 * 
 * @author Mark Hobson
 * @see DelegatingEncoder
 */
@RunWith(JMock.class)
public class DelegatingEncoderTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery context = new JUnit4Mockery();

	private Encoder<A, B> mockEncoder;

	private DelegatingEncoder<A, B> encoder;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: can we avoid this cast warning without @SuppressWarnings
		@SuppressWarnings("unchecked")
		Encoder<A, B> uncheckedMockEncoder = context.mock(Encoder.class);
		
		mockEncoder = uncheckedMockEncoder;
		
		encoder = new DelegatingEncoder<A, B>(mockEncoder);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNull()
	{
		new DelegatingEncoder<A, B>(null);
	}
	
	@Test
	public void encode() throws EncoderException
	{
		final A a = new A("a");
		
		context.checking(new Expectations() { {
			one(mockEncoder).encode(a);
		} });
		
		encoder.encode(a);
	}
	
	@Test
	public void getEncoder()
	{
		assertEquals(mockEncoder, encoder.getEncoder());
	}
}
