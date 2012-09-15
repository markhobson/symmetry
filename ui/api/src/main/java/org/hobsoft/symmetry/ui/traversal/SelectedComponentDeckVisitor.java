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

import org.hobsoft.symmetry.ui.Deck;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
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
class SelectedComponentDeckVisitor<T extends Deck, P, E extends Exception> extends DelegatingContainerVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public SelectedComponentDeckVisitor(ContainerVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T deck, P parameter, int childIndex) throws E
	{
		// skip unselected components
		if (childIndex != deck.getSelectedIndex())
		{
			return SKIP_CHILDREN;
		}
		
		return super.visitChild(deck, parameter, childIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T deck, P parameter, int childIndex) throws E
	{
		// skip siblings after selected component
		if (childIndex >= deck.getSelectedIndex())
		{
			return SKIP_SIBLINGS;
		}
		
		return super.endVisitChild(deck, parameter, childIndex);
	}
}
