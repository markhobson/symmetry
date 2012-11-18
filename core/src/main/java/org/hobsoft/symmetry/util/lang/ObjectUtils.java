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
package org.hobsoft.symmetry.util.lang;

import java.util.Arrays;

/**
 * 
 * 
 * @author Mark Hobson
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
		
		if (a.getClass().isArray() && b.getClass().isArray())
		{
			return arraysEquals(a, b);
		}
		
		return a.equals(b);
	}
	
	public static String toString(Object object)
	{
		if (object == null)
		{
			return "null";
		}
		
		if (object.getClass().isArray())
		{
			return arrayToString(object);
		}
		
		return object.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean arraysEquals(Object a, Object b)
	{
		Class<?> aType = a.getClass().getComponentType();
		Class<?> bType = b.getClass().getComponentType();

		boolean equals;
		
		if (aType != bType)
		{
			equals = false;
		}
		else if (aType == boolean.class)
		{
			equals = Arrays.equals((boolean[]) a, (boolean[]) b);
		}
		else if (aType == byte.class)
		{
			equals = Arrays.equals((byte[]) a, (byte[]) b);
		}
		else if (aType == char.class)
		{
			equals = Arrays.equals((char[]) a, (char[]) b);
		}
		else if (aType == double.class)
		{
			equals = Arrays.equals((double[]) a, (double[]) b);
		}
		else if (aType == float.class)
		{
			equals = Arrays.equals((float[]) a, (float[]) b);
		}
		else if (aType == int.class)
		{
			equals = Arrays.equals((int[]) a, (int[]) b);
		}
		else if (aType == long.class)
		{
			equals = Arrays.equals((long[]) a, (long[]) b);
		}
		else if (aType == short.class)
		{
			equals = Arrays.equals((short[]) a, (short[]) b);
		}
		else
		{
			equals = Arrays.equals((Object[]) a, (Object[]) b);
		}
		
		return equals;
	}
	
	private static String arrayToString(Object array)
	{
		Class<?> type = array.getClass().getComponentType();
		
		String string;
		
		if (type == boolean.class)
		{
			string = Arrays.toString((boolean[]) array);
		}
		else if (type == byte.class)
		{
			string = Arrays.toString((byte[]) array);
		}
		else if (type == char.class)
		{
			string = Arrays.toString((char[]) array);
		}
		else if (type == double.class)
		{
			string = Arrays.toString((double[]) array);
		}
		else if (type == float.class)
		{
			string = Arrays.toString((float[]) array);
		}
		else if (type == int.class)
		{
			string = Arrays.toString((int[]) array);
		}
		else if (type == long.class)
		{
			string = Arrays.toString((long[]) array);
		}
		else if (type == short.class)
		{
			string = Arrays.toString((short[]) array);
		}
		else
		{
			string = Arrays.toString((Object[]) array);
		}
		
		return string;
	}
}
