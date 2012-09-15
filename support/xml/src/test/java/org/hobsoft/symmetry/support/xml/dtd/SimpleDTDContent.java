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
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A simple name-matching <code>DTDContent</code> implementation for use with testing <code>DTDContentContainer</code>s.
 * 
 * @author Mark Hobson
 */
public class SimpleDTDContent implements DTDContent
{
	// fields -----------------------------------------------------------------
	
	private final String name;

	// constructors -----------------------------------------------------------
	
	public SimpleDTDContent(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("name: null");
		}
		
		this.name = name;
	}
	
	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		if (index < elements.length && getName().equals(elements[index]))
		{
			index++;
		}
		else
		{
			index = -1;
		}
		
		return index;
	}

	// public methods ---------------------------------------------------------
	
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
		return name.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof SimpleDTDContent))
		{
			return false;
		}
		
		SimpleDTDContent content = (SimpleDTDContent) object;
		
		return name.equals(content.name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name;
	}
}
