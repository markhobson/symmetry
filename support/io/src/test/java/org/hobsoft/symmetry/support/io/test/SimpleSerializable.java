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

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SimpleSerializable implements Serializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 7987891414315418494L;
	
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public SimpleSerializable(String name)
	{
		setName(name);
	}
	
	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = (name != null) ? name : "";
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof SimpleSerializable))
		{
			return false;
		}
		
		SimpleSerializable that = (SimpleSerializable) object;
		
		return name.equals(that.getName());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + name + "]";
	}
}
