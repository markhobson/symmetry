/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/StringPropertyValueStateCodec.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import uk.co.iizuka.kozo.util.io.IOUtils;
import uk.co.iizuka.kozo.util.io.StringDataInput;
import uk.co.iizuka.kozo.util.io.StringDataOutput;
import uk.co.iizuka.kozo.util.io.TinyObjectInput;
import uk.co.iizuka.kozo.util.io.TinyObjectOutput;

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
