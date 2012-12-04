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
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.hobsoft.symmetry.support.io.IOUtils;
import org.hobsoft.symmetry.support.io.StringDataInput;
import org.hobsoft.symmetry.support.io.StringDataOutput;
import org.hobsoft.symmetry.support.io.TinyObjectInput;
import org.hobsoft.symmetry.support.io.TinyObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StringPropertyValueStateCodec extends DelegatingStateCodec
{
	// TODO: support null
	
	// constructors -----------------------------------------------------------
	
	public StringPropertyValueStateCodec(StateCodec delegate)
	{
		super(delegate);
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		StringBuilder builder = new StringBuilder();
		ObjectOutput out = new TinyObjectOutput(new StringDataOutput(builder));
		Class<?> propertyType = property.getDescriptor().getPropertyType();
		
		try
		{
			IOUtils.writeType(out, propertyType, property.getValue());
		}
		catch (IOException exception)
		{
			throw new StateException("Cannot encode property value: " + property, exception);
		}
		
		return builder.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		ObjectInput in = new TinyObjectInput(new StringDataInput(encodedValue));
		Class<?> propertyType = descriptor.getPropertyType();
		
		try
		{
			return IOUtils.readType(in, propertyType);
		}
		catch (IOException exception)
		{
			throw new StateException(String.format("Cannot decode property value: %s.%s=%s", bean,
				descriptor.getName(), encodedValue), exception);
		}
		catch (ClassNotFoundException exception)
		{
			throw new StateException(String.format("Cannot decode property value: %s.%s=%s", bean,
				descriptor.getName(), encodedValue), exception);
		}
	}
}
