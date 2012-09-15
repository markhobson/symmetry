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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SimpleExternalizable implements Externalizable
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public SimpleExternalizable()
	{
		this(null);
	}
	
	public SimpleExternalizable(String name)
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
		if (name == null)
		{
			name = "";
		}
		
		this.name = name;
	}
	
	// Externalizable methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeUTF(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		name = in.readUTF();
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
		if (!(object instanceof SimpleExternalizable))
		{
			return false;
		}
		
		SimpleExternalizable that = (SimpleExternalizable) object;
		
		return name.equals(that.getName());
	}
}
