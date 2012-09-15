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
package org.hobsoft.symmetry.support.test;

import java.lang.reflect.Field;

import org.junit.Assert;

/**
 * Provides access to private members in classes.
 * 
 * @author Mark Hobson
 * @see <a href="http://junit.sourceforge.net/doc/faq/faq.htm#tests_11">JUnit FAQ</a>
 * @see <a href="http://www.onjava.com/pub/a/onjava/2003/11/12/reflection.html">PrivilegedAccessor</a>
 */
public final class PrivateAccessor
{
	// constructors -----------------------------------------------------------
	
	private PrivateAccessor()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static Object getPrivateField(Object object, String fieldName)
	{
		// check we have valid arguments
		Assert.assertNotNull(object);
		Assert.assertNotNull(fieldName);
		
		// go and find the private field...
		for (Field field : object.getClass().getDeclaredFields())
		{
			if (fieldName.equals(field.getName()))
			{
				try
				{
					field.setAccessible(true);
					
					return field.get(object);
				}
				catch (IllegalAccessException exception)
				{
					Assert.fail("IllegalAccessException accessing field: " + fieldName);
				}
			}
		}
		
		Assert.fail("Field not found: " + fieldName);
		
		return null;
	}
}
