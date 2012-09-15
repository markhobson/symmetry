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
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;

import org.hobsoft.symmetry.support.bean.model.FooListener;
import org.hobsoft.symmetry.support.bean.model.TestBean;
import org.junit.Test;

import static org.hobsoft.symmetry.support.bean.BeanAssert.assertEventSetDescriptor;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code EventSets}.
 * 
 * @author Mark Hobson
 * @see EventSets
 */
public class EventSetsTest
{
	// tests ------------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullBeanClass()
	{
		EventSets.getDescriptor((Class<?>) null, null);
	}

	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullEvent()
	{
		EventSets.getDescriptor(TestBean.class, null);
	}

	@Test
	public void getDescriptorWithSimpleEvent() throws IntrospectionException
	{
		EventSetDescriptor actual = EventSets.getDescriptor(TestBean.class, TestBean.FOO_EVENT);
		
		EventSetDescriptor expected = new EventSetDescriptor(TestBean.class, "foo", FooListener.class,
			new String[] {"foo1", "foo2"}, "addFooListener", "removeFooListener", "getFooListeners");
		
		assertEventSetDescriptor(expected, actual);
	}
	
	@Test(expected = NoSuchEventSetException.class)
	public void getDescriptorWithInvalidEvent()
	{
		try
		{
			EventSets.getDescriptor(TestBean.class, "invalid");
		}
		catch (NoSuchEventSetException exception)
		{
			assertEquals("TestBean.invalid", exception.getMessage());
			throw exception;
		}
	}

	@Test(expected = NullPointerException.class)
	public void getDescriptorWithNullBeanInfo()
	{
		EventSets.getDescriptor((BeanInfo) null, null);
	}
	
	@Test
	public void getDescriptorWithListenerMethod() throws IntrospectionException
	{
		EventSetDescriptor actual = EventSets.getDescriptor(TestBean.class, TestBean.FOO_EVENT, "foo1");
		
		EventSetDescriptor expected = new EventSetDescriptor(TestBean.class, "foo", FooListener.class,
			new String[] {"foo1"}, "addFooListener", "removeFooListener", "getFooListeners");
		
		assertEventSetDescriptor(expected, actual);
	}
	
	@Test(expected = NoSuchMethodException.class)
	public void getDescriptorWithInvalidListenerMethod()
	{
		try
		{
			EventSets.getDescriptor(TestBean.class, TestBean.FOO_EVENT, "foo3");
		}
		catch (NoSuchMethodException exception)
		{
			assertEquals("foo.foo3", exception.getMessage());
			throw exception;
		}
	}
}
