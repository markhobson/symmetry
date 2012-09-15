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
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EventListener;
import java.util.EventObject;

import static org.hobsoft.symmetry.support.bean.Utilities.checkNotNull;
import static org.hobsoft.symmetry.support.bean.Utilities.nullEquals;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class EventSets
{
	// constructors -----------------------------------------------------------
	
	private EventSets()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static EventSetDescriptor getDescriptor(Object bean, String eventSetName)
	{
		return getDescriptor(bean.getClass(), eventSetName);
	}
	
	public static EventSetDescriptor getDescriptor(Class<?> beanClass, String eventSetName)
	{
		return getDescriptor(BeanUtils.getBeanInfo(beanClass), eventSetName);
	}

	public static EventSetDescriptor getDescriptor(BeanInfo beanInfo, String eventSetName)
	{
		checkNotNull(beanInfo, "beanInfo");
		checkNotNull(eventSetName, "eventSetName");
		
		EventSetDescriptor[] descriptors = beanInfo.getEventSetDescriptors();
		
		for (EventSetDescriptor descriptor : descriptors)
		{
			if (eventSetName.equals(descriptor.getName()))
			{
				return descriptor;
			}
		}
		
		throw new NoSuchEventSetException(beanInfo, eventSetName);
	}
	
	public static EventSetDescriptor getDescriptor(Object bean, String eventSetName, String listenerMethodName)
	{
		return getDescriptor(bean.getClass(), eventSetName, listenerMethodName);
	}

	public static EventSetDescriptor getDescriptor(Class<?> beanClass, String eventSetName, String listenerMethodName)
	{
		return getDescriptor(BeanUtils.getBeanInfo(beanClass), eventSetName, listenerMethodName);
	}

	public static EventSetDescriptor getDescriptor(BeanInfo beanInfo, String eventSetName, String listenerMethodName)
	{
		checkNotNull(beanInfo, "beanInfo");
		checkNotNull(eventSetName, "eventSetName");
		checkNotNull(listenerMethodName, "listenerMethodName");
		
		EventSetDescriptor descriptor = getDescriptor(beanInfo, eventSetName);
		
		MethodDescriptor listenerMethodDescriptor = BeanUtils.getListenerMethodDescriptor(descriptor,
			listenerMethodName);
		Method[] listenerMethods = new Method[] {listenerMethodDescriptor.getMethod()};
		
		try
		{
			return new EventSetDescriptor(descriptor.getName(), descriptor.getListenerType(), listenerMethods,
				descriptor.getAddListenerMethod(), descriptor.getRemoveListenerMethod(),
				descriptor.getGetListenerMethod());
		}
		catch (IntrospectionException exception)
		{
			throw new BeanException("Cannot construct event set descriptor", exception);
		}
	}
	
	public static void addListener(Object bean, String eventSetName, EventListener listener)
	{
		BeanInfo info = BeanUtils.getBeanInfo(bean.getClass());
		
		EventSetDescriptor eventSet = getDescriptor(info, eventSetName);
		
		addListener(bean, eventSet, listener);
	}
	
	public static void addListener(Object bean, EventSetDescriptor eventSetDescriptor, EventListener listener)
	{
		Method method = eventSetDescriptor.getAddListenerMethod();
		
		try
		{
			method.invoke(bean, listener);
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot add event set listener: " + eventSetDescriptor.getName(), exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException("Cannot add event set listener: " + eventSetDescriptor.getName(), exception);
		}
	}
	
	public static void removeListener(Object bean, String eventSetName, EventListener listener)
	{
		BeanInfo info = BeanUtils.getBeanInfo(bean.getClass());
		
		EventSetDescriptor eventSet = getDescriptor(info, eventSetName);
		
		removeListener(bean, eventSet, listener);
	}
	
	public static void removeListener(Object bean, EventSetDescriptor eventSetDescriptor,
		EventListener listener)
	{
		Method method = eventSetDescriptor.getRemoveListenerMethod();
		
		try
		{
			method.invoke(bean, new Object[] {listener});
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot remove event set listener: " + eventSetDescriptor.getName(), exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException("Cannot remove event set listener: " + eventSetDescriptor.getName(), exception);
		}
	}
	
	public static EventListener[] getListeners(Object bean, String eventSetName)
	{
		BeanInfo info = BeanUtils.getBeanInfo(bean.getClass());
		
		EventSetDescriptor eventSet = getDescriptor(info, eventSetName);
		
		return getListeners(bean, eventSet);
	}

	public static EventListener[] getListeners(Object bean, EventSetDescriptor eventSetDescriptor)
	{
		Method method = eventSetDescriptor.getGetListenerMethod();
		
		try
		{
			return (EventListener[]) method.invoke(bean);
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot get event set listeners: " + eventSetDescriptor.getName(), exception);
		}
		catch (InvocationTargetException exception)
		{
			throw new BeanException("Cannot get event set listeners: " + eventSetDescriptor.getName(), exception);
		}
	}
	
	public static void fire(Object bean, EventSetDescriptor eventSetDescriptor, EventObject eventObject)
	{
		EventListener[] listeners = getListeners(bean, eventSetDescriptor);
		
		Method eventMethod = eventSetDescriptor.getListenerMethods()[0];

		Consumable consumable = (eventObject instanceof Consumable) ? (Consumable) eventObject : null;
		
		Object[] args = null;
		
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			if (args == null)
			{
				args = new Object[] {eventObject};
			}
			
			if (consumable == null || !consumable.isConsumed())
			{
				try
				{
					eventMethod.invoke(listeners[i], args);
				}
				catch (IllegalAccessException exception)
				{
					throw new BeanException("Cannot fire event set: " + eventSetDescriptor.getName(), exception);
				}
				catch (InvocationTargetException exception)
				{
					throw new BeanException("Cannot fire event set: " + eventSetDescriptor.getName(), exception);
				}
			}
		}
	}
	
	public static boolean equals(EventSetDescriptor descriptor1, EventSetDescriptor descriptor2)
	{
		if (descriptor1 == descriptor2)
		{
			return true;
		}
		
		// TODO: check all properties
		return nullEquals(descriptor1.getAddListenerMethod(), descriptor2.getAddListenerMethod())
			&& nullEquals(descriptor1.getRemoveListenerMethod(), descriptor2.getRemoveListenerMethod())
			&& nullEquals(descriptor1.getGetListenerMethod(), descriptor2.getGetListenerMethod())
			&& Arrays.equals(descriptor1.getListenerMethods(), descriptor2.getListenerMethods())
			&& descriptor1.isUnicast() == descriptor2.isUnicast();
	}
	
	public static String toString(EventSetDescriptor descriptor)
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
			
			if (descriptor.getAddListenerMethod() != null)
			{
				builder.append(",").append("addMethod=").append(descriptor.getAddListenerMethod().getName());
			}

			if (descriptor.getRemoveListenerMethod() != null)
			{
				builder.append(",").append("removeMethod=").append(descriptor.getRemoveListenerMethod().getName());
			}

			if (descriptor.getGetListenerMethod() != null)
			{
				builder.append(",").append("getMethod=").append(descriptor.getGetListenerMethod().getName());
			}
			
			builder.append(",").append("listenerMethods=").append("[");
			
			Method[] listenerMethods = descriptor.getListenerMethods();
			
			for (int i = 0; i < listenerMethods.length; i++)
			{
				if (i > 0)
				{
					builder.append(",");
				}
				
				builder.append(listenerMethods[i].getName());
			}
			
			builder.append("]");

			// TODO: other properties
			
			builder.append("]");
		}
		
		return builder.toString();
	}
}
