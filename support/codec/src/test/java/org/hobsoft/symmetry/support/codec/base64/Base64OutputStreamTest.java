/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/base64/Base64OutputStreamTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>Base64OutputStream</code>.
 * 
 * @author	Mark Hobson
 * @version	$Id: Base64OutputStreamTest.java 75358 2010-07-23 11:34:52Z mark@IIZUKA.CO.UK $
 * @see		Base64OutputStream
 */
public class Base64OutputStreamTest
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream byteOut;
	
	private Base64OutputStream out;

	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		byteOut = new ByteArrayOutputStream();
		
		out = new Base64OutputStream(byteOut);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void writeBytes() throws IOException
	{
		out.write("Hello World".getBytes());
		out.close();
		
		assertOut("SGVsbG8gV29ybGQ=");
	}
	
	// private methods --------------------------------------------------------
	
	private void assertOut(String expected)
	{
		String actual = new String(byteOut.toByteArray());
		
		assertEquals("Output", expected, actual);
	}
}
