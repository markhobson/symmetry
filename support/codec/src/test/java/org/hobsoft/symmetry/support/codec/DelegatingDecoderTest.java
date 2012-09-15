/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/DelegatingDecoderTest.java $
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
 * Tests {@code DelegatingDecoder}.
 * 
 * @author Mark Hobson
 * @see DelegatingDecoder
 */
@RunWith(JMock.class)
public class DelegatingDecoderTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery context = new JUnit4Mockery();

	private Decoder<A, B> mockDecoder;

	private DelegatingDecoder<A, B> decoder;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		// TODO: can we avoid this cast warning without @SuppressWarnings
		@SuppressWarnings("unchecked")
		Decoder<A, B> uncheckedMockDecoder = context.mock(Decoder.class);
		
		mockDecoder = uncheckedMockDecoder;
		
		decoder = new DelegatingDecoder<A, B>(mockDecoder);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithNull()
	{
		new DelegatingDecoder<A, B>(null);
	}
	
	@Test
	public void decode() throws DecoderException
	{
		final A a = new A("a");
		
		context.checking(new Expectations() { {
			one(mockDecoder).decode(a);
		} });
		
		decoder.decode(a);
	}
	
	@Test
	public void getDecoder()
	{
		assertEquals(mockDecoder, decoder.getDecoder());
	}
}
