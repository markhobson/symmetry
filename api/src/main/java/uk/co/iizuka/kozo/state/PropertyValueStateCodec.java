/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/PropertyValueStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyValueStateCodec.java 98230 2012-02-02 11:34:34Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the property type
 */
public abstract class PropertyValueStateCodec<T> extends DelegatingStateCodec
{
	// fields -----------------------------------------------------------------
	
	private final PropertyDescriptor propertyDescriptor;
	
	private final Class<T> propertyType;
	
	// constructors -----------------------------------------------------------
	
	public PropertyValueStateCodec(StateCodec delegate, PropertyDescriptor propertyDescriptor, Class<T> propertyType)
	{
		super(delegate);
		
		if (!ClassUtils.box(propertyDescriptor.getPropertyType()).isAssignableFrom(propertyType))
		{
			throw new IllegalArgumentException(String.format("propertyDescriptor must be a '%s' property: %s",
				propertyType.getName(), propertyDescriptor));
		}
		
		this.propertyDescriptor = propertyDescriptor;
		this.propertyType = propertyType;
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String encodePropertyValue(PropertyState property) throws StateException
	{
		String encodedValue;
		
		if (propertyDescriptor.equals(property.getDescriptor()))
		{
			T value = propertyType.cast(property.getValue());
			
			encodedValue = encodePropertyValue(property.getBean(), value);
		}
		else
		{
			encodedValue = super.encodePropertyValue(property);
		}
		
		return encodedValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		Object decodedValue;

		if (propertyDescriptor.equals(descriptor))
		{
			decodedValue = decodePropertyValue(bean, encodedValue);
		}
		else
		{
			decodedValue = super.decodePropertyValue(bean, descriptor, encodedValue);
		}
		
		return decodedValue;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract String encodePropertyValue(Object bean, T value) throws StateException;

	protected abstract T decodePropertyValue(Object bean, String encodedValue) throws StateException;
}
