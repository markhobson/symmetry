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

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

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
class FilteringHierarchicalComponentVisitor<T extends Component, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ComponentFilter filter;

	// constructors -----------------------------------------------------------
	
	public FilteringHierarchicalComponentVisitor(HierarchicalComponentVisitor<? super T, P, E> delegate,
		ComponentFilter filter)
	{
		super(delegate);
		
		this.filter = filter;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return SKIP_CHILDREN;
		}
		
		return super.visit(component, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return VISIT_SIBLINGS;
		}
		
		return super.endVisit(component, parameter);
	}
}
