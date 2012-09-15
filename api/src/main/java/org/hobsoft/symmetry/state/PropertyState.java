/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/PropertyState.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class PropertyState
{
	// fields -----------------------------------------------------------------
	
	private final Object bean;
	
	private final PropertyDescriptor propertyDescriptor;
	
	private final Object value;
	
	// constructors -----------------------------------------------------------
	
	public PropertyState(Object bean, PropertyDescriptor propertyDescriptor, Object value)
	{
		if (bean == null)
		{
			throw new NullPointerException("bean cannot be null");
		}
		
		if (propertyDescriptor == null)
		{
			throw new NullPointerException("propertyDescriptor cannot be null");
		}
		
		Class<?> propertyType = propertyDescriptor.getPropertyType();
		boolean primitive = propertyType.isPrimitive();
		
		if ((primitive && value == null) || (value != null && !ClassUtils.box(propertyType).isInstance(value)))
		{
			throw new IllegalArgumentException(String.format("value must match property descriptor type '%s': %s",
				propertyType.getName(), value));
		}
		
		this.bean = bean;
		this.propertyDescriptor = propertyDescriptor;
		this.value = value;
	}
	
	// public methods ---------------------------------------------------------
	
	public Object getBean()
	{
		return bean;
	}
	
	public PropertyDescriptor getDescriptor()
	{
		return propertyDescriptor;
	}
	
	public Object getValue()
	{
		return value;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = bean.hashCode();
		hashCode = (hashCode * 31) + propertyDescriptor.hashCode();
		hashCode = (hashCode * 31) + valueHashCode(value, propertyDescriptor.getPropertyType());
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof PropertyState))
		{
			return false;
		}
		
		PropertyState property = (PropertyState) object;
		
		return bean.equals(property.getBean())
			&& propertyDescriptor.equals(property.getDescriptor())
			&& valueEquals(value, property.getValue(), propertyDescriptor.getPropertyType());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(bean).append(".").append(propertyDescriptor.getName());
		builder.append("=");
		builder.append(toString(getValue()));
		
		return builder.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private static String toString(Object object)
	{
		if (object == null)
		{
			return "null";
		}
		
		Class<?> klass = object.getClass();
		
		if (!klass.isArray())
		{
			return object.toString();
		}
		
		int length = Array.getLength(object);
		
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		
		for (int i = 0; i < length; i++)
		{
			if (i > 0)
			{
				builder.append(',');
			}
			
			builder.append(Array.get(object, i));
		}
		
		builder.append(']');
		
		return builder.toString();
	}
	
	private static int valueHashCode(Object object, Class<?> type)
	{
		if (object == null)
		{
			return 0;
		}
		
		if (!type.isArray())
		{
			return object.hashCode();
		}
		
		return ArrayUtils.hashCode(object);
	}
	
	private static boolean valueEquals(Object a, Object b, Class<?> type)
	{
		if (a == null || b == null)
		{
			return (a == b);
		}
		
		if (!type.isArray())
		{
			return a.equals(b);
		}
		
		return ArrayUtils.equals(a, b);
	}
}
