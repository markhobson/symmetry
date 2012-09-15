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
 * An abstract DTD attribute declaration that can hold a string value.
 * 
 * @author	Mark Hobson
 * @version	$Id: DTDValuedAttrDecl.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class DTDValuedAttrDecl implements DTDAttrDecl
{
	// fields -----------------------------------------------------------------
	
	private final String value;
	
	// constructors -----------------------------------------------------------
	
	public DTDValuedAttrDecl(String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value: null");
		}
		
		this.value = value;
	}
	
	// public methods ---------------------------------------------------------
	
	public String getValue()
	{
		return value;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return value.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDValuedAttrDecl))
		{
			return false;
		}
		
		DTDValuedAttrDecl decl = (DTDValuedAttrDecl) object;
		
		return value.equals(decl.getValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "'" + value + "'";
	}
}
