/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/AbstractBeanIntrospector.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractBeanIntrospector implements BeanIntrospector
{
	// fields -----------------------------------------------------------------
	
	private final Map<String, Object> featureValuesByName;
	
	// constructors -----------------------------------------------------------
	
	public AbstractBeanIntrospector()
	{
		featureValuesByName = new HashMap<String, Object>();
	}
	
	// BeanIntrospector methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getFeature(String name)
	{
		if (!hasFeature(name))
		{
			// TODO: throw better exception
			throw new IllegalArgumentException("Unsupported feature: " + name);
		}
		
		return featureValuesByName.get(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFeature(String name, Object value)
	{
		if (!hasFeature(name))
		{
			// TODO: throw better exception
			throw new IllegalArgumentException("Unsupported feature: " + name);
		}
		
		featureValuesByName.put(name, value);
	}
}
