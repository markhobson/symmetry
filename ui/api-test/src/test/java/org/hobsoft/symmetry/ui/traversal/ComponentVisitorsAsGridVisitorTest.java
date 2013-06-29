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

import org.hobsoft.symmetry.ui.Grid;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createContainerVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createGridVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asGridVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors#asGridVisitor(HierarchicalComponentVisitor)}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors#asGridVisitor(HierarchicalComponentVisitor)
 */
@RunWith(JMock.class)
public class ComponentVisitorsAsGridVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void asGridVisitorVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(grid, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorVisitWithContainerVisitor()
	{
		final ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(grid, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorVisitWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(grid, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorVisitWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visit(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(grid, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitWithContainerVisitor()
	{
		final ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(grid, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(grid, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisit(grid, "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisit(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitChildWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitChild(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitChildWithContainerVisitor()
	{
		final ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitChild(grid, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitChild(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitChildWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitChild(grid, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitChild(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitChildWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitChild(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitChildWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitChild(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitChildWithContainerVisitor()
	{
		final ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitChild(grid, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitChild(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitChildWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitChild(grid, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitChild(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitChildWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitChild(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitColumnsWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitColumnsWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitColumnsWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitColumns(grid, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitColumns(grid, "x"));
	}
	
	@Test
	public void asGridVisitorVisitColumnsWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitColumnWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).visitColumn(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitColumnWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).visitColumn(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitColumnWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitColumn(grid, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).visitColumn(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitColumnWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).visitColumn(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitColumnsWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitColumnsWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitColumnsWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitColumns(grid, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitColumns(grid, "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitColumnsWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitColumns(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitRowsWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRows(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitRowsWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRows(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitRowsWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitRows(grid, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRows(grid, "x"));
	}
	
	@Test
	public void asGridVisitorVisitRowsWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRows(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorVisitRowWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitRowWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitRowWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitRow(grid, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRow(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorVisitRowWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asGridVisitor(visitor).visitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitRowWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitRowWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitRowWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitRow(grid, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRow(grid, "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitRowWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRow(new Grid(), "x", 1));
	}
	
	@Test
	public void asGridVisitorEndVisitRowsWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Grid, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRows(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitRowsWithContainerVisitor()
	{
		ContainerVisitor<Grid, String, RuntimeException> visitor = createContainerVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRows(new Grid(), "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitRowsWithGridVisitor()
	{
		final GridVisitor<Grid, String, RuntimeException> visitor = createGridVisitor(mockery);
		final Grid grid = new Grid();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitRows(grid, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRows(grid, "x"));
	}
	
	@Test
	public void asGridVisitorEndVisitRowsWithNull()
	{
		GridVisitor<Grid, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asGridVisitor(visitor).endVisitRows(new Grid(), "x"));
	}
}
