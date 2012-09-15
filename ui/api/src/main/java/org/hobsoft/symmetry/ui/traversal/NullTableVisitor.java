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

import org.hobsoft.symmetry.ui.Table;

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
public class NullTableVisitor<T extends Table, P, E extends Exception>
	extends NullHierarchicalComponentVisitor<T, P, E>
	implements TableVisitor<T, P, E>
{
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, P parameter) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, P parameter) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, P parameter) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, P parameter) throws E
	{
		return VISIT_SIBLINGS;
	}
}
