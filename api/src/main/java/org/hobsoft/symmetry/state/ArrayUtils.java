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
package org.hobsoft.symmetry.state;

import java.util.Arrays;

/**
 * 
 * 
 * @author Mark Hobson
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
