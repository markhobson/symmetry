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

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests {@code ArrayUtils}.
 * 
 * @author Mark Hobson
 * @see ArrayUtils
 */
public class ArrayUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void mergeWithReferenceArrays()
	{
		Integer[] actual = (Integer[]) ArrayUtils.merge(Integer.class, new Integer[] {1, 2}, new Integer[] {3, 4});
		
		assertArrayEquals(new Integer[] {1, 2, 3, 4}, actual);
	}
	
	@Test
	public void mergeWithPrimitiveArrays()
	{
		int[] actual = (int[]) ArrayUtils.merge(int.class, new int[] {1, 2}, new int[] {3, 4});
		
		assertArrayEquals(new int[] {1, 2, 3, 4}, actual);
	}
	
	@Test
	public void mergeWithNullArray()
	{
		int[] actual = (int[]) ArrayUtils.merge(int.class, (Object) null);
		
		assertArrayEquals(new int[0], actual);
	}
}
