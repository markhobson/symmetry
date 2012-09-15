/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/lang/ObjectUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.lang;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ObjectUtils.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public final class ObjectUtils
{
	// constructors -----------------------------------------------------------
	
	private ObjectUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static boolean equals(Object a, Object b)
	{
		if (a == null || b == null)
		{
			return (a == b);
		}
		
		Class<?> aClass = a.getClass();
		Class<?> bClass = b.getClass();
		if (aClass.isArray() && bClass.isArray())
		{
			Class<?> aType = aClass.getComponentType();
			Class<?> bType = bClass.getComponentType();
			
			if (aType != bType)
			{
				return false;
			}
			
			if (aType == boolean.class)
			{
				return Arrays.equals((boolean[]) a, (boolean[]) b);
			}
			
			if (aType == byte.class)
			{
				return Arrays.equals((byte[]) a, (byte[]) b);
			}
			
			if (aType == char.class)
			{
				return Arrays.equals((char[]) a, (char[]) b);
			}
			
			if (aType == double.class)
			{
				return Arrays.equals((double[]) a, (double[]) b);
			}
			
			if (aType == float.class)
			{
				return Arrays.equals((float[]) a, (float[]) b);
			}
			
			if (aType == int.class)
			{
				return Arrays.equals((int[]) a, (int[]) b);
			}
			
			if (aType == long.class)
			{
				return Arrays.equals((long[]) a, (long[]) b);
			}
			
			if (aType == double.class)
			{
				return Arrays.equals((short[]) a, (short[]) b);
			}
			
			return Arrays.equals((Object[]) a, (Object[]) b);
		}
		
		return a.equals(b);
	}
	
	public static String toString(Object object)
	{
		if (object == null)
		{
			return "null";
		}
		
		Class<?> klass = object.getClass();
		
		if (!klass.isArray())
		{
			return object.toString();
		}
		
		int length = Array.getLength(object);
		
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (int i = 0; i < length; i++)
		{
			if (i > 0)
			{
				builder.append(',');
			}
			
			builder.append(Array.get(object, i));
		}
		builder.append(']');
		return builder.toString();
	}
}
