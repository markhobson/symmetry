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

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * A typesafe heterogeneous container for {@code ComponentVisitor}s. See Item 29 in Effective Java Second Edition for a
 * discussion of this pattern.
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class HierarchicalComponentVisitorContainer<P, E extends Exception>
{
	// TODO: do we want to lower bound the container?
	// TODO: do we want to relax value type to ComponentVisitor<? super U, P, E>?
	
	// fields -----------------------------------------------------------------
	
	private final Map<Generic<? extends Component>, HierarchicalComponentVisitor<? extends Component, P, E>> container;
	
	// constructors -----------------------------------------------------------
	
	public HierarchicalComponentVisitorContainer()
	{
		container = new HashMap<Generic<? extends Component>,
			HierarchicalComponentVisitor<? extends Component, P, E>>();
	}
	
	// public methods ---------------------------------------------------------
	
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> get(Generic<T> componentType)
	{
		// guaranteed by put()
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>)
			container.get(componentType);
		
		return visitor;
	}
	
	public <T extends Component> void put(Generic<T> componentType, HierarchicalComponentVisitor<T, P, E> visitor)
	{
		container.put(componentType, visitor);
	}
}