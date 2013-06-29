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

import org.hobsoft.symmetry.ui.Table;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createTableVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors#asTableVisitor(HierarchicalComponentVisitor)}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors#asTableVisitor(HierarchicalComponentVisitor)
 */
@RunWith(JMock.class)
public class ComponentVisitorsAsTableVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void asTableVisitorVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(table, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visit(table, "x"));
	}
	
	@Test
	public void asTableVisitorVisitWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(table, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visit(table, "x"));
	}
	
	@Test
	public void asTableVisitorVisitWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visit(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(table, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisit(table, "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(table, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisit(table, "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisit(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitHeaderWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeader(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitHeaderWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitHeader(table, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeader(table, "x"));
	}
	
	@Test
	public void asTableVisitorVisitHeaderWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeader(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitHeaderCellWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeaderCell(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorVisitHeaderCellWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitHeaderCell(table, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeaderCell(table, "x", 1));
	}
	
	@Test
	public void asTableVisitorVisitHeaderCellWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitHeaderCell(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderCellWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeaderCell(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderCellWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitHeaderCell(table, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeaderCell(table, "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderCellWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeaderCell(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeader(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitHeader(table, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeader(table, "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitHeaderWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitHeader(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitBodyWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitBody(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitBodyWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitBody(table, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitBody(table, "x"));
	}
	
	@Test
	public void asTableVisitorVisitBodyWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitBody(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorVisitRowWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitRow(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorVisitRowWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitRow(table, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitRow(table, "x", 1));
	}
	
	@Test
	public void asTableVisitorVisitRowWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitRow(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorVisitCellWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitCell(new Table(), "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorVisitCellWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitCell(table, "x", 1, 2); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitCell(table, "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorVisitCellWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asTableVisitor(visitor).visitCell(new Table(), "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorEndVisitCellWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitCell(new Table(), "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorEndVisitCellWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitCell(table, "x", 1, 2); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitCell(table, "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorEndVisitCellWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitCell(new Table(), "x", 1, 2));
	}
	
	@Test
	public void asTableVisitorEndVisitRowWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitRow(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitRowWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitRow(table, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitRow(table, "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitRowWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitRow(new Table(), "x", 1));
	}
	
	@Test
	public void asTableVisitorEndVisitBodyWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Table, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitBody(new Table(), "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitBodyWithTableVisitor()
	{
		final TableVisitor<Table, String, RuntimeException> visitor = createTableVisitor(mockery);
		final Table table = new Table();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitBody(table, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitBody(table, "x"));
	}
	
	@Test
	public void asTableVisitorEndVisitBodyWithNull()
	{
		TableVisitor<Table, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asTableVisitor(visitor).endVisitBody(new Table(), "x"));
	}
}
