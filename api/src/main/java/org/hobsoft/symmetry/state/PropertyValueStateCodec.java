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

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
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
