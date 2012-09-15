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
package org.hobsoft.symmetry.ui.internal;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Preconditions
{
	// TODO: can we achieve these with Guava?
	
	// constructors -----------------------------------------------------------
	
	private Preconditions()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static int checkNonNegative(int value, String name)
	{
		checkArgument(value >= 0, "%s must be non-negative: %s", name, value);
		
		return value;
	}

	public static int checkPositive(int value, String name)
	{
		checkArgument(value > 0, "%s must be positive: %s", name, value);
		
		return value;
	}

	public static void checkElementIndexes(int[] indexes, int size, String name)
	{
		checkNonNegative(size, "size");
		
		for (int index : indexes)
		{
			if (index < 0)
			{
				throw new IndexOutOfBoundsException(String.format("%s must not be negative: %s", name,
					Arrays.toString(indexes)));
			}
			
			if (index >= size)
			{
				throw new IndexOutOfBoundsException(String.format("%s must be less than %d: %s", name, size,
					Arrays.toString(indexes)));
			}
		}
	}
}
