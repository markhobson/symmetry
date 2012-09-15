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
package org.hobsoft.symmetry.support.xml.dom;

import org.w3c.dom.Node;

/**
 * Provides various utility methods for working with the DOM Validation API.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/2004/REC-DOM-Level-3-Val-20040127/">Document Object Model (DOM) Level 3 Validation
 *      Specification</a>
 */
public final class ValidationUtils
{
	// constants --------------------------------------------------------------
	
	/**
	 * The name of the DOM Validation feature.
	 */
	public static final String VALIDATION_FEATURE = "Validation";
	
	/**
	 * The version of the DOM Level 3 Validation feature.
	 */
	public static final String VALIDATION_VERSION_3_0 = "3.0";
	
	// constructors -----------------------------------------------------------
	
	private ValidationUtils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Validation 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Validation 3.0
	 */
	public static boolean hasValidation3(Node node)
	{
		return NodeUtils.hasFeature(node, VALIDATION_FEATURE, VALIDATION_VERSION_3_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Validation 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Validation 3.0
	 */
	public static void ensureValidation3(Node node)
	{
		NodeUtils.ensureFeature(node, VALIDATION_FEATURE, VALIDATION_VERSION_3_0);
	}
}
