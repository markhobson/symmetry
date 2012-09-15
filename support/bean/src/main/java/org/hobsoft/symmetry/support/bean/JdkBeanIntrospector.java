/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/JdkBeanIntrospector.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class JdkBeanIntrospector extends AbstractBeanIntrospector
{
	// BeanIntrospector methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasFeature(String name)
	{
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanInfo getBeanInfo(Class<?> beanClass)
	{
		try
		{
			return Introspector.getBeanInfo(beanClass);
		}
		catch (IntrospectionException exception)
		{
			throw new BeanException(exception);
		}
	}
}
