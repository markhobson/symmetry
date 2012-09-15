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
package org.hobsoft.symmetry.state;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.hobsoft.symmetry.support.bean.BeanException;
import org.hobsoft.symmetry.support.bean.BeanIntrospector;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.support.codec.base64.Base64Alphabet;
import org.hobsoft.symmetry.support.codec.base64.Base64InputStream;
import org.hobsoft.symmetry.support.codec.base64.Base64OutputStream;
import org.hobsoft.symmetry.util.io.IOUtils;
import org.hobsoft.symmetry.util.io.SerializerFactory;
import org.hobsoft.symmetry.util.io.SerializerTinyObjectInput;
import org.hobsoft.symmetry.util.io.SerializerTinyObjectOutput;
import org.hobsoft.symmetry.util.io.TinyDataInput;
import org.hobsoft.symmetry.util.io.TinyDataOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Base64StateCodec implements StateCodec
{
	// types ------------------------------------------------------------------
	
	private static class FeatureDescriptorComparator implements Comparator<FeatureDescriptor>
	{
		// constants --------------------------------------------------------------
		
		public static final FeatureDescriptorComparator INSTANCE = new FeatureDescriptorComparator();
		
		// Comparator methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(FeatureDescriptor descriptor1, FeatureDescriptor descriptor2)
		{
			return descriptor1.getName().compareTo(descriptor2.getName());
		}
	}
	
	private static class MethodComparator implements Comparator<Method>
	{
		// constants --------------------------------------------------------------
		
		public static final MethodComparator INSTANCE = new MethodComparator();
		
		// Comparator methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Method method1, Method method2)
		{
			return method1.getName().compareTo(method2.getName());
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final Codec<Object, Integer> componentCodec;
	
	private final BeanIntrospector introspector;
	
	private final SerializerFactory serializerFactory;
	
	// constructors -----------------------------------------------------------
	
	public Base64StateCodec(Codec<Object, Integer> componentCodec, BeanIntrospector introspector,
		SerializerFactory serializerFactory)
	{
		this.componentCodec = componentCodec;
		this.introspector = introspector;
		this.serializerFactory = serializerFactory;
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		try
		{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			
			ObjectOutput out = newObjectOutput(byteOut);
			
			for (PropertyState property : state.getProperties())
			{
				encodeProperty(out, property);
			}
			
			out.close();
			
			Iterator<EventSetState> events = state.getEvents().iterator();
			
			if (events.hasNext())
			{
				byteOut.write('?');
			}
			
			out = newObjectOutput(byteOut);
			
			while (events.hasNext())
			{
				encodeEvent(out, events.next());
			}
			
			out.close();
			
			return new EncodedState(byteOut.toString());
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode state", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State decodeState(EncodedState encodedState) throws StateException
	{
		String string = encodedState.getState();
		String[] tokens = string.split("\\?");
		State state = new State();
		
		try
		{
			ObjectInput in = newObjectInput(new ByteArrayInputStream(tokens[0].getBytes()));
			PropertyState property;
			
			while ((property = decodeProperty(in)) != null)
			{
				state.addProperty(property);
			}
			
			in.close();
			
			if (tokens.length > 1)
			{
				in = newObjectInput(new ByteArrayInputStream(tokens[1].getBytes()));
				
				EventSetState event;
				
				while ((event = decodeEvent(in)) != null)
				{
					state.addEvent(event);
				}
				
				in.close();
			}
			
			return state;
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode state", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodeBean(Object bean) throws StateException
	{
		try
		{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			
			ObjectOutput out = newObjectOutput(byteOut);

			encodeBean(out, bean);
			
			out.close();
			
			return byteOut.toString();
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode bean", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodeBean(String encodedBean) throws StateException
	{
		try
		{
			ObjectInput in = newObjectInput(new ByteArrayInputStream(encodedBean.getBytes()));
			
			Object bean = decodeBean(in);

			in.close();
			
			return bean;
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode bean", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		try
		{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			
			ObjectOutput out = newObjectOutput(byteOut);

			encodePropertyValue(out, property);
			
			out.close();
			
			return byteOut.toString();
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode property value", exception);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		if (encodedValue == null)
		{
			// TODO: should hydrators be responsible for this rather than the state codec?
			return TypeUtils.defaultValue(descriptor.getPropertyType());
		}
		
		try
		{
			ObjectInput in = newObjectInput(new ByteArrayInputStream(encodedValue.getBytes()));
			
			Object value = decodePropertyValue(in, bean, descriptor);

			in.close();
			
			return value;
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode property value", exception);
		}
		catch (ClassNotFoundException exception)
		{
			throw new StateException("Cannot decode property value", exception);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected ObjectInput newObjectInput(InputStream in)
	{
		DataInput dataIn = new TinyDataInput(new Base64InputStream(in, Base64Alphabet.URL));
		
		// TODO: ideally do this if we could extend rather than delegate at runtime
//		ObjectInput objectIn = new TinyObjectInput(dataIn);
//		return new SerializerObjectInput(objectIn, serializerFactory);
		
		return new SerializerTinyObjectInput(dataIn, serializerFactory);
	}
	
	protected ObjectOutput newObjectOutput(OutputStream out)
	{
		DataOutput dataOut = new TinyDataOutput(new Base64OutputStream(out, Base64Alphabet.URL, false));
		
		// TODO: ideally do this if we could extend rather than delegate at runtime
//		ObjectOutput objectOut = new TinyObjectOutput(dataOut);
//		return new SerializerObjectOutput(objectOut, serializerFactory);
		
		return new SerializerTinyObjectOutput(dataOut, serializerFactory);
	}
	
	// private methods --------------------------------------------------------
	
	private void encodeProperty(ObjectOutput out, PropertyState property) throws StateException
	{
		try
		{
			Object component = property.getBean();
			encodeBean(out, component);
			
			PropertyDescriptor descriptor = property.getDescriptor();
			int descriptorId = encodePropertyDescriptor(descriptor, component.getClass());
			
			if (descriptorId == -1)
			{
				throw new StateException("Cannot encode property descriptor: " + descriptor.getName());
			}
			
			if (descriptorId > 0xFF)
			{
				throw new StateException("Too many property descriptors: " + component.getClass().getName());
			}
			
			out.writeByte(descriptorId);
			
			encodePropertyValue(out, property);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode property: " + property, exception);
		}
	}
	
	private void encodeBean(ObjectOutput out, Object bean) throws StateException, IOException
	{
		try
		{
			Integer encodedBean = componentCodec.encode(bean);
			
			if (encodedBean == null)
			{
				throw new StateException("Cannot encode bean: " + bean);
			}
			
			out.writeInt(encodedBean);
		}
		catch (EncoderException exception)
		{
			throw new StateException("Cannot encode bean: " + bean, exception);
		}
	}
	
	private void encodePropertyValue(ObjectOutput out, PropertyState property) throws IOException
	{
		Class<?> propertyType = property.getDescriptor().getPropertyType();
		
		// TODO: ideally do this if we could extend rather than delegate at runtime
//		out = new SerializerObjectOutput(out, null, component);
		
		((SerializerTinyObjectOutput) out).setSerializerContext(property.getBean());
		IOUtils.writeType(out, propertyType, property.getValue());
	}
	
	private void encodeEvent(ObjectOutput out, EventSetState event) throws StateException
	{
		try
		{
			EventObject eventObject = event.getEventObject();
			Object component = eventObject.getSource();
			encodeBean(out, component);
			
			EventSetDescriptor eventSet = event.getDescriptor();
			int eventSetId = encodeEventSetDescriptor(eventSet, component.getClass());
			
			if (eventSetId == -1)
			{
				throw new StateException("Cannot encode event set: " + eventSet.getName());
			}
			
			if (eventSetId > 0xFF)
			{
				throw new StateException("Too many event set descriptors: " + component.getClass());
			}
			
			out.writeByte(eventSetId);
			
			// TODO: ideally do this if we could extend rather than delegate at runtime
//			out = new SerializerObjectOutput(out, null, component);
			((SerializerTinyObjectOutput) out).setSerializerContext(component);
			out.writeObject(eventObject);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode event: " + event, exception);
		}
	}
	
	private int encodePropertyDescriptor(PropertyDescriptor descriptor, Class<?> beanClass)
	{
		BeanInfo info = introspector.getBeanInfo(beanClass);
		PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
		return encodeObject(descriptors, descriptor, FeatureDescriptorComparator.INSTANCE);
	}
	
	private int encodeEventSetDescriptor(EventSetDescriptor eventSet, Class<?> beanClass)
	{
		BeanInfo info = introspector.getBeanInfo(beanClass);
		EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
		int eventSetId = encodeObject(eventSets, eventSet, FeatureDescriptorComparator.INSTANCE);
		
		if (eventSetId == -1)
		{
			return -1;
		}
		
		Method[] methods = eventSet.getListenerType().getMethods();
		Method method = eventSet.getListenerMethods()[0];
		int methodId = encodeObject(methods, method, MethodComparator.INSTANCE);
		
		if (methodId == -1)
		{
			return -1;
		}
		
		// TODO: allow customisation of number of bits
		return (eventSetId << 4) | methodId;
	}
	
	private <T> int encodeObject(T[] values, T value, Comparator<T> comparator)
	{
		// TODO: cache sorted value arrays
		List<T> list = Arrays.asList(values);
		Collections.sort(list, comparator);
		int id = Collections.binarySearch(list, value, comparator);
		return (id >= 0) ? id : -1;
	}
	
	private PropertyState decodeProperty(ObjectInput in) throws StateException
	{
		try
		{
			Object component = decodeBean(in);
			
			int descriptorId = in.readUnsignedByte();
			PropertyDescriptor descriptor = decodePropertyDescriptor(descriptorId, component.getClass());
			
			if (descriptor == null)
			{
				throw new StateException("Cannot decode property descriptor: " + descriptorId);
			}
			
			Object value = decodePropertyValue(in, component, descriptor);
			
			return new PropertyState(component, descriptor, value);
		}
		catch (EOFException exception)
		{
			return null;
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
	
	private Object decodeBean(ObjectInput in) throws StateException, IOException
	{
		int beanId = in.readInt();
		
		try
		{
			return componentCodec.decode(beanId);
		}
		catch (DecoderException exception)
		{
			throw new StateException("Cannot decode bean: " + beanId, exception);
		}
	}
	
	private Object decodePropertyValue(ObjectInput in, Object bean, PropertyDescriptor descriptor) throws IOException,
		ClassNotFoundException
	{
		Class<?> propertyType = descriptor.getPropertyType();
		
		// TODO: ideally do this if we could extend rather than delegate at runtime
//		in = new SerializerObjectInput(in, null, component);
		
		((SerializerTinyObjectInput) in).setSerializerContext(bean);
		
		return IOUtils.readType(in, propertyType);
	}
	
	private EventSetState decodeEvent(ObjectInput in) throws StateException
	{
		try
		{
			Object component = decodeBean(in);
			
			int eventSetId = in.readUnsignedByte();
			EventSetDescriptor eventSet = decodeEventSetDescriptor(eventSetId, component.getClass());
			
			if (eventSet == null)
			{
				throw new StateException("Cannot decode event set descriptor: " + eventSetId);
			}
			
			Class<?> eventType = eventSet.getListenerMethods()[0].getParameterTypes()[0];
			
			// TODO: ideally do this if we could extend rather than delegate at runtime
//			in = new SerializerObjectInput(in, null, component);
			((SerializerTinyObjectInput) in).setSerializerContext(component);
			EventObject eventObject = (EventObject) IOUtils.readObject(in, eventType);
			StateCodecUtils.setSource(eventObject, component);
			return new EventSetState(eventSet, eventObject);
		}
		catch (EOFException exception)
		{
			return null;
		}
		catch (ClassNotFoundException exception)
		{
			throw new StateException("Cannot decode property", exception);
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot decode property", exception);
		}
	}
	
	private PropertyDescriptor decodePropertyDescriptor(int id, Class<?> beanClass)
	{
		BeanInfo info = introspector.getBeanInfo(beanClass);
		PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
		return (id >= 0 && id < descriptors.length) ? descriptors[id] : null;
	}
	
	private EventSetDescriptor decodeEventSetDescriptor(int id, Class<?> beanClass)
	{
		int eventSetId = (id >>> 4) & 0xF;
		int methodId = id & 0xF;
		
		BeanInfo info = introspector.getBeanInfo(beanClass);
		EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
		EventSetDescriptor eventSet = (EventSetDescriptor) decodeObject(eventSets, eventSetId,
			FeatureDescriptorComparator.INSTANCE);
		
		if (eventSet == null)
		{
			return null;
		}
		
		Method[] methods = eventSet.getListenerMethods();
		Method method = decodeObject(methods, methodId, MethodComparator.INSTANCE);
		
		if (method == null)
		{
			return null;
		}
		
		try
		{
			return new EventSetDescriptor(eventSet.getName(), eventSet.getListenerType(), new Method[] {method},
				eventSet.getAddListenerMethod(), eventSet.getRemoveListenerMethod(), eventSet.getGetListenerMethod());
		}
		catch (IntrospectionException exception)
		{
			throw new BeanException(exception);
		}
	}
	
	private <T> T decodeObject(T[] values, int index, Comparator<T> comparator)
	{
		// TODO: cache sorted value arrays
		List<T> list = Arrays.asList(values);
		Collections.sort(list, comparator);
		return list.get(index);
	}
}
