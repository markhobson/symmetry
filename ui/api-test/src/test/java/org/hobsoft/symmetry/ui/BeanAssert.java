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
package org.hobsoft.symmetry.ui;

import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BeanAssert
{
	// constructors -----------------------------------------------------------
	
	private BeanAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void assertProperty(Class<?> actualBeanClass, String actualPropertyName,
		Class<?> expectedPropertyType)
	{
		assertProperty(Properties.getDescriptor(actualBeanClass, actualPropertyName), expectedPropertyType);
	}
	
	public static void assertProperty(PropertyDescriptor actual, Class<?> expectedPropertyType)
	{
		assertNotNull("Bean property descriptor is null", actual);
		assertEquals("propertyType", expectedPropertyType, actual.getPropertyType());
	}
	
	public static void assertIndexedProperty(Class<?> actualBeanClass, String actualPropertyName)
	{
		assertIndexedProperty(Properties.getDescriptor(actualBeanClass, actualPropertyName));
	}
	
	public static void assertIndexedProperty(PropertyDescriptor actual)
	{
		assertNotNull("Bean property descriptor is null", actual);
		assertTrue("Bean property is not indexed: " + actual.getDisplayName(),
			actual instanceof IndexedPropertyDescriptor);
	}
	
	public static void assertReadableProperty(Class<?> actualBeanClass, String actualPropertyName)
	{
		assertReadableProperty(Properties.getDescriptor(actualBeanClass, actualPropertyName));
	}
	
	public static void assertReadableProperty(PropertyDescriptor actual)
	{
		assertNotNull("Bean property descriptor is null", actual);
		assertNotNull("Bean property is not readable: " + actual.getDisplayName(), actual.getReadMethod());
	}
	
	public static void assertWritableProperty(Class<?> actualBeanClass, String actualPropertyName)
	{
		assertWritableProperty(Properties.getDescriptor(actualBeanClass, actualPropertyName));
	}
	
	public static void assertWritableProperty(PropertyDescriptor actual)
	{
		assertNotNull("Bean property descriptor is null", actual);
		assertNotNull("Bean property is not writable: " + actual.getDisplayName(), actual.getWriteMethod());
	}
}
