/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.io.test;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 
 * 
 * @author Mark Hobson
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
