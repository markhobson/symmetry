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
 * An enumeration of the basic DTD attribute declarations.
 * 
 * @author Mark Hobson
 */
public enum DTDAttrDecls implements DTDAttrDecl
{
	// constants --------------------------------------------------------------
	
	/**
	 * A DTD attribute declaration that requires the attribute to always be provided. This corresponds to the
	 * <code>#REQUIRED</code> declaration in DTD.
	 */
	REQUIRED("#REQUIRED"),
	
	/**
	 * A DTD attribute declaration that provides no default attribute value. This corresponds to the
	 * <code>#IMPLIED</code> declaration in DTD.
	 */
	IMPLIED("#IMPLIED");

	// fields -----------------------------------------------------------------
	
	private final String name;

	// constructors -----------------------------------------------------------
	
	private DTDAttrDecls(String name)
	{
		this.name = name;
	}

	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name;
	}
}
