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
 * A DTD attribute declaration that provides a default attribute value. This corresponds to the default declaration in
 * DTD.
 * 
 * @author Mark Hobson
 */
public class DTDDefaultAttrDecl extends DTDValuedAttrDecl
{
	// constructors -----------------------------------------------------------
	
	public DTDDefaultAttrDecl(String value)
	{
		super(value);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDDefaultAttrDecl))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
