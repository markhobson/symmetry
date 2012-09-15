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

import org.hobsoft.symmetry.ui.Grid;

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
public abstract class DelegatingGridVisitor<T extends Grid, P, E extends Exception>
	extends DelegatingContainerVisitor<T, P, E>
	implements GridVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingGridVisitor(GridVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// GridVisitor methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitColumns(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).visitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit visitColumn(T grid, P parameter, int columnIndex) throws E
	{
		return getDelegate(grid, parameter).visitColumn(grid, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).endVisitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRows(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).visitRows(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.Visit visitRow(T grid, P parameter, int rowIndex) throws E
	{
		return getDelegate(grid, parameter).visitRow(grid, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.EndVisit endVisitRow(T grid, P parameter, int rowIndex) throws E
	{
		return getDelegate(grid, parameter).endVisitRow(grid, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRows(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).endVisitRows(grid, parameter);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GridVisitor<? super T, P, E> getDelegate(T grid, P parameter)
	{
		return (GridVisitor<? super T, P, E>) super.getDelegate(grid, parameter);
	}
}
