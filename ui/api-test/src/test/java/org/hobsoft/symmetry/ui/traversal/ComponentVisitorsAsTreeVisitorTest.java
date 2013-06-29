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
import org.hobsoft.symmetry.ui.model.TreePath;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createTreeVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors#asTreeVisitor(HierarchicalComponentVisitor)}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors#asTreeVisitor(HierarchicalComponentVisitor)
 */
@RunWith(JMock.class)
public class ComponentVisitorsAsTreeVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void asTreeVisitorVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		final Tree tree = new Tree();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(tree, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visit(tree, "x"));
	}
	
	@Test
	public void asTreeVisitorVisitWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(tree, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visit(tree, "x"));
	}
	
	@Test
	public void asTreeVisitorVisitWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visit(new Tree(), "x"));
	}
	
	@Test
	public void asTreeVisitorEndVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		final Tree tree = new Tree();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(tree, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisit(tree, "x"));
	}
	
	@Test
	public void asTreeVisitorEndVisitWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(tree, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisit(tree, "x"));
	}
	
	@Test
	public void asTreeVisitorEndVisitWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisit(new Tree(), "x"));
	}
	
	@Test
	public void asTreeVisitorVisitNodeWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNode(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorVisitNodeWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		final TreePath path = new TreePath("y");
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitNode(tree, "x", path); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNode(tree, "x", path));
	}
	
	@Test
	public void asTreeVisitorVisitNodeWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNode(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorVisitNodeChildrenWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNodeChildren(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorVisitNodeChildrenWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		final TreePath path = new TreePath("y");
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitNodeChildren(tree, "x", path); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNodeChildren(tree, "x", path));
	}
	
	@Test
	public void asTreeVisitorVisitNodeChildrenWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTreeVisitor(visitor).visitNodeChildren(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeChildrenWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNodeChildren(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeChildrenWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		final TreePath path = new TreePath("y");
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitNodeChildren(tree, "x", path); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNodeChildren(tree, "x", path));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeChildrenWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNodeChildren(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Tree, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNode(new Tree(), "x", new TreePath("y")));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeWithTreeVisitor()
	{
		final TreeVisitor<Tree, String, RuntimeException> visitor = createTreeVisitor(mockery);
		final Tree tree = new Tree();
		final TreePath path = new TreePath("y");
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitNode(tree, "x", path); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNode(tree, "x", path));
	}
	
	@Test
	public void asTreeVisitorEndVisitNodeWithNull()
	{
		TreeVisitor<Tree, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTreeVisitor(visitor).endVisitNode(new Tree(), "x", new TreePath("y")));
	}
}
