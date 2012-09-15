/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/EventSetsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import static org.hobsoft.symmetry.support.bean.BeanAssert.assertEventSetDescriptor;
import static org.junit.Assert.assertEquals;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;

import org.hobsoft.symmetry.support.bean.model.FooListener;
import org.hobsoft.symmetry.support.bean.model.TestBean;
import org.junit.Test;

/**
 * Tests {@code EventSets}.
 * 
 * @author Mark Hobson
 * @version $Id: EventSetsTest.java 97397 2011-12-30 11:53:48Z mark@IIZUKA.CO.UK $
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
