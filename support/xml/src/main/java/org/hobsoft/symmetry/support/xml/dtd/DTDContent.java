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
 * Defines a DTD element content model.
 * 
 * @author Mark Hobson
 */
public interface DTDContent
{
	/**
	 * Validates the specified element names against this content model.
	 * <p>
	 * Starting from the specified index, this method should scan the specified element names until either: an element
	 * name invalidates this content model, in which case <code>-1</code> is returned; or the end of the element names
	 * is reached, in which case the length of the element names array is returned.
	 * </p>
	 * 
	 * @param index
	 *            the element name index to start validating from
	 * @param elements
	 *            the element names to validate against, where an array element of <code>null</code> represents
	 *            character data
	 * @return the element name index after this content model, or <code>-1</code> if this content model cannot be
	 *         satisfied
	 */
	int validate(int index, String... elements);
}
