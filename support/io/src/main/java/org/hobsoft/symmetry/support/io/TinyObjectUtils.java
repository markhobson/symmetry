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
package org.hobsoft.symmetry.support.io;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class TinyObjectUtils
{
	// types ------------------------------------------------------------------
	
	private static class FieldComparator implements Comparator<Field>
	{
		// constants --------------------------------------------------------------
		
		public static final FieldComparator INSTANCE = new FieldComparator();
		
		// Comparator methods -----------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Field field1, Field field2)
		{
			return field1.getName().compareTo(field2.getName());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private TinyObjectUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static List<Field> getOrderedDeclaredFields(Class<?> klass)
	{
		List<Field> fields = new ArrayList<Field>();
		
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		
		Collections.sort(fields, FieldComparator.INSTANCE);
		
		return fields;
	}
}
