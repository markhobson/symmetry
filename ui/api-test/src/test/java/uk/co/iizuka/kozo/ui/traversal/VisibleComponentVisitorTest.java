/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/VisibleComponentVisitorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertEquals;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asContainerVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asTableVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asTreeVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import com.googlecode.jtype.Generic;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.DefaultTableModel;
import uk.co.iizuka.kozo.ui.model.DefaultTreeNode;
import uk.co.iizuka.kozo.ui.model.TreePath;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code VisibleComponentVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: VisibleComponentVisitorTest.java 100651 2012-04-23 10:18:53Z mark@IIZUKA.CO.UK $
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
			.visit(Generic.get(DummyComponent.class), component, null);
		
		assertEquals(VISIT_CHILDREN, subvisitor.visit(component, null));
	}
	
	@Test
	public void visitDeckVisitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Deck.class), deck, null);
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(subvisitor).visitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckVisitChildWhenUnselected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Deck.class), deck, null);
		
		assertEquals(SKIP_CHILDREN, asContainerVisitor(subvisitor).visitChild(deck, null, 1));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Deck.class), deck, null);
		
		assertEquals(SKIP_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenBeforeSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		deck.setSelectedIndex(1);
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Deck.class), deck, null);
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 0));
	}
	
	@Test
	public void visitDeckEndVisitChildWhenAfterSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		HierarchicalComponentVisitor<Deck, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Deck.class), deck, null);
		
		assertEquals(SKIP_SIBLINGS, asContainerVisitor(subvisitor).endVisitChild(deck, null, 1));
	}
	
	@Test
	public void visitTableVisitRowWhenVisible()
	{
		Table table = createTableWithRows(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Table.class), table, null);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 0));
	}
	
	@Test
	public void visitTableVisitRowWhenBeforeVisible()
	{
		Table table = createTableWithRows(2);
		table.setFirstVisibleRowIndex(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Table.class), table, null);
		
		assertEquals(SKIP_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 0));
	}
	
	@Test
	public void visitTableVisitRowWhenAfterVisible()
	{
		Table table = createTableWithRows(2);
		table.setVisibleRowCount(1);
		
		HierarchicalComponentVisitor<Table, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Table.class), table, null);
		
		assertEquals(SKIP_CHILDREN, asTableVisitor(subvisitor).visitRow(table, null, 1));
	}
	
	@Test
	public void visitTreeVisitNodeChildrenWhenExpanded()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		tree.setExpandedPaths(new TreePath[] {rootPath});
		
		HierarchicalComponentVisitor<Tree, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Tree.class), tree, null);
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(subvisitor).visitNodeChildren(tree, null, rootPath));
	}
	
	@Test
	public void visitTreeVisitNodeChildrenWhenCollapsed()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		
		HierarchicalComponentVisitor<Tree, Void, RuntimeException> subvisitor = visitor
			.visit(Generic.get(Tree.class), tree, null);
		
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
