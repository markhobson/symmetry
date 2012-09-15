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

/**
 * 
 * 
 * @author Mark Hobson
 */
final class EncodedPropertyState
{
	// fields -----------------------------------------------------------------
	
	private final Object bean;
	
	private final PropertyDescriptor descriptor;
	
	private final String encodedValue;
	
	// constructors -----------------------------------------------------------
	
	public EncodedPropertyState(Object bean, PropertyDescriptor descriptor, String encodedValue)
	{
		this.bean = bean;
		this.descriptor = descriptor;
		this.encodedValue = encodedValue;
	}
	
	// public methods ---------------------------------------------------------
	
	public Object getBean()
	{
		return bean;
	}
	
	public PropertyDescriptor getDescriptor()
	{
		return descriptor;
	}
	
	public String getEncodedValue()
	{
		return encodedValue;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = bean.hashCode();
		hashCode = (hashCode * 31) + descriptor.hashCode();
		hashCode = (hashCode * 31) + safeHashCode(encodedValue);
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof EncodedPropertyState))
		{
			return false;
		}
		
		EncodedPropertyState property = (EncodedPropertyState) object;
		
		return bean.equals(property.getBean())
			&& descriptor.equals(property.getDescriptor())
			&& safeEquals(encodedValue, property.getEncodedValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s.%s=%s", bean, descriptor.getName(), encodedValue);
	}
	
	// private methods --------------------------------------------------------
	
	private static int safeHashCode(Object object)
	{
		return (object != null) ? object.hashCode() : 0;
	}
	
	private static boolean safeEquals(Object a, Object b)
	{
		return (a == null || b == null) ? (a == b) : a.equals(b);
	}
}
