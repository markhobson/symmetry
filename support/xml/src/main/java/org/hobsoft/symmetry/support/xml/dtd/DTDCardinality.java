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
 * An enumeration of the various occurrence types used by DTD content models.
 * 
 * @author	Mark Hobson
 */
public enum DTDCardinality
{
	// constants --------------------------------------------------------------
	
	/**
	 * An occurrence of exactly once. This corresponds to the default syntax used in DTD.
	 */
	ONCE(""),
	
	/**
	 * An occurrence of one or more times. This corresponds to the "<code>+</code>" syntax used in DTD.
	 */
	ONE_OR_MORE("+"),
	
	/**
	 * An occurrence of zero or more times. This corresponds to the "<code>*</code>" syntax used in DTD.
	 */
	ZERO_OR_MORE("*"),
	
	/**
	 * An occurrence of zero or one times. This corresponds to the "<code>?</code>" syntax used in DTD.
	 */
	ZERO_OR_ONE("?");
	
	// fields -----------------------------------------------------------------
	
	private final String name;
	
	// constructors -----------------------------------------------------------
	
	private DTDCardinality(String name)
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
