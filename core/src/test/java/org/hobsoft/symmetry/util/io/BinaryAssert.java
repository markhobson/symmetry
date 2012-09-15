/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/BinaryAssert.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.util.Arrays;

import junit.framework.Assert;

/**
 * Provides assertion methods for binary data.
 * 
 * @author Mark Hobson
 */
public final class BinaryAssert
{
	// constructors -----------------------------------------------------------
	
	private BinaryAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void assertBytes(int[] expected, byte[] actual)
	{
		byte[] bytes = new byte[expected.length];
		
		for (int i = 0; i < expected.length; i++)
		{
			bytes[i] = (byte) expected[i];
		}
		
		if (!Arrays.equals(bytes, actual))
		{
			Assert.fail("expected:<" + toBinaryString(bytes) + "> but was:<" + toBinaryString(actual) + ">");
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static String toBinaryString(byte[] bytes)
	{
		StringBuilder builder = new StringBuilder();
		
		for (byte b : bytes)
		{
			if (builder.length() > 0)
			{
				builder.append(" ");
			}
			
			builder.append(toBinaryString(b));
		}
		
		return builder.toString();
	}
	
	private static String toBinaryString(byte b)
	{
		char[] chars = new char[8];
		
		for (int i = 0; i < 8; i++)
		{
			int bit = b & (1 << (7 - i));
			
			chars[i] = (bit == 0) ? '0' : '1';
		}
		
		return new String(chars);
	}
}
