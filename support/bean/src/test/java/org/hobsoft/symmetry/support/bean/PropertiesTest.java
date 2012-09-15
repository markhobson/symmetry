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
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.model.TestBean;
import org.junit.Test;

import static org.hobsoft.symmetry.support.bean.BeanAssert.assertPropertyDescriptor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Tests {@code Properties}.
 * 
 * @author Mark Hobson
 * @see Properties
 */
public class PropertiesTest
{
	// tests ------------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullBeanClass()
	{
		Properties.getDescriptor((Class<?>) null, null);
	}

	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullProperty()
	{
		Properties.getDescriptor(TestBean.class, null);
	}

	@Test
	public void getDescriptorWithSimpleProperty() throws IntrospectionException
	{
		PropertyDescriptor actual = Properties.getDescriptor(TestBean.class, TestBean.FOO_PROPERTY);
		
		PropertyDescriptor expected = new PropertyDescriptor("foo", TestBean.class, "getFoo", "setFoo");
		
		assertPropertyDescriptor(expected, actual);
	}
	
	@Test
	public void getDescriptorWithReadOnlyProperty() throws IntrospectionException
	{
		PropertyDescriptor actual = Properties.getDescriptor(TestBean.class, TestBean.FOO_READ_ONLY_PROPERTY);
		
		PropertyDescriptor expected = new PropertyDescriptor("fooReadOnly", TestBean.class, "getFooReadOnly", null);
		
		assertPropertyDescriptor(expected, actual);
	}
	
	@Test
	public void getDescriptorWithWriteOnlyProperty() throws IntrospectionException
	{
		PropertyDescriptor actual = Properties.getDescriptor(TestBean.class, TestBean.FOO_WRITE_ONLY_PROPERTY);
		
		PropertyDescriptor expected = new PropertyDescriptor("fooWriteOnly", TestBean.class, null, "setFooWriteOnly");
		
		assertPropertyDescriptor(expected, actual);
	}
	
	@Test(expected = NoSuchPropertyException.class)
	public void getDescriptorWithInvalidProperty()
	{
		try
		{
			Properties.getDescriptor(TestBean.class, "invalid");
		}
		catch (NoSuchPropertyException exception)
		{
			assertEquals("TestBean.invalid", exception.getMessage());
			throw exception;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullBeanInfo()
	{
		Properties.getDescriptor((BeanInfo) null, null);
	}
	
	@Test
	public void copyDescriptor() throws IntrospectionException
	{
		PropertyDescriptor descriptor = new PropertyDescriptor("foo", TestBean.class, "getFoo", "setFoo");
		
		PropertyDescriptor actual = Properties.copyDescriptor(descriptor);
		
		assertNotSame(descriptor, actual);
		assertPropertyDescriptor(descriptor, actual);
	}
}
