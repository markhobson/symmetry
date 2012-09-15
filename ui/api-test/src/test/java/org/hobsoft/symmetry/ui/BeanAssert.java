/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/BeanAssert.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.Properties;

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
