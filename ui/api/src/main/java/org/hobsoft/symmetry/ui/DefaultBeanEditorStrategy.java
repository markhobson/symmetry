/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/DefaultBeanEditorStrategy.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.hobsoft.symmetry.support.bean.BeanException;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultBeanEditorStrategy implements BeanEditorStrategy
{
	// fields -----------------------------------------------------------------
	
	private final String[] propertyNames;
	
	// constructors -----------------------------------------------------------
	
	public DefaultBeanEditorStrategy(String... propertyNames)
	{
		this.propertyNames = propertyNames;
	}
	
	// BeanEditorStrategy methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T newBean(BeanEditor<T> editor)
	{
		Class<T> beanClass = editor.getBeanClass();
		
		Object[] propertyValues = new Object[propertyNames.length];
		
		for (int i = 0; i < propertyNames.length; i++)
		{
			propertyValues[i] = editor.getPropertyValue(propertyNames[i]);
		}
		
		try
		{
			Constructor<T> constructor = getConstructor(beanClass, propertyNames);
			return constructor.newInstance(propertyValues);
		}
		catch (NoSuchMethodException exception)
		{
			throw new BeanException(exception);
		}
		catch (InstantiationException exception)
		{
			throw new BeanException(exception);
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException(exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException(exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPropertyValue(Object bean, PropertyDescriptor property)
	{
		return (bean == null) ? null : Properties.get(bean, property);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPropertyValue(Object bean, PropertyDescriptor property, Object value)
	{
		Properties.set(bean, property, value);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> Constructor<T> getConstructor(Class<T> klass, String... propertyNames)
		throws NoSuchMethodException
	{
		Class<?>[] paramTypes = new Class<?>[propertyNames.length];
		
		for (int i = 0; i < propertyNames.length; i++)
		{
			paramTypes[i] = Properties.getDescriptor(klass, propertyNames[i]).getPropertyType();
		}
		
		return klass.getConstructor(paramTypes);
	}
}
