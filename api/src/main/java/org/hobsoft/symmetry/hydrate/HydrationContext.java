/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/HydrationContext.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.hobsoft.symmetry.hydrate.Utilities.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class HydrationContext
{
	// TODO: add remove(Class<?> parameterType)
	
	// fields -----------------------------------------------------------------
	
	private final Map<Class<?>, Deque<?>> parametersByType;
	
	// constructors -----------------------------------------------------------
	
	public HydrationContext()
	{
		this.parametersByType = new HashMap<Class<?>, Deque<?>>();
	}
	
	// public methods ---------------------------------------------------------
	
	public Set<Class<?>> getParameterTypes()
	{
		return new HashSet<Class<?>>(parametersByType.keySet());
	}
	
	public <T> T get(Class<T> parameterType)
	{
		T parameter = get(parameterType, null);
		
		if (parameter == null)
		{
			throw new IllegalArgumentException("Parameter not found: " + parameterType.getName());
		}
		
		return parameterType.cast(parameter);
	}
	
	public <T> T get(Class<T> parameterType, T defaultParameter)
	{
		checkNotNull(parameterType, "parameterType");
		
		T parameter;
		
		if (parametersByType.containsKey(parameterType))
		{
			parameter = getParameterDeque(parameterType).peek();
		}
		else
		{
			parameter = defaultParameter;
		}
		
		return parameter;
	}
	
	public <T> T getOrSet(Class<T> parameterType, T defaultParameter)
	{
		checkNotNull(defaultParameter, "defaultParameter");
		
		T parameter = get(parameterType, null);
		
		if (parameter == null)
		{
			parameter = defaultParameter;
			
			set(parameterType, parameter);
		}
		
		return parameter;
	}
	
	public Map<Class<?>, Object> getAll()
	{
		return getParameters(this);
	}
	
	public <T> void set(Class<T> parameterType, T parameter)
	{
		checkNotNull(parameterType, "parameterType");
		checkNotNull(parameter, "parameter");
		
		parametersByType.put(parameterType, createParameterDeque(parameter));
	}
	
	public void setAll(Map<? extends Class<?>, ?> parametersByType)
	{
		if (parametersByType == null)
		{
			return;
		}
		
		for (Entry<? extends Class<?>, ?> entry : parametersByType.entrySet())
		{
			Class<?> parameterType = entry.getKey();
			Object parameter = entry.getValue();
			
			setCapture(parameterType, parameter);
		}
	}
	
	public <T> void push(Class<T> parameterType, T parameter)
	{
		checkNotNull(parameterType, "parameterType");
		checkNotNull(parameter, "parameter");
		
		if (parametersByType.containsKey(parameterType))
		{
			getParameterDeque(parameterType).push(parameter);
		}
		else
		{
			set(parameterType, parameter);
		}
	}
	
	public <T> T pop(Class<T> parameterType)
	{
		if (!parametersByType.containsKey(parameterType))
		{
			throw new IllegalArgumentException("Parameter not found: " + parameterType.getName());
		}
		
		Deque<T> parameterDeque = getParameterDeque(parameterType);
		T parameter = parameterDeque.pop();
		
		if (parameterDeque.isEmpty())
		{
			parametersByType.remove(parameterType);
		}
		
		return parameter;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return parametersByType.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof HydrationContext))
		{
			return false;
		}
		
		HydrationContext context = (HydrationContext) object;
		
		return parametersByType.equals(context.parametersByType);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> Deque<T> createParameterDeque(T parameter)
	{
		return new LinkedList<T>(Collections.singleton(parameter));
	}
	
	private <T> Deque<T> getParameterDeque(Class<T> parameterType)
	{
		return (Deque<T>) parametersByType.get(parameterType);
	}
	
	private static Map<Class<?>, Object> getParameters(HydrationContext context)
	{
		Map<Class<?>, Object> parametersByType = new HashMap<Class<?>, Object>();
		
		for (Class<?> parameterType : context.getParameterTypes())
		{
			Object parameter = context.get(parameterType);
			
			parametersByType.put(parameterType, parameter);
		}
		
		return parametersByType;
	}
	
	private <T> void setCapture(Class<T> parameterType, Object parameter)
	{
		T castParameter = parameterType.cast(parameter);
		
		set(parameterType, castParameter);
	}
}
