/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/base64/Base64InputStreamTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * Tests <code>Base64InputStream</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64InputStreamTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		Base64InputStream
 */
public class Base64InputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private Base64InputStream in;

	// tests ------------------------------------------------------------------
	
	@Test
	public void writeBytes() throws IOException
	{
		in = createBase64InputStream("SGVsbG8gV29ybGQ=");
		
		assertEquals("Hello World", toString(in));
	}
	
	// private methods --------------------------------------------------------
	
	private Base64InputStream createBase64InputStream(String string)
	{
		return new Base64InputStream(new ByteArrayInputStream(string.getBytes()));
	}
	
	private String toString(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		int b;
		while ((b = in.read()) != -1)
		{
			out.write(b);
		}
		
		out.close();
		
		return out.toString();
	}
}
