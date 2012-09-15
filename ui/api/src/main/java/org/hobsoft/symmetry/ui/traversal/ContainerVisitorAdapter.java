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

import org.hobsoft.symmetry.ui.Container;

/**
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code ContainerVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class ContainerVisitorAdapter<T extends Container, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ContainerVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ContainerVisitor<? super T, P, E> containerDelegate;

	// constructors -----------------------------------------------------------
	
	public ContainerVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullContainerVisitor());
	}
	
	public ContainerVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		ContainerVisitor<? super T, P, E> containerDelegate)
	{
		super(delegate);
		
		this.containerDelegate = containerDelegate;
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T container, P parameter, int childIndex) throws E
	{
		return containerDelegate.visitChild(container, parameter, childIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T container, P parameter, int childIndex) throws E
	{
		return containerDelegate.endVisitChild(container, parameter, childIndex);
	}
}
