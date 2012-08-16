/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/test-support/src/main/java/uk/co/iizuka/kozo/test/state/StubStateCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.test.state;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.common.bean.Properties;
import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.PropertyState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.state.StateException;

/**
 * State codec stub for use in testing.
 * 
 * @author Mark Hobson
 * @version $Id: StubStateCodec.java 95586 2011-11-28 12:30:05Z mark@IIZUKA.CO.UK $
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
