/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/ArrayUtils.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.util.Arrays;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ArrayUtils.java 98228 2012-02-02 11:29:46Z mark@IIZUKA.CO.UK $
 */
final class ArrayUtils
{
	// constructors -----------------------------------------------------------
	
	private ArrayUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static int hashCode(Object array)
	{
		int hashCode;
		
		if (array instanceof byte[])
		{
			hashCode = Arrays.hashCode((byte[]) array);
		}
		else if (array instanceof short[])
		{
			hashCode = Arrays.hashCode((short[]) array);
		}
		else if (array instanceof int[])
		{
			hashCode = Arrays.hashCode((int[]) array);
		}
		else if (array instanceof long[])
		{
			hashCode = Arrays.hashCode((long[]) array);
		}
		else if (array instanceof char[])
		{
			hashCode = Arrays.hashCode((char[]) array);
		}
		else if (array instanceof float[])
		{
			hashCode = Arrays.hashCode((float[]) array);
		}
		else if (array instanceof double[])
		{
			hashCode = Arrays.hashCode((double[]) array);
		}
		else if (array instanceof boolean[])
		{
			hashCode = Arrays.hashCode((boolean[]) array);
		}
		else if (array instanceof Object[])
		{
			hashCode = Arrays.hashCode((Object[]) array);
		}
		else
		{
			throw new IllegalArgumentException("array must be an array: " + array);
		}
		
		return hashCode;
	}
	
	public static boolean equals(Object array1, Object array2)
	{
		if (!array1.getClass().equals(array2.getClass()))
		{
			throw new IllegalArgumentException("array1 and array2 must be the same type: " + array1 + " and " + array2);
		}
		
		boolean equals;
		
		if (array1 instanceof byte[])
		{
			equals = Arrays.equals((byte[]) array1, (byte[]) array2);
		}
		else if (array1 instanceof short[])
		{
			equals = Arrays.equals((short[]) array1, (short[]) array2);
		}
		else if (array1 instanceof int[])
		{
			equals = Arrays.equals((int[]) array1, (int[]) array2);
		}
		else if (array1 instanceof long[])
		{
			equals = Arrays.equals((long[]) array1, (long[]) array2);
		}
		else if (array1 instanceof char[])
		{
			equals = Arrays.equals((char[]) array1, (char[]) array2);
		}
		else if (array1 instanceof float[])
		{
			equals = Arrays.equals((float[]) array1, (float[]) array2);
		}
		else if (array1 instanceof double[])
		{
			equals = Arrays.equals((double[]) array1, (double[]) array2);
		}
		else if (array1 instanceof boolean[])
		{
			equals = Arrays.equals((boolean[]) array1, (boolean[]) array2);
		}
		else if (array1 instanceof Object[])
		{
			equals = Arrays.equals((Object[]) array1, (Object[]) array2);
		}
		else
		{
			throw new IllegalArgumentException("array1 must be an array: " + array1);
		}
		
		return equals;
	}
}
