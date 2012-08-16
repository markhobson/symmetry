/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/VisibleNodeTreeVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertEquals;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.DefaultTreeNode;
import uk.co.iizuka.kozo.ui.model.TreeNode;
import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * Tests {@code VisibleNodeTreeVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: VisibleNodeTreeVisitorTest.java 100649 2012-04-23 10:05:30Z mark@IIZUKA.CO.UK $
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
