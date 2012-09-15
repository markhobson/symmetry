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
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.Tree;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.visibleNodes;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.visibleRows;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class VisibleComponentVisitor<P, E extends Exception> extends DelegatingComponentVisitor<P, E>
{
	// TODO: should we consider component visibility?
	// TODO: should this visitor handle all these cases or be broken into smaller parts?
	
	// constructors -----------------------------------------------------------
	
	public VisibleComponentVisitor(ComponentVisitor<P, E> delegate)
	{
		super(delegate);
	}
	
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = super.visit(componentType, component, parameter);
		
		// TODO: this isn't pretty
		
		if (Generic.get(Deck.class).equals(componentType))
		{
			HierarchicalComponentVisitor<Deck, P, E> deckVisitor =
				(HierarchicalComponentVisitor<Deck, P, E>) subvisitor;
			
			deckVisitor = ComponentVisitors.selectedComponent(asContainerVisitor(deckVisitor));
			
			subvisitor = (HierarchicalComponentVisitor<T, P, E>) deckVisitor;
		}
		else if (Generic.get(Table.class).equals(componentType))
		{
			HierarchicalComponentVisitor<Table, P, E> tableVisitor =
				(HierarchicalComponentVisitor<Table, P, E>) subvisitor;
			
			tableVisitor = visibleRows(asTableVisitor(tableVisitor));
			
			subvisitor = (HierarchicalComponentVisitor<T, P, E>) tableVisitor;
		}
		else if (Generic.get(Tree.class).equals(componentType))
		{
			HierarchicalComponentVisitor<Tree, P, E> treeVisitor =
				(HierarchicalComponentVisitor<Tree, P, E>) subvisitor;
			
			treeVisitor = visibleNodes(asTreeVisitor(treeVisitor));
			
			subvisitor = (HierarchicalComponentVisitor<T, P, E>) treeVisitor;
		}
		
		return subvisitor;
	}
}
