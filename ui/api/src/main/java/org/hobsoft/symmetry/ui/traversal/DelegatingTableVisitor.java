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
public abstract class DelegatingTableVisitor<T extends Table, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TableVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingTableVisitor(TableVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).visitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return getDelegate(table, parameter).visitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return getDelegate(table, parameter).endVisitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).endVisitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).visitBody(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		return getDelegate(table, parameter).visitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return getDelegate(table, parameter).visitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return getDelegate(table, parameter).endVisitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		return getDelegate(table, parameter).endVisitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).endVisitBody(table, parameter);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableVisitor<? super T, P, E> getDelegate(T table, P parameter)
	{
		return (TableVisitor<? super T, P, E>) super.getDelegate(table, parameter);
	}
}
