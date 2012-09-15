/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/DefaultBeanInfoTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.model.Bean;
import org.hobsoft.symmetry.support.bean.model.TestBean;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.support.bean.BeanAssert.assertBeanDescriptor;
import static org.hobsoft.symmetry.support.bean.BeanAssert.assertPropertyDescriptors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code DefaultBeanInfo}.
 * 
 * @author Mark Hobson
 * @see DefaultBeanInfo
 */
public class DefaultBeanInfoTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultBeanInfo beanInfo;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		beanInfo = new DefaultBeanInfo(Bean.class);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newDefaultBeanInfo()
	{
		assertBeanDescriptor("beanDescriptor", new BeanDescriptor(Bean.class), beanInfo.getBeanDescriptor());
		assertNull("eventSetDescriptors", beanInfo.getEventSetDescriptors());
		assertEquals("defaultEventIndex", -1, beanInfo.getDefaultEventIndex());
		assertNull("propertyDescriptors", beanInfo.getPropertyDescriptors());
		assertEquals("defaultPropertyIndex", -1, beanInfo.getDefaultPropertyIndex());
		assertNull("methodDescriptors", beanInfo.getMethodDescriptors());
		assertNull("additionalBeanInfo", beanInfo.getAdditionalBeanInfo());
	}
	
	@Test
	public void addPropertyDescriptor() throws IntrospectionException
	{
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor("foo", Bean.class);
		beanInfo.addPropertyDescriptor(propertyDescriptor);
		
		PropertyDescriptor[] expected = new PropertyDescriptor[] {propertyDescriptor};
		assertPropertyDescriptors(expected, beanInfo.getPropertyDescriptors());
	}
	
	@Test
	public void addPropertyDescriptorPropertyName() throws IntrospectionException
	{
		beanInfo.addPropertyDescriptor("foo");
		
		PropertyDescriptor[] expected = new PropertyDescriptor[] {new PropertyDescriptor("foo", Bean.class)};
		assertPropertyDescriptors(expected, beanInfo.getPropertyDescriptors());
	}
	
	@Test
	public void addPropertyDescriptorPropertyNameAndDisplayName() throws IntrospectionException
	{
		beanInfo.addPropertyDescriptor("foo", "Foo");
		
		PropertyDescriptor expectedPropertyDescriptor = new PropertyDescriptor("foo", Bean.class);
		expectedPropertyDescriptor.setDisplayName("Foo");
		PropertyDescriptor[] expected = new PropertyDescriptor[] {expectedPropertyDescriptor};
		assertPropertyDescriptors(expected, beanInfo.getPropertyDescriptors());
	}
	
	@Test
	public void addPropertyDescriptorPropertyNameAndDisplayNameReadOnly() throws IntrospectionException
	{
		beanInfo = new DefaultBeanInfo(TestBean.class);
		beanInfo.addPropertyDescriptor("fooReadOnly", "FooReadOnly");
		
		PropertyDescriptor expectedPropertyDescriptor = new PropertyDescriptor("fooReadOnly", TestBean.class,
			"getFooReadOnly", null);
		expectedPropertyDescriptor.setDisplayName("FooReadOnly");
		PropertyDescriptor[] expected = new PropertyDescriptor[] {expectedPropertyDescriptor};
		assertPropertyDescriptors(expected, beanInfo.getPropertyDescriptors());
	}
	
	@Test
	public void addPropertyDescriptorPropertyNameAndDisplayNameWriteOnly() throws IntrospectionException
	{
		beanInfo = new DefaultBeanInfo(TestBean.class);
		beanInfo.addPropertyDescriptor("fooWriteOnly", "FooWriteOnly");
		
		PropertyDescriptor expectedPropertyDescriptor = new PropertyDescriptor("fooWriteOnly", TestBean.class, null,
			"setFooWriteOnly");
		expectedPropertyDescriptor.setDisplayName("FooWriteOnly");
		PropertyDescriptor[] expected = new PropertyDescriptor[] {expectedPropertyDescriptor};
		assertPropertyDescriptors(expected, beanInfo.getPropertyDescriptors());
	}
}
