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

import static com.google.common.base.Preconditions.checkState;

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
public abstract class DelegatingHierarchicalComponentVisitor<T extends Component, P, E extends Exception>
	implements HierarchicalComponentVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final HierarchicalComponentVisitor<? super T, P, E> delegate;

	// constructors -----------------------------------------------------------
	
	public DelegatingHierarchicalComponentVisitor(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		// TODO: how best to support the two modes of operation?
		// 1) a non-null delegate is provided at construction (need to checkNotNull)
		// 2) no delegate is provided at construction, but getDelegate is overridden for dynamic delegation (how to
		//    enforce?)
		// ideally we don't want two classes for this since we have many Delegating*Visitors
		
		this.delegate = delegate;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, P parameter) throws E
	{
		return getDelegate(component, parameter).visit(component, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, P parameter) throws E
	{
		return getDelegate(component, parameter).endVisit(component, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	// TODO: can this be protected?
	public HierarchicalComponentVisitor<? super T, P, E> getDelegate(T component, P parameter)
	{
		checkState(delegate != null, "getDelegate must be overridden when no delegate provided at construction");
		
		return delegate;
	}
}
