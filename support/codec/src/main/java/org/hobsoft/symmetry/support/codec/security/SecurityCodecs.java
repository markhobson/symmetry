/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
