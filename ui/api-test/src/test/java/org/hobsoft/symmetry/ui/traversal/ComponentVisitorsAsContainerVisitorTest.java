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

import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.test.FakeContainer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createContainerVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors#asContainerVisitor(HierarchicalComponentVisitor)}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors#asContainerVisitor(HierarchicalComponentVisitor)
 */
@RunWith(JMock.class)
public class ComponentVisitorsAsContainerVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void asContainerVisitorVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Container, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(container, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visit(container, "x"));
	}
	
	@Test
	public void asContainerVisitorVisitWithContainerVisitor()
	{
		final ContainerVisitor<Container, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(container, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visit(container, "x"));
	}
	
	@Test
	public void asContainerVisitorVisitWithNull()
	{
		ContainerVisitor<Container, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visit(new FakeContainer(), "x"));
	}
	
	@Test
	public void asContainerVisitorEndVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<Container, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(container, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisit(container, "x"));
	}
	
	@Test
	public void asContainerVisitorEndVisitWithContainerVisitor()
	{
		final ContainerVisitor<Container, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(container, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisit(container, "x"));
	}
	
	@Test
	public void asContainerVisitorEndVisitWithNull()
	{
		ContainerVisitor<Container, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisit(new FakeContainer(), "x"));
	}
	
	@Test
	public void asContainerVisitorVisitChildWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Container, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visitChild(new FakeContainer(), "x", 1));
	}
	
	@Test
	public void asContainerVisitorVisitChildWithContainerVisitor()
	{
		final ContainerVisitor<Container, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitChild(container, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visitChild(container, "x", 1));
	}
	
	@Test
	public void asContainerVisitorVisitChildWithContainerVisitorWithNull()
	{
		ContainerVisitor<Container, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asContainerVisitor(visitor).visitChild(new FakeContainer(), "x", 1));
	}
	
	@Test
	public void asContainerVisitorEndVisitChildWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<Container, String, RuntimeException> visitor = createHierarchicalVisitor(mockery);
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisitChild(new FakeContainer(), "x", 1));
	}
	
	@Test
	public void asContainerVisitorEndVisitChildWithContainerVisitor()
	{
		final ContainerVisitor<Container, String, RuntimeException> visitor = createContainerVisitor(mockery);
		final Container container = new FakeContainer();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitChild(container, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisitChild(container, "x", 1));
	}
	
	@Test
	public void asContainerVisitorEndVisitChildWithNull()
	{
		ContainerVisitor<Container, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asContainerVisitor(visitor).endVisitChild(new FakeContainer(), "x", 1));
	}
}
