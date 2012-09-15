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
