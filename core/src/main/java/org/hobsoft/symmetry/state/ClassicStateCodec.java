/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/ClassicStateCodec.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.EventObject;

import org.hobsoft.symmetry.support.bean.BeanException;
import org.hobsoft.symmetry.support.bean.BeanIntrospector;
import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.support.bean.Properties;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.util.io.IOUtils;
import org.hobsoft.symmetry.util.io.StringDataInput;
import org.hobsoft.symmetry.util.io.StringDataOutput;
import org.hobsoft.symmetry.util.io.TinyObjectInput;
import org.hobsoft.symmetry.util.io.TinyObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ClassicStateCodec.java 98792 2012-02-27 12:47:49Z mark@IIZUKA.CO.UK $
 */
public class ClassicStateCodec implements StateCodec
{
	// TODO: reuse StringPropertyValueStateCodec
	
	// fields -----------------------------------------------------------------
	
	private final Codec<Object, Integer> componentCodec;
	
	private final BeanIntrospector introspector;
	
	// constructors -----------------------------------------------------------
	
	public ClassicStateCodec(Codec<Object, Integer> componentCodec, BeanIntrospector introspector)
	{
		this.componentCodec = componentCodec;
		this.introspector = introspector;
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		StringBuilder builder = new StringBuilder();
		for (PropertyState property : state.getProperties())
		{
			if (builder.length() > 0)
			{
				builder.append('&');
			}
			
			encodeProperty(builder, property);
		}
		for (EventSetState event : state.getEvents())
		{
			if (builder.length() > 0)
			{
				builder.append('&');
			}
			
			encodeEvent(builder, event);
		}
		return new EncodedState(builder.toString());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State decodeState(EncodedState encodedState) throws StateException
	{
		String string = encodedState.getState();
		State state = new State();
		
		String[] tokens = string.split("&");
		for (int i = 0; i < tokens.length; i++)
		{
			String token = tokens[i];
			int index = token.indexOf('=');
			if (index != -1)
			{
				String name = token.substring(0, index);
				String value = token.substring(index + 1);
				
				if ("event".equals(name))
				{
					state.addEvent(decodeEvent(name, value));
				}
				else
				{
					state.addProperty(decodeProperty(name, value));
				}
			}
		}
		
		return state;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodeBean(Object bean) throws StateException
	{
		Class<?> klass = bean.getClass();
		
		// FIXME: don't restrict to kozo.ui package
		while (!"org.hobsoft.symmetry.ui".equals(klass.getPackage().getName()))
		{
			klass = klass.getSuperclass();
		}
		
		String className = klass.getName();
		int index = className.lastIndexOf('.');
		
		if (index != -1)
		{
			className = className.substring(index + 1);
		}
		
		int componentId;
		try
		{
			componentId = componentCodec.encode(bean);
		}
		catch (EncoderException exception)
		{
			throw new StateException("Cannot encode bean: " + bean, exception);
		}
		
		return className.substring(0, 1).toLowerCase() + className.substring(1) + componentId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodeBean(String encodedBean) throws StateException
	{
		int index = encodedBean.length() - 1;
		
		while (Character.isDigit(encodedBean.charAt(index)))
		{
			index--;
		}
		
		int componentId = Integer.parseInt(encodedBean.substring(index + 1));
		
		try
		{
			return componentCodec.decode(componentId);
		}
		catch (DecoderException exception)
		{
			throw new StateException("Cannot decode bean: " + componentId, exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		StringBuilder builder = new StringBuilder();
		encodePropertyValue(builder, property);
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		return decodePropertyValue(encodedValue, bean, descriptor);
	}
	
	// private methods --------------------------------------------------------
	
	private void encodeProperty(StringBuilder builder, PropertyState property) throws StateException
	{
		Object component = property.getBean();
		builder.append(encodeBean(component));
		
		PropertyDescriptor descriptor = property.getDescriptor();
		builder.append(descriptor.getName());
		builder.append('=');
		encodePropertyValue(builder, property);
	}
	
	private void encodePropertyValue(StringBuilder builder, PropertyState property) throws StateException
	{
		try
		{
			ObjectOutput out = new TinyObjectOutput(new StringDataOutput(builder));
			Class<?> propertyType = property.getDescriptor().getPropertyType();
			IOUtils.writeType(out, propertyType, property.getValue());
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode property: " + property, exception);
		}
	}
	
	private void encodeEvent(StringBuilder builder, EventSetState event) throws StateException
	{
		EventSetDescriptor eventSet = event.getDescriptor();
		EventObject eventObject = event.getEventObject();
		Object source = eventObject.getSource();
		
		builder.append("event=");
		try
		{
			ObjectOutput out = new TinyObjectOutput(new StringDataOutput(builder));
			out.writeUTF(eventSet.getName());
			out.writeUTF(eventSet.getListenerMethods()[0].getName());
			out.writeUTF(encodeBean(source));
			out.writeObject(eventObject);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode event: " + event, exception);
		}
	}
	
	private PropertyState decodeProperty(String name, String value) throws StateException
	{
		int end = name.length() - 1;
		
		while (!Character.isDigit(name.charAt(end)))
		{
			end--;
		}
		
		Object component = decodeBean(name.substring(0, end + 1));
		String propertyName = name.substring(end + 1);
		BeanInfo componentInfo = introspector.getBeanInfo(component.getClass());
		PropertyDescriptor descriptor = Properties.getDescriptor(componentInfo, propertyName);
		
		Object propertyValue = decodePropertyValue(value, component, descriptor);
		
		return new PropertyState(component, descriptor, propertyValue);
	}
	
	private Object decodePropertyValue(String encodedValue, Object bean, PropertyDescriptor descriptor)
		throws StateException
	{
		try
		{
			ObjectInput in = new TinyObjectInput(new StringDataInput(encodedValue));
			Class<?> propertyType = descriptor.getPropertyType();
			return IOUtils.readType(in, propertyType);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode property", exception);
		}
		catch (ClassNotFoundException exception)
		{
			throw new StateException("Cannot decode property", exception);
		}
	}
	
	private EventSetState decodeEvent(String name, String value) throws StateException
	{
		try
		{
			ObjectInput in = new TinyObjectInput(new StringDataInput(value));
			String eventSetName = in.readUTF();
			String methodName = in.readUTF();
			Object component = decodeBean(in.readUTF());
			
			EventSetDescriptor eventSet = decodeEventSetDescriptor(eventSetName, methodName, component.getClass());
			
			if (eventSet == null)
			{
				throw new StateException("Cannot decode event set descriptor: " + eventSetName);
			}
			
			Class<?> eventType = eventSet.getListenerMethods()[0].getParameterTypes()[0];
			EventObject eventObject = (EventObject) IOUtils.readObject(in, eventType);
			StateCodecUtils.setSource(eventObject, component);
			return new EventSetState(eventSet, eventObject);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode event", exception);
		}
		catch (ClassNotFoundException exception)
		{
			throw new StateException("Cannot decode event", exception);
		}
	}
	
	private EventSetDescriptor decodeEventSetDescriptor(String eventSetName, String methodName, Class<?> beanClass)
	{
		try
		{
			BeanInfo info = introspector.getBeanInfo(beanClass);
			
			return EventSets.getDescriptor(info, eventSetName, methodName);
		}
		catch (BeanException exception)
		{
			return null;
		}
	}
}
