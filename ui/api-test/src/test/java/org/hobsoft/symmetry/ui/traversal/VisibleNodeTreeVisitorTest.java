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
