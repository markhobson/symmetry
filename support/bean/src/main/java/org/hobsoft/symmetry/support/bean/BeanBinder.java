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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyDescriptor;

import static org.hobsoft.symmetry.support.bean.BeanUtils.addPropertyChangeListener;
import static org.hobsoft.symmetry.support.bean.BeanUtils.getPropertyChangeListeners;
import static org.hobsoft.symmetry.support.bean.BeanUtils.removePropertyChangeListener;
import static org.hobsoft.symmetry.support.bean.Properties.getDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BeanBinder
{
	// TODO: remove in preference to entangle
	// TODO: add event-set-to-event-set bindings
	// TODO: add property-to-event-set bindings and vice-versa
	
	// constructors -----------------------------------------------------------
	
	private BeanBinder()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static void push(Object sourceBean, String propertyName, Object targetBean)
	{
		push(sourceBean, propertyName, targetBean, propertyName);
	}
	
	public static void push(Object sourceBean, String sourcePropertyName, Object targetBean, String targetPropertyName)
	{
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		
		push(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void push(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		checkPropertyTypes(sourceProperty, targetProperty);
		addPropertyBinder(sourceBean, sourceProperty, targetBean, targetProperty);
		set(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void unpush(Object sourceBean, String propertyName, Object targetBean)
	{
		unpush(sourceBean, propertyName, targetBean, propertyName);
	}
	
	public static void unpush(Object sourceBean, String sourcePropertyName, Object targetBean,
		String targetPropertyName)
	{
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		
		unpush(sourceBean, sourceProperty, targetBean, targetProperty);
	}

	public static void unpush(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		PropertyBinder targetBinder = new PropertyBinder(targetBean, targetProperty);
		
		for (PropertyChangeListener listener : getPropertyChangeListeners(sourceBean))
		{
			PropertyChangeListener unproxiedListener = unproxy(listener);
			
			if (unproxiedListener instanceof PropertyBinder)
			{
				PropertyBinder binder = (PropertyBinder) unproxiedListener;
				
				if (targetBinder.equals(binder))
				{
					removePropertyChangeListener(sourceBean, sourceProperty.getName(), unproxiedListener);
				}
			}
		}
	}
	
	public static void pull(Object targetBean, String propertyName, Object sourceBean)
	{
		pull(targetBean, propertyName, sourceBean, propertyName);
	}
	
	public static void pull(Object targetBean, String targetPropertyName, Object sourceBean, String sourcePropertyName)
	{
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		
		pull(targetBean, targetProperty, sourceBean, sourceProperty);
	}
	
	public static void pull(Object targetBean, PropertyDescriptor targetProperty, Object sourceBean,
		PropertyDescriptor sourceProperty)
	{
		push(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void unpull(Object targetBean, String propertyName, Object sourceBean)
	{
		unpull(targetBean, propertyName, sourceBean, propertyName);
	}
	
	public static void unpull(Object targetBean, String targetPropertyName, Object sourceBean,
		String sourcePropertyName)
	{
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		
		unpull(targetBean, targetProperty, sourceBean, sourceProperty);
	}
	
	public static void unpull(Object targetBean, PropertyDescriptor targetProperty, Object sourceBean,
		PropertyDescriptor sourceProperty)
	{
		unpush(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void bind(Object sourceBean, String propertyName, Object targetBean)
	{
		bind(sourceBean, propertyName, targetBean, propertyName);
	}
	
	public static void bind(Object sourceBean, String sourcePropertyName, Object targetBean, String targetPropertyName)
	{
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		
		bind(sourceBean, sourceProperty, targetBean, targetProperty);
	}

	public static void bind(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		checkPropertyTypes(sourceProperty, targetProperty);
		addPropertyBinder(sourceBean, sourceProperty, targetBean, targetProperty);
		addPropertyBinder(targetBean, targetProperty, sourceBean, sourceProperty);
		set(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void unbind(Object sourceBean, String propertyName, Object targetBean)
	{
		unbind(sourceBean, propertyName, targetBean, propertyName);
	}
	
	public static void unbind(Object sourceBean, String sourcePropertyName, Object targetBean,
		String targetPropertyName)
	{
		PropertyDescriptor sourceProperty = getDescriptor(sourceBean.getClass(), sourcePropertyName);
		PropertyDescriptor targetProperty = getDescriptor(targetBean.getClass(), targetPropertyName);
		
		unbind(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	public static void unbind(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		unpush(sourceBean, sourceProperty, targetBean, targetProperty);
		unpull(sourceBean, sourceProperty, targetBean, targetProperty);
	}
	
	// private methods --------------------------------------------------------
	
	private static void checkPropertyTypes(PropertyDescriptor sourceProperty, PropertyDescriptor targetProperty)
	{
		Class<?> sourceType = sourceProperty.getPropertyType();
		Class<?> targetType = targetProperty.getPropertyType();
		
		if (!targetType.isAssignableFrom(sourceType))
		{
			throw new BeanException("Bean property type mismatch");
		}
	}
	
	private static void addPropertyBinder(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		PropertyChangeListener binder = new PropertyBinder(targetBean, targetProperty);
		addPropertyChangeListener(sourceBean, sourceProperty.getName(), binder);
	}
	
	private static void set(Object sourceBean, PropertyDescriptor sourceProperty, Object targetBean,
		PropertyDescriptor targetProperty)
	{
		Object value = Properties.get(sourceBean, sourceProperty);
		Properties.set(targetBean, targetProperty, value);
	}
	
	private static PropertyChangeListener unproxy(PropertyChangeListener listener)
	{
		if (listener instanceof PropertyChangeListenerProxy)
		{
			PropertyChangeListenerProxy proxy = (PropertyChangeListenerProxy) listener;
			listener = (PropertyChangeListener) proxy.getListener();
		}
		
		return listener;
	}
}
