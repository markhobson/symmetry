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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ObjectUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void nonInstantiable()
	{
		Constructor<?>[] constructors = ObjectUtils.class.getConstructors();
		
		for (int i = 0; i < constructors.length; i++)
		{
			assertTrue(Modifier.isPrivate(constructors[i].getModifiers()));
		}
	}
	
	@Test
	public void equalsOrNullBothNull()
	{
		assertTrue(ObjectUtils.equals(null, null));
	}
	
	@Test
	public void equalsOrNullFirstNull()
	{
		assertFalse(ObjectUtils.equals(null, new Object()));
	}
	
	@Test
	public void equalsOrNullSecondNull()
	{
		assertFalse(ObjectUtils.equals(new Object(), null));
	}
	
	@Test
	public void equalsOrNullSame()
	{
		Object object = new Object();
		assertTrue(ObjectUtils.equals(object, object));
	}
	
	@Test
	public void equalsOrNullEqual()
	{
		assertTrue(ObjectUtils.equals(123, 123));
	}
}