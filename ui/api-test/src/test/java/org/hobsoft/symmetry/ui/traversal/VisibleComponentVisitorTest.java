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
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.model.DefaultTreeNode;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code VisibleComponentVisitor}.
 * 
 * @author Mark Hobson
 * @see VisibleComponentVisitor
 */
public class VisibleComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private VisibleComponentVisitor<Void, RuntimeException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new VisibleComponentVisitor<Void, RuntimeException>(
			new NullComponentVisitor<Void, RuntimeException>());
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitComponentVisit()
	{
		DummyComponent component = new DummyComponent();
		
		HierarchicalComponentVisitor<DummyComponent, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(DummyComponent.class), component, null);
		
		assertEquals(VISIT_CHILDREN, subvisitor.visit(component, null));
	}
	
	@Test
	public void visitDeckVisitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Deck.class), deck, null);
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(subvisitor).visitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckVisitChildWhenUnselected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Deck.class), deck, null);
		
		assertEquals(SKIP_CHILDREN, asContainerVisitor(subvisitor).visitChild(deck, null, 1));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Deck.class), deck, null);
		
		assertEquals(SKIP_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenBeforeSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		deck.setSelectedIndex(1);
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Deck.class), deck, null);
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenAfterSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Deck.class), deck, null);
		
		assertEquals(SKIP_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 1));
	}
	
	@Test
	public void visitTableVisitRowWhenVisible()
	{
		Table table = createTableWithRows(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Table.class), table, null);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 0));
	}
	
	@Test
	public void visitTableVisitRowWhenBeforeVisible()
	{
		Table table = createTableWithRows(2);
		table.setFirstVisibleRowIndex(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Table.class), table, null);
		
		assertEquals(SKIP_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 0));
	}
	
	@Test
	public void visitTableVisitRowWhenAfterVisible()
	{
		Table table = createTableWithRows(2);
		table.setVisibleRowCount(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Table.class), table, null);
		
		assertEquals(SKIP_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 1));
	}
	
	@Test
	public void visitTreeVisitNodeChildrenWhenExpanded()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		tree.setExpandedPaths(new TreePath[] {rootPath});
		
		HierarchicalComponentVisitor<Tree, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Tree.class), tree, null);
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(subvisitor).visitNodeChildren(tree, null, rootPath));
	}
	
	@Test
	public void visitTreeVisitNodeChildrenWhenCollapsed()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		
		HierarchicalComponentVisitor<Tree, Void, RuntimeException> subvisitor = visitor
			.visit(TypeToken.of(Tree.class), tree, null);
		
		assertEquals(SKIP_CHILDREN, asTreeVisitor(subvisitor).visitNodeChildren(tree, null, rootPath));
	}
	
	// private methods --------------------------------------------------------
	
	private static Table createTableWithRows(int rows)
	{
		DefaultTableModel model = new DefaultTableModel();
		
		for (int i = 0; i < rows; i++)
		{
			model.addRow("a");
		}
		
		return new Table(model);
	}

	private static Tree createTreeWithRootNode()
	{
		return new Tree(new DefaultTreeNode());
	}
}
