/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/NoSuchPropertyException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.BeanInfo;

/**
 * Indicates that a named property could not be found.
 * 
 * @author Mark Hobson
 * @version $Id: NoSuchPropertyException.java 86792 2011-04-11 20:47:20Z mark@IIZUKA.CO.UK $
 */
public final class NoSuchPropertyException extends BeanException
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1228951278432397454L;

	// fields -----------------------------------------------------------------
	
	private final BeanInfo beanInfo;
	
	private final String propertyName;

	// constructors -----------------------------------------------------------
	
	public NoSuchPropertyException(BeanInfo beanInfo, String propertyName)
	{
		this.beanInfo = beanInfo;
		this.propertyName = propertyName;
	}
	
	// Throwable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage()
	{
		return getBeanInfo().getBeanDescriptor().getName() + "." + getPropertyName();
	}
	
	// public methods ---------------------------------------------------------
	
	public BeanInfo getBeanInfo()
	{
		return beanInfo;
	}
	
	public String getPropertyName()
	{
		return propertyName;
	}
}
