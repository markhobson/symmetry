/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/NoSuchMethodException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.EventSetDescriptor;

/**
 * Indicates that a named method could not be found.
 * 
 * @author Mark Hobson
 */
public final class NoSuchMethodException extends BeanException
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = -4902537550270320721L;

	// fields -----------------------------------------------------------------
	
	private final EventSetDescriptor descriptor;
	
	private final String methodName;

	// constructors -----------------------------------------------------------
	
	public NoSuchMethodException(EventSetDescriptor descriptor, String methodName)
	{
		this.descriptor = descriptor;
		this.methodName = methodName;
	}
	
	// Throwable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage()
	{
		return getEventSetDescriptor().getName() + "." + getMethodName();
	}
	
	// public methods ---------------------------------------------------------
	
	public EventSetDescriptor getEventSetDescriptor()
	{
		return descriptor;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
}
