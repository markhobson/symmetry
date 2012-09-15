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
package org.hobsoft.symmetry.ui.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class Utilities
{
	// TODO: can we achieve these with Guava?
	
	// constructors -----------------------------------------------------------
	
	private Utilities()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void setSize(List<?> list, int size)
	{
		if (size < list.size())
		{
			crop(list, size);
		}
		else if (size > list.size())
		{
			grow(list, size);
		}
	}

	public static void grow(Collection<?> collection, int size)
	{
		growCapture(collection, size);
	}
	
	public static void crop(List<?> list, int size)
	{
		// TODO: there must be a more efficient way of doing this, e.g. using subList?
		
		int n = list.size() - size;
		
		while (n > 0)
		{
			list.remove(list.size() - 1);
			n--;
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static <E> void growCapture(Collection<E> collection, int size)
	{
		int n = size - collection.size();
		
		if (n > 0)
		{
			collection.addAll(Collections.<E>nCopies(n, null));
		}
	}
}
