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

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipChildren;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class FilteringComponentVisitor<P, E extends Exception> extends DelegatingComponentVisitor<P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ComponentFilter filter;
	
	// constructors -----------------------------------------------------------
	
	public FilteringComponentVisitor(ComponentVisitor<P, E> delegate, ComponentFilter filter)
	{
		super(delegate);
		
		this.filter = filter;
	}
	
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return skipChildren();
		}
		
		return super.visit(componentType, component, parameter);
	}
}
