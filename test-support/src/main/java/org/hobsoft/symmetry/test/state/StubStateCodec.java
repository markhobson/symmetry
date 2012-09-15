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
package org.hobsoft.symmetry.test.state;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * State codec stub for use in testing.
 * 
 * @author Mark Hobson
 */
public class StubStateCodec implements StateCodec
{
	// fields -----------------------------------------------------------------
	
	private EncodedState encodedState;
	
	private State state;
	
	private final Map<Object, String> encodedBeansByBean;
	
	private final Map<String, Object> beansByEncodedBean;
	
	private final Map<PropertyState, String> encodedValuesByProperty;
	
	private final Map<EncodedPropertyState, Object> valuesByEncodedProperty;
	
	// constructors -----------------------------------------------------------
	
	public StubStateCodec()
	{
		encodedState = new EncodedState("state");
		state = new State();
		
		encodedBeansByBean = new HashMap<Object, String>();
		beansByEncodedBean = new HashMap<String, Object>();
		encodedValuesByProperty = new HashMap<PropertyState, String>();
		valuesByEncodedProperty = new HashMap<EncodedPropertyState, Object>();
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		return encodedState;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State decodeState(EncodedState encodedState) throws StateException
	{
		return state;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodeBean(Object bean) throws StateException
	{
		return encodedBeansByBean.get(bean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodeBean(String encodedBean) throws StateException
	{
		return beansByEncodedBean.get(encodedBean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		return encodedValuesByProperty.get(property);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		return valuesByEncodedProperty.get(new EncodedPropertyState(bean, descriptor, encodedValue));
	}

	// public methods ---------------------------------------------------------
	
	public State getState()
	{
		return state;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public EncodedState getEncodedState()
	{
		return encodedState;
	}
	
	public void setEncodedState(EncodedState encodedState)
	{
		this.encodedState = encodedState;
	}
	
	public void setEncodedBean(Object bean, String encodedBean)
	{
		if (encodedBeansByBean.containsKey(bean))
		{
			throw new IllegalArgumentException("Encoded bean already set for bean: " + bean);
		}
		
		if (beansByEncodedBean.containsKey(encodedBean))
		{
			throw new IllegalArgumentException("Bean already set for encoded bean: " + encodedBean);
		}
		
		encodedBeansByBean.put(bean, encodedBean);
		beansByEncodedBean.put(encodedBean, bean);
	}
	
	public void setEncodedPropertyValue(Object bean, String propertyName, Object value, String encodedValue)
	{
		setEncodedPropertyValue(bean, Properties.getDescriptor(bean, propertyName), value, encodedValue);
	}
	
	public void setEncodedPropertyValue(Object bean, PropertyDescriptor descriptor, Object value, String encodedValue)
	{
		setEncodedPropertyValue(new PropertyState(bean, descriptor, value), encodedValue);
	}
	
	public void setEncodedPropertyValue(PropertyState property, String encodedValue)
	{
		if (encodedValuesByProperty.containsKey(property))
		{
			throw new IllegalArgumentException("Encoded property value already set for property: " + property);
		}
		
		EncodedPropertyState encodedProperty = new EncodedPropertyState(property.getBean(), property.getDescriptor(),
			encodedValue);
		
		if (valuesByEncodedProperty.containsKey(encodedProperty))
		{
			throw new IllegalArgumentException("Property value already set for encoded property: " + encodedProperty);
		}
		
		encodedValuesByProperty.put(property, encodedValue);
		valuesByEncodedProperty.put(encodedProperty, property.getValue());
	}
}
