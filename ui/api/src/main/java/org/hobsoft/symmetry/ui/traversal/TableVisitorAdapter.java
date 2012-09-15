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
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code TableVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class TableVisitorAdapter<T extends Table, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TableVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final TableVisitor<? super T, P, E> tableDelegate;

	// constructors -----------------------------------------------------------
	
	public TableVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullTableVisitor());
	}
	
	public TableVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		TableVisitor<? super T, P, E> tableDelegate)
	{
		super(delegate);
		
		this.tableDelegate = tableDelegate;
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, P parameter) throws E
	{
		return tableDelegate.visitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return tableDelegate.visitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return tableDelegate.endVisitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, P parameter) throws E
	{
		return tableDelegate.endVisitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, P parameter) throws E
	{
		return tableDelegate.visitBody(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		return tableDelegate.visitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return tableDelegate.visitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return tableDelegate.endVisitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		return tableDelegate.endVisitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, P parameter) throws E
	{
		return tableDelegate.endVisitBody(table, parameter);
	}
}
