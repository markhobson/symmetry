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
 * A DTD element content model that represents an element with a corresponding cardinality.
 * 
 * @author Mark Hobson
 */
public class DTDNameContent extends DTDCardinalContent
{
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	// constructors -----------------------------------------------------------
	
	public DTDNameContent(String name)
	{
		this(name, DTDCardinality.ONCE);
	}
	
	public DTDNameContent(String name, DTDCardinality cardinality)
	{
		super(cardinality);
		
		if (name == null)
		{
			throw new IllegalArgumentException("name: null");
		}
		
		this.name = name;
	}
	
	// DTDCardinalContent methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int validateOnce(int index, String... elements)
	{
		return (index < elements.length && getName().equals(elements[index])) ? index + 1 : -1;
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
		return name.hashCode() * 31 + getCardinality().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDNameContent))
		{
			return false;
		}
		
		DTDNameContent content = (DTDNameContent) object;
		
		return content.getName().equals(name) && content.getCardinality() == getCardinality();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name + getCardinality();
	}
}
