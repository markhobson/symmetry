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
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public interface HierarchicalComponentVisitor<T extends Component, P, E extends Exception>
{
	// TODO: rename as we no longer extend ComponentVisitor
	
	// types ------------------------------------------------------------------
	
	/**
	 * Traversal action when visiting a component.
	 */
	enum Visit
	{
		VISIT_CHILDREN,
		SKIP_CHILDREN;
	}
	
	/**
	 * Traversal action after visiting a component.
	 */
	enum EndVisit
	{
		VISIT_SIBLINGS,
		SKIP_SIBLINGS;
	}
	
	// public methods ---------------------------------------------------------
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visit(T component, P parameter) throws E;
	// CHECKSTYLE:ON
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	EndVisit endVisit(T component, P parameter) throws E;
	// CHECKSTYLE:ON
}
