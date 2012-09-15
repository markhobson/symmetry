/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/security/CipherCodecTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.security;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests <code>CipherCodec</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: CipherCodecTest.java 75371 2010-07-26 10:24:46Z mark@IIZUKA.CO.UK $
 * @see		CipherCodec
 */
public class CipherCodecTest
{
	// constants --------------------------------------------------------------
	
	private static final String CIPHER_TRANSFORMATION = "DES/ECB/PKCS5Padding";
	
	private static final String KEY_GENERATOR_ALGORITHM = "DES";
	
	private static final byte[] KEY = new byte[] {
		-65, 26, -33, -63, -85, 117, -75, -36
	};
	
	private static final byte[] PLAINTEXT = new byte[] {
		72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100
	};
	
	private static final byte[] CIPHERTEXT = new byte[] {
		-76, 14, -53, -28, -94, -103, -58, 22, -25, -111, -73, -112, 73, 66, -4, -30,
	};
	
	// fields -----------------------------------------------------------------
	
	private Cipher cipher;
	
	private Key key;
	
	private CipherCodec codec;

	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp() throws GeneralSecurityException
	{
		cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
		
		key = SecretKeyFactory.getInstance(KEY_GENERATOR_ALGORITHM).generateSecret(new DESKeySpec(KEY));
		
		codec = new CipherCodec(cipher, key);
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithCipherNull()
	{
		try
		{
			new CipherCodec(null, key);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("cipher cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructWithKeyNull()
	{
		try
		{
			new CipherCodec(cipher, null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("key cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
	
	@Test
	public void encode() throws EncoderException
	{
		byte[] actual = codec.encode(PLAINTEXT);
		
		assertTrue("Ciphertext", Arrays.equals(CIPHERTEXT, actual));
	}
	
	@Test
	public void encodeWithNull() throws EncoderException
	{
		assertNull(codec.encode(null));
	}
	
	@Test
	public void decode() throws DecoderException
	{
		byte[] actual = codec.decode(CIPHERTEXT);
		
		assertTrue("Plaintext", Arrays.equals(PLAINTEXT, actual));
	}

	@Test
	public void decodeWithNull() throws DecoderException
	{
		assertNull(codec.decode(null));
	}
}
