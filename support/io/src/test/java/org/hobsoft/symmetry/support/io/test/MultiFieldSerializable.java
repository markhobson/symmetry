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
public class MultiFieldSerializable implements Serializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 2038666496362382018L;
	
	// fields -----------------------------------------------------------------
	
	private String name;
	
	private int age;
	
	private double height;
	
	// constructors -----------------------------------------------------------
	
	public MultiFieldSerializable(String name, int age, double height)
	{
		setName(name);
		setAge(age);
		setHeight(height);
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
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = name.hashCode();
		hashCode = (hashCode * 31) + age;
		hashCode = (hashCode * 31) + Double.valueOf(height).hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof MultiFieldSerializable))
		{
			return false;
		}
		
		MultiFieldSerializable that = (MultiFieldSerializable) object;
		
		return name.equals(that.getName())
			&& age == that.getAge()
			&& height == that.getHeight();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + name + ",age=" + age + ",height=" + height + "]";
	}
}
