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
 * A DTD element content model that disallows any content.
 * 
 * @author Mark Hobson
 */
public final class DTDEmptyContent implements DTDContent
{
	// constants --------------------------------------------------------------
	
	public static final DTDEmptyContent INSTANCE = new DTDEmptyContent();
	
	// constructors -----------------------------------------------------------
	
	private DTDEmptyContent()
	{
		// private constructor for singleton
	}

	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		return (index < elements.length) ? -1 : elements.length;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "EMPTY";
	}
}
