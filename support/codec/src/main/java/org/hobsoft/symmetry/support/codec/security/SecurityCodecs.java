/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/security/SecurityCodecs.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.security;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;

import org.hobsoft.symmetry.support.codec.Codec;

/**
 * Factory class for building security codecs.
 * 
 * @author Mark Hobson
 * @version $Id: SecurityCodecs.java 75578 2010-08-02 18:30:22Z mark@IIZUKA.CO.UK $
 */
public final class SecurityCodecs
{
	// constructors -----------------------------------------------------------

	private SecurityCodecs()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Codec<byte[], byte[]> cipher(Cipher cipher, Key key)
	{
		return new CipherCodec(cipher, key);
	}
	
	public static Codec<byte[], byte[]> messageDigest(MessageDigest digest)
	{
		return new MessageDigestCodec(digest);
	}
}
