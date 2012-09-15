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

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

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
public class NullGridVisitor<T extends Grid, P, E extends Exception>
	extends NullContainerVisitor<T, P, E>
	implements GridVisitor<T, P, E>
{
	// GridVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitColumns(T grid, P parameter) throws E
	{
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit visitColumn(T grid, P parameter, int columnIndex) throws E
	{
		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, P parameter) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRows(T grid, P parameter) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T grid, P parameter, int rowIndex) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T grid, P parameter, int rowIndex) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRows(T grid, P parameter) throws E
	{
		return VISIT_SIBLINGS;
	}
}
