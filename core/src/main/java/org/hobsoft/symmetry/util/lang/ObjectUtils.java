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

import java.lang.reflect.Array;
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
