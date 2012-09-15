/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/VisibleNodeTreeVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.DefaultTreeNode;
import org.hobsoft.symmetry.ui.model.TreeNode;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code VisibleNodeTreeVisitor}.
 * 
 * @author Mark Hobson
 * @see VisibleNodeTreeVisitor
 */
public class VisibleNodeTreeVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private VisibleNodeTreeVisitor<Tree, Void, RuntimeException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new VisibleNodeTreeVisitor<Tree, Void, RuntimeException>(
			new NullTreeVisitor<Tree, Void, RuntimeException>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void visitNodeChildrenWhenNodeCollapsed()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		
		assertEquals(SKIP_CHILDREN, visitor.visitNodeChildren(tree, null, rootPath));
	}
	
	@Test
	public void visitNodeChildrenWhenNodeExpanded()
	{
		Tree tree = createTreeWithRootNode();
		TreePath rootPath = new TreePath(tree.getModel().getRoot());
		tree.setExpandedPaths(new TreePath[] {rootPath});
		
		assertEquals(VISIT_CHILDREN, visitor.visitNodeChildren(tree, null, rootPath));
	}
	
	// private methods --------------------------------------------------------
	
	private static Tree createTreeWithRootNode()
	{
		TreeNode root = new DefaultTreeNode();
		return new Tree(root);
	}
}
