/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/base64/Base64UtilsTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests <code>Base64Utils</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64UtilsTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		Base64Utils
 */
public class Base64UtilsTest
{
	// constants --------------------------------------------------------------
	
	private static final byte[] PLAINTEXT = new byte[] {
		72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100
	};
	
	private static final byte[] CIPHERTEXT = new byte[] {
		83, 71, 86, 115, 98, 71, 56, 103, 86, 50, 57, 121, 98, 71, 81, 61
	};

	// tests ------------------------------------------------------------------
	
	@Test
	public void encode() throws IOException
	{
		byte[] actual = Base64Utils.encode(PLAINTEXT);
		
		assertTrue("Ciphertext", Arrays.equals(CIPHERTEXT, actual));
	}
	
	@Test
	public void decode() throws IOException
	{
		byte[] actual = Base64Utils.decode(CIPHERTEXT);
		
		assertTrue("Plaintext", Arrays.equals(PLAINTEXT, actual));
	}
}
