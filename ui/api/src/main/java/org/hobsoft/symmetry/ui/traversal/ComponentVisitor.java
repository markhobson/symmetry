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

import com.google.common.reflect.TypeToken;

/**
 * Defines a hierarchical visitor pattern for components.
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 * @see <a href="http://c2.com/cgi/wiki?HierarchicalVisitorPattern">HierarchicalVisitorPattern</a>
 */
public interface ComponentVisitor<P, E extends Exception>
{
	// public methods ---------------------------------------------------------
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	<T extends Component> HierarchicalComponentVisitor<T, P, E> visit(TypeToken<T> componentType, T component,
		P parameter) throws E;
	// CHECKSTYLE:ON
}
