/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/CodecsTest.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests {@code Codecs}.
 * 
 * @author Mark Hobson
 * @see Codecs
 */
public class CodecsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void nullCodec()
	{
		assertEquals(NullCodec.get(), Codecs.nullCodec());
	}
	
	@Test
	public void identity()
	{
		assertEquals(IdentityCodec.get(), Codecs.identity());
	}
	
	// TODO: others
}
