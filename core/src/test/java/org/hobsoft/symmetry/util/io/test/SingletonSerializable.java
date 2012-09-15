/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/SingletonSerializable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io.test;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SingletonSerializable.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public final class SingletonSerializable implements Serializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1984692460901022810L;
	
	// fields -----------------------------------------------------------------
	
	private static final Map<String, SingletonSerializable> INSTANCES_BY_NAME =
		new WeakHashMap<String, SingletonSerializable>();
	
	private final String name;
	
	// constructors -----------------------------------------------------------
	
	private SingletonSerializable(String name)
	{
		this.name = (name != null) ? name : "";
	}
	
	// public methods ---------------------------------------------------------
	
	public static synchronized SingletonSerializable getInstance(String name)
	{
		SingletonSerializable instance = INSTANCES_BY_NAME.get(name);
		
		if (instance == null)
		{
			instance = new SingletonSerializable(name);
			
			INSTANCES_BY_NAME.put(name, instance);
		}
		
		return instance;
	}
	
	public String getName()
	{
		return name;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return System.identityHashCode(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		return (this == object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + name + "]";
	}
	
	// Serializable methods ---------------------------------------------------
	
	private Object readResolve()
	{
		return getInstance(name);
	}
}
