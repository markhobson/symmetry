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
 * Defines a visitor API for UI components.
 * 
 * @param <P>
 *            the type of parameter required by this visitor
 * @param <E>
 *            the type of exception thrown by this visitor
 */
public interface ComponentVisitor<P, E extends Exception>
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	// CHECKSTYLE:OFF
	void visit(Window window, P parameter) throws E;
	// CHECKSTYLE:ON
}
