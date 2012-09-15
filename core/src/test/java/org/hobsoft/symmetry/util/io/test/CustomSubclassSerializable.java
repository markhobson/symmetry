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
package org.hobsoft.symmetry.util.io.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CustomSubclassSerializable extends CustomSerializable
{
	// fields -----------------------------------------------------------------
	
	private int age;
	
	// constructors -----------------------------------------------------------
	
	public CustomSubclassSerializable(String name, int age)
	{
		super(name);
		
		setAge(age);
	}
	
	// public methods ---------------------------------------------------------
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() * 31 + age;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof CustomSubclassSerializable))
		{
			return false;
		}
		
		CustomSubclassSerializable that = (CustomSubclassSerializable) object;
		
		return super.equals(object) && age == that.getAge();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + getName() + ",age=" + age + "]";
	}
	
	// Serializable methods ---------------------------------------------------
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		// write age in a non-standard way for test
		
		out.writeInt(age / 10);
		out.writeInt(age % 10);
	}
	
	private void readObject(ObjectInputStream in) throws IOException
	{
		// read age in a non-standard way for test
		
		int quotient = in.readInt();
		int remainder = in.readInt();
		
		age = (quotient * 10) + remainder;
	}
}
