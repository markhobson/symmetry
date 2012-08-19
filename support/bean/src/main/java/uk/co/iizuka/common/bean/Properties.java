/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/Properties.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import static uk.co.iizuka.common.bean.Utilities.checkNotNull;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Properties.java 98170 2012-01-30 19:45:16Z mark@IIZUKA.CO.UK $
 */
public final class Properties
{
	// TODO: add indexed property support
	
	// constructors -----------------------------------------------------------
	
	private Properties()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static PropertyDescriptor getDescriptor(Object bean, String propertyName)
	{
		return getDescriptor(bean.getClass(), propertyName);
	}
	
	public static PropertyDescriptor getDescriptor(Class<?> beanClass, String propertyName)
	{
		return getDescriptor(BeanUtils.getBeanInfo(beanClass), propertyName);
	}
	
	public static PropertyDescriptor getDescriptor(BeanInfo beanInfo, String propertyName)
	{
		checkNotNull(beanInfo, "beanInfo");
		checkNotNull(propertyName, "propertyName");
		
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors())
		{
			if (propertyName.equals(descriptor.getName()))
			{
				return descriptor;
			}
		}
		
		throw new NoSuchPropertyException(beanInfo, propertyName);
	}
	
	public static IndexedPropertyDescriptor getIndexedDescriptor(Object bean, String propertyName)
	{
		return getIndexedDescriptor(bean.getClass(), propertyName);
	}
	
	public static IndexedPropertyDescriptor getIndexedDescriptor(Class<?> beanClass, String propertyName)
	{
		return getIndexedDescriptor(BeanUtils.getBeanInfo(beanClass), propertyName);
	}
	
	public static IndexedPropertyDescriptor getIndexedDescriptor(BeanInfo beanInfo, String propertyName)
	{
		PropertyDescriptor descriptor = getDescriptor(beanInfo, propertyName);
		
		if (!(descriptor instanceof IndexedPropertyDescriptor))
		{
			// TODO: throw better exception
			throw new IllegalArgumentException(propertyName + " is not an indexed property");
		}
		
		return (IndexedPropertyDescriptor) descriptor;
	}
	
	public static PropertyDescriptor copyDescriptor(PropertyDescriptor propertyDescriptor)
	{
		try
		{
			return new PropertyDescriptor(propertyDescriptor.getName(), propertyDescriptor.getReadMethod(),
				propertyDescriptor.getWriteMethod());
		}
		catch (IntrospectionException exception)
		{
			throw new BeanException("Cannot copy property descriptor: " + propertyDescriptor, exception);
		}
	}
	
	public static Object get(Object bean, String propertyName)
	{
		return get(bean, getDescriptor(bean.getClass(), propertyName));
	}
	
	public static Object get(Object bean, PropertyDescriptor propertyDescriptor)
	{
		try
		{
			Method method = propertyDescriptor.getReadMethod();
			
			if (method == null)
			{
				throw new BeanException("Cannot read bean property: " + propertyDescriptor.getName());
			}
			
			method.setAccessible(true);
			
			return method.invoke(bean, (Object[]) null);
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot read bean property: " + propertyDescriptor.getName(), exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException("Cannot read bean property: " + propertyDescriptor.getName(), exception);
		}
	}
	
	public static void set(Object bean, String propertyName, Object value)
	{
		set(bean, getDescriptor(bean.getClass(), propertyName), value);
	}

	public static void set(Object bean, PropertyDescriptor propertyDescriptor, Object value)
	{
		try
		{
			Method method = propertyDescriptor.getWriteMethod();
			
			if (method == null)
			{
				throw new BeanException("Cannot write bean property: " + propertyDescriptor.getName());
			}
			
			method.setAccessible(true);
			
			method.invoke(bean, new Object[] {value});
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot write bean property: " + propertyDescriptor.getName(), exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException("Cannot write bean property: " + propertyDescriptor.getName(), exception);
		}
	}
	
	public static String toString(PropertyDescriptor descriptor)
	{
		StringBuilder builder = new StringBuilder();
		
		if (descriptor == null)
		{
			builder.append((Object) null);
		}
		else
		{
			builder.append(descriptor.getClass().getName());
			builder.append("[");
			builder.append("name=").append(descriptor.getName());
	
			if (descriptor.getReadMethod() != null)
			{
				builder.append(",").append("readMethod=").append(descriptor.getReadMethod().getName());
			}
			
			if (descriptor.getWriteMethod() != null)
			{
				builder.append(",").append("writeMethod=").append(descriptor.getWriteMethod().getName());
			}
			
			builder.append(",").append("type=").append(descriptor.getPropertyType().getName());
			
			if (descriptor.getPropertyEditorClass() != null)
			{
				builder.append(",").append("propertyEditorClass=")
					.append(descriptor.getPropertyEditorClass().getName());
			}
			
			builder.append(",").append("bound=").append(descriptor.isBound());
			builder.append(",").append("constrained=").append(descriptor.isConstrained());
			
			builder.append("]");
		}
		
		return builder.toString();
	}
}
