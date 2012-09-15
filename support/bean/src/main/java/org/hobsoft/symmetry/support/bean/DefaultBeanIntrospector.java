/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/DefaultBeanIntrospector.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultBeanIntrospector extends AbstractBeanIntrospector
{
	// constants --------------------------------------------------------------
	
	private static final String ADD_EVENT_LISTENER_PATTERN = "add([A-Z]\\w+)Listener";

	private static final String REMOVE_EVENT_LISTENER_PATTERN = "remove([A-Z]\\w+)Listener";

	private static final String GET_EVENT_LISTENERS_PATTERN = "get([A-Z]\\w+)Listeners";

	private static final String GET_PROPERTY_PATTERN = "(?:get|is)([A-Z]\\w+)";

	private static final String SET_PROPERTY_PATTERN = "set([A-Z]\\w+)";

	// BeanIntrospector methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasFeature(String name)
	{
		return BeanIntrospectorFeatures.FLUENT_METHODS.equals(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanInfo getBeanInfo(Class<?> beanClass)
	{
		// TODO: cache
		
		try
		{
			return createBeanInfo(beanClass);
		}
		catch (IntrospectionException exception)
		{
			// TODO: better
			throw new BeanException(exception);
		}
	}

	// private methods --------------------------------------------------------
	
	private BeanInfo createBeanInfo(Class<?> beanClass) throws IntrospectionException
	{
		BeanDescriptor beanDescriptor = createBeanDescriptor(beanClass);
		EventSetDescriptor[] eventSetDescriptors = createEventSetDescriptors(beanClass);
		PropertyDescriptor[] propertyDescriptors = createPropertyDescriptors(beanClass);

		return new DefaultBeanInfo(beanDescriptor, eventSetDescriptors, propertyDescriptors);
	}
	
	private BeanDescriptor createBeanDescriptor(Class<?> beanClass)
	{
		return new BeanDescriptor(beanClass);
	}
	
	private EventSetDescriptor[] createEventSetDescriptors(Class<?> beanClass) throws IntrospectionException
	{
		// analyse methods
		
		Map<String, Method> addMethodsByEventSetName = new HashMap<String, Method>();
		Map<String, Method> removeMethodsByEventSetName = new HashMap<String, Method>();
		Map<String, Method> getMethodsByEventSetName = new HashMap<String, Method>();
		
		for (Method method : beanClass.getMethods())
		{
			if (isAddEventListener(method))
			{
				String eventSetName = getAddEventListenerName(method);
				
				addMethodsByEventSetName.put(eventSetName, method);
			}
			else if (isRemoveEventListener(method))
			{
				String eventSetName = getRemoveEventListenerName(method);
				
				removeMethodsByEventSetName.put(eventSetName, method);
			}
			else if (isGetEventListeners(method))
			{
				String eventSetName = getGetEventListenersName(method);
				
				getMethodsByEventSetName.put(eventSetName, method);
			}
		}
		
		// construct descriptors
		
		Map<String, EventSetDescriptor> descriptorsByEventSetName = new TreeMap<String, EventSetDescriptor>();
		
		for (String eventSetName : addMethodsByEventSetName.keySet())
		{
			Method addMethod = addMethodsByEventSetName.get(eventSetName);
			Method removeMethod = removeMethodsByEventSetName.get(eventSetName);
			Method getMethod = getMethodsByEventSetName.get(eventSetName);
			
			Class<?> listenerType = getListenerType(addMethod);
			Method[] listenerMethods = getListenerMethods(listenerType);
			
			EventSetDescriptor descriptor = new EventSetDescriptor(eventSetName, listenerType, listenerMethods,
				addMethod, removeMethod, getMethod);
			
			descriptorsByEventSetName.put(eventSetName, descriptor);
		}
		
		// return as an array

		Collection<EventSetDescriptor> descriptors = descriptorsByEventSetName.values();
		
		EventSetDescriptor[] descriptorArray;
		
		if (descriptors.isEmpty())
		{
			descriptorArray = null;
		}
		else
		{
			descriptorArray = descriptors.toArray(new EventSetDescriptor[descriptors.size()]);
		}
		
		return descriptorArray;
	}
	
	private PropertyDescriptor[] createPropertyDescriptors(Class<?> beanClass) throws IntrospectionException
	{
		// analyse methods
		
		Map<String, Method> readMethodsByPropertyName = new HashMap<String, Method>();
		Map<String, Method> writeMethodsByPropertyName = new HashMap<String, Method>();

		for (Method method : beanClass.getMethods())
		{
			if (isGetProperty(method))
			{
				String propertyName = getGetPropertyName(method);
				
				readMethodsByPropertyName.put(propertyName, method);
			}
			else if (isSetProperty(method))
			{
				String propertyName = getSetPropertyName(method);

				writeMethodsByPropertyName.put(propertyName, method);
			}
		}
		
		// construct descriptors
		
		Map<String, PropertyDescriptor> descriptorsByPropertyName = new TreeMap<String, PropertyDescriptor>();

		Set<String> propertyNames = new HashSet<String>();
		propertyNames.addAll(readMethodsByPropertyName.keySet());
		propertyNames.addAll(writeMethodsByPropertyName.keySet());
		
		for (String propertyName : propertyNames)
		{
			Method readMethod = readMethodsByPropertyName.get(propertyName);
			Method writeMethod = writeMethodsByPropertyName.get(propertyName);
			
			PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, readMethod, writeMethod);
			
			descriptorsByPropertyName.put(propertyName, descriptor);
		}
		
		// return as an array
		
		Collection<PropertyDescriptor> descriptors = descriptorsByPropertyName.values();
		
		PropertyDescriptor[] descriptorArray;
		
		if (descriptors.isEmpty())
		{
			descriptorArray = null;
		}
		else
		{
			descriptorArray = descriptors.toArray(new PropertyDescriptor[descriptors.size()]);
		}
		
		return descriptorArray;
	}
	
	private boolean isAddEventListener(Method method)
	{
		// TODO: support fluent methods?
		
		return void.class.equals(method.getReturnType())
			&& method.getParameterTypes().length == 1
			&& EventListener.class.isAssignableFrom(method.getParameterTypes()[0])
			&& Pattern.matches(ADD_EVENT_LISTENER_PATTERN, method.getName());
	}
	
	private boolean isRemoveEventListener(Method method)
	{
		// TODO: support fluent methods?
		
		return void.class.equals(method.getReturnType())
			&& method.getParameterTypes().length == 1
			&& EventListener.class.isAssignableFrom(method.getParameterTypes()[0])
			&& Pattern.matches(REMOVE_EVENT_LISTENER_PATTERN, method.getName());
	}
	
	private boolean isGetEventListeners(Method method)
	{
		return method.getReturnType().isArray()
			&& EventListener.class.isAssignableFrom(method.getReturnType().getComponentType())
			&& method.getParameterTypes().length == 0
			&& Pattern.matches(GET_EVENT_LISTENERS_PATTERN, method.getName());
	}
	
	private boolean isGetProperty(Method method)
	{
		return !void.class.equals(method.getReturnType())
			&& method.getParameterTypes().length == 0
			&& Pattern.matches(GET_PROPERTY_PATTERN, method.getName());
	}
	
	private boolean isSetProperty(Method method)
	{
		return isVoidOrFluentReturnType(method)
			&& method.getParameterTypes().length == 1
			&& Pattern.matches(SET_PROPERTY_PATTERN, method.getName());
	}
	
	private boolean isVoidOrFluentReturnType(Method method)
	{
		Class<?> returnType = method.getReturnType();
		Class<?> beanClass = method.getDeclaringClass();
		
		return void.class.equals(returnType) || isFluentMethods() && returnType.isAssignableFrom(beanClass);
	}
	
	private String getAddEventListenerName(Method method)
	{
		return getCaptureGroup(method, ADD_EVENT_LISTENER_PATTERN);
	}
	
	private String getRemoveEventListenerName(Method method)
	{
		return getCaptureGroup(method, REMOVE_EVENT_LISTENER_PATTERN);
	}
	
	private String getGetEventListenersName(Method method)
	{
		return getCaptureGroup(method, GET_EVENT_LISTENERS_PATTERN);
	}
	
	private Class<?> getListenerType(Method addMethod)
	{
		return addMethod.getParameterTypes()[0];
	}
	
	private Method[] getListenerMethods(Class<?> listenerType)
	{
		return listenerType.getMethods();
	}
	
	private String getGetPropertyName(Method method)
	{
		return getCaptureGroup(method, GET_PROPERTY_PATTERN);
	}
	
	private String getSetPropertyName(Method method)
	{
		return getCaptureGroup(method, SET_PROPERTY_PATTERN);
	}
	
	private String getCaptureGroup(Method method, String pattern)
	{
		Matcher matcher = Pattern.compile(pattern).matcher(method.getName());
		
		if (!matcher.matches())
		{
			throw new IllegalStateException("Method name does not match pattern: " + pattern);
		}
		
		if (matcher.groupCount() < 1)
		{
			throw new IllegalStateException("Missing capture group in pattern: " + pattern);
		}
		
		String group = matcher.group(1);
		
		if (group == null)
		{
			throw new IllegalStateException("Unmatched capture group in pattern: " + pattern);
		}
		
		return uncamelCase(group);
	}
	
	private String uncamelCase(String string)
	{
		if (string == null || string.length() == 0)
		{
			return string;
		}
		
		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}
	
	private boolean isFluentMethods()
	{
		return Boolean.TRUE.equals(getFeature(BeanIntrospectorFeatures.FLUENT_METHODS));
	}
}
