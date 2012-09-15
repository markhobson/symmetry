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
package org.hobsoft.symmetry.ui.common;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

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
	
	public static Object merge(Class<?> componentType, Object... arrays)
	{
		return merge(componentType, Arrays.asList(arrays));
	}
	
	public static Object merge(Class<?> componentType, Collection<Object> arrays)
	{
		int totalLength = getTotalLength(arrays);
		
		Object target = Array.newInstance(componentType, totalLength);
		int index = 0;
		
		for (Object array : arrays)
		{
			if (array != null)
			{
				int length = Array.getLength(array);
				System.arraycopy(array, 0, target, index, length);
				
				index += length;
			}
		}
		
		return target;
	}
	
	// private methods --------------------------------------------------------
	
	private static int getTotalLength(Collection<Object> arrays)
	{
		int length = 0;
		
		for (Object array : arrays)
		{
			if (array != null)
			{
				length += Array.getLength(array);
			}
		}
		
		return length;
	}
}