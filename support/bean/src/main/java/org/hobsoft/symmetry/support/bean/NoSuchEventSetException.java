/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/NoSuchEventSetException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanInfo;

/**
 * Indicates that a named event set could not be found.
 * 
 * @author Mark Hobson
 * @version $Id: NoSuchEventSetException.java 86792 2011-04-11 20:47:20Z mark@IIZUKA.CO.UK $
 */
public final class NoSuchEventSetException extends BeanException
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 355842021918911446L;

	// fields -----------------------------------------------------------------
	
	private final BeanInfo beanInfo;
	
	private final String eventSetName;

	// constructors -----------------------------------------------------------
	
	public NoSuchEventSetException(BeanInfo beanInfo, String eventSetName)
	{
		this.beanInfo = beanInfo;
		this.eventSetName = eventSetName;
	}
	
	// Throwable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage()
	{
		return getBeanInfo().getBeanDescriptor().getName() + "." + getEventSetName();
	}
	
	// public methods ---------------------------------------------------------
	
	public BeanInfo getBeanInfo()
	{
		return beanInfo;
	}
	
	public String getEventSetName()
	{
		return eventSetName;
	}
}
