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
package org.hobsoft.symmetry.ui;

/**
 * Defines a Symmetry UI component.
 */
public interface Component
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Accepts the specified visitor on this component.
	 * 
	 * @param visitor
	 *            the visitor to accept
	 * @param parameter
	 *            the parameter to supply to the visitor
	 * @throws E
	 *             if an error occurs visiting this component
	 * @param <P>
	 *            the type of parameter required by the visitor
	 * @param <E>
	 *            the type of exception thrown by the visitor
	 */
	<P, E extends Exception> void accept(ComponentVisitor<P, E> visitor, P parameter) throws E;
}
