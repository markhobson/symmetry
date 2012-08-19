/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/base64/Base64Utils.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utilities for working with Base64.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64Utils.java 75372 2010-07-26 10:34:40Z mark@IIZUKA.CO.UK $
 */
public final class Base64Utils
{
	// constants --------------------------------------------------------------
	
	/**
	 * The size of the buffer to use when copying streams.
	 */
	private static final int BUFFER_SIZE = 1024 * 4;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Private constructor for utility class.
	 */
	private Base64Utils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Encodes the specified bytes as Base64 using the default alphabet, padding to the 24-bit boundary.
	 * 
	 * @param	bytes
	 * 				the bytes to encode
	 * @return	the Base64 encoded bytes
	 * @throws	IOException
	 * 				if an error occurs encoding
	 */
	public static byte[] encode(byte[] bytes) throws IOException
	{
		return encode(bytes, Base64Alphabet.DEFAULT);
	}
	
	/**
	 * Encodes the specified bytes as Base64 using the specified alphabet, padding to the 24-bit boundary.
	 * 
	 * @param	bytes
	 * 				the bytes to encode
	 * @param	alphabet
	 * 				the Base64 alphabet to use when encoding bytes
	 * @return	the Base64 encoded bytes
	 * @throws	IOException
	 * 				if an error occurs encoding
	 */
	public static byte[] encode(byte[] bytes, Base64Alphabet alphabet) throws IOException
	{
		return encode(bytes, alphabet, true);
	}
	
	/**
	 * Encodes the specified bytes as Base64 using the specified alphabet, optionally padding to the 24-bit boundary.
	 * 
	 * @param	bytes
	 *				the bytes to encode
	 * @param	alphabet
	 * 				the Base64 alphabet to use when encoding bytes
	 * @param	pad
	 *				whether to pad to the 24-bit boundary with the pad character
	 * @return	the Base64 encoded bytes
	 * @throws	IOException
	 *				if an error occurs encoding
	 */
	public static byte[] encode(byte[] bytes, Base64Alphabet alphabet, boolean pad) throws IOException
	{
		// TODO: can we not throw IOException?
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Base64OutputStream out = new Base64OutputStream(byteOut, alphabet, pad);
		
		out.write(bytes);
		out.close();
		
		return byteOut.toByteArray();
	}
	
	/**
	 * Decodes the specified bytes as Base64 using the default alphabet.
	 * 
	 * @param	bytes
	 * 				the bytes to decode
	 * @return	the Base64 decoded bytes
	 * @throws	IOException
	 * 				if an error occurs decoding
	 */
	public static byte[] decode(byte[] bytes) throws IOException
	{
		return decode(bytes, Base64Alphabet.DEFAULT);
	}
	
	/**
	 * Decodes the specified bytes as Base64 using the specified alphabet.
	 * 
	 * @param	bytes
	 * 				the bytes to decode
	 * @param	alphabet
	 *				the Base64 alphabet to use when decoding bytes
	 * @return	the Base64 decoded bytes
	 * @throws	IOException
	 * 				if an error occurs decoding
	 */
	public static byte[] decode(byte[] bytes, Base64Alphabet alphabet) throws IOException
	{
		// TODO: can we not throw IOException?
		
		ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
		Base64InputStream in = new Base64InputStream(byteIn, alphabet);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		copy(in, out);

		in.close();
		out.close();
		
		return out.toByteArray();
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Copies the bytes from the specified input stream to the specified output stream.
	 * 
	 * @param	in
	 * 				the input stream to read from
	 * @param	out
	 * 				the output stream to write to
	 * @throws	IOException
	 * 				if an I/O error occurs
	 */
	private static void copy(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[BUFFER_SIZE];
		int n;
		
		while ((n = in.read(buffer, 0, buffer.length)) != -1)
		{
			out.write(buffer, 0, n);
		}
	}
}
