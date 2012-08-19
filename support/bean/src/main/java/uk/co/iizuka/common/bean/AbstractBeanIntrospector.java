/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/AbstractBeanIntrospector.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractBeanIntrospector.java 97399 2011-12-30 11:55:45Z mark@IIZUKA.CO.UK $
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
