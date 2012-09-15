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
class VisibleRowTableVisitor<T extends Table, P, E extends Exception> extends DelegatingTableVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public VisibleRowTableVisitor(TableVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		if (!isRowVisible(table, rowIndex))
		{
			return SKIP_CHILDREN;
		}
		
		return super.visitRow(table, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		// TODO: skip siblings once last visible row encountered
		
		if (!isRowVisible(table, rowIndex))
		{
			return VISIT_SIBLINGS;
		}

		return super.endVisitRow(table, parameter, rowIndex);
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean isRowVisible(Table table, int row)
	{
		int pageSize = table.getVisibleRowCount();
		
		if (pageSize == 0)
		{
			return true;
		}
		
		int firstRow = table.getFirstVisibleRowIndex();
		int lastRow = firstRow + pageSize - 1;
		
		return (row >= firstRow && row <= lastRow);
	}
}
