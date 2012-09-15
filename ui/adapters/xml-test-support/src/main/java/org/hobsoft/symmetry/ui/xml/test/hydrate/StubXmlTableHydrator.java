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
package org.hobsoft.symmetry.ui.xml.test.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.traversal.TableVisitor;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
class StubXmlTableHydrator<T extends Table>
	extends StubXmlHierarchicalComponentHydrator<T>
	implements TableVisitor<T, HydrationContext, HydrationException>
{
	// fields -----------------------------------------------------------------
	
	private final boolean stubCells;
	
	// constructors -----------------------------------------------------------
	
	public StubXmlTableHydrator(String text, boolean stubCells)
	{
		super(text);
		
		this.stubCells = stubCells;
	}
	
	// TableVisitor methods ---------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + ".header]", context);

		return stubCells ? VISIT_CHILDREN : SKIP_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		write("[" + getText() + ".headerCell]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		write("[/" + getText() + ".headerCell]", context);

		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + ".header]", context);
		
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + ".body]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		write("[" + getText() + ".row]", context);

		return stubCells ? VISIT_CHILDREN : SKIP_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, HydrationContext context, int rowIndex, int columnIndex) throws HydrationException
	{
		write("[" + getText() + ".cell]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, HydrationContext context, int rowIndex, int columnIndex)
		throws HydrationException
	{
		write("[/" + getText() + ".cell]", context);
		
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		write("[/" + getText() + ".row]", context);

		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + ".body]", context);

		return VISIT_SIBLINGS;
	}
}
