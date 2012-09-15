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
import java.io.Serializable;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CustomSerializable implements Serializable
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public CustomSerializable(String name)
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
		if (!(object instanceof CustomSerializable))
		{
			return false;
		}
		
		CustomSerializable that = (CustomSerializable) object;
		
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
	
	// Serializable methods ---------------------------------------------------
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		int length = name.length();
		
		out.writeInt(length);
		
		for (int i = 0; i < length; i++)
		{
			out.writeChar(name.charAt(i));
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException
	{
		int length = in.readInt();
		
		StringBuilder builder = new StringBuilder();
		
		while (length-- > 0)
		{
			builder.append(in.readChar());
		}
		
		name = builder.toString();
	}
}
