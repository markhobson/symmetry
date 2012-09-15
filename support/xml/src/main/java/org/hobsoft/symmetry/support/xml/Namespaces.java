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
package org.hobsoft.symmetry.support.xml;

/**
 * Provides constants related to the W3C XML Namespaces specification.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/REC-xml-names/">Namespaces in XML</a>
 */
public final class Namespaces
{
	// constants --------------------------------------------------------------
	
	/**
	 * The XML Namespaces namespace URI.
	 */
	public static final String XMLNS = "http://www.w3.org/2000/xmlns/";
	
	/**
	 * The XML Namespaces prefix.
	 */
	public static final String PREFIX = "xmlns";
	
	// constructors -----------------------------------------------------------
	
	private Namespaces()
	{
		throw new AssertionError();
	}
}
