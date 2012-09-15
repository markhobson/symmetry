/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/StringPropertyValueStateCodec.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.hobsoft.symmetry.util.io.IOUtils;
import org.hobsoft.symmetry.util.io.StringDataInput;
import org.hobsoft.symmetry.util.io.StringDataOutput;
import org.hobsoft.symmetry.util.io.TinyObjectInput;
import org.hobsoft.symmetry.util.io.TinyObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StringPropertyValueStateCodec.java 101084 2012-05-04 14:50:55Z mark@IIZUKA.CO.UK $
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
