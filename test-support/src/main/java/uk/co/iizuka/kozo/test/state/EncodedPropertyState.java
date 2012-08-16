/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/test-support/src/main/java/uk/co/iizuka/kozo/test/state/EncodedPropertyState.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.test.state;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EncodedPropertyState.java 95586 2011-11-28 12:30:05Z mark@IIZUKA.CO.UK $
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
