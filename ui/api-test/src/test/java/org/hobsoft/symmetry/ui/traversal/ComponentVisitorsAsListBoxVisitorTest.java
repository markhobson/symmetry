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

import org.hobsoft.symmetry.ui.ComboBox;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createListBoxVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asListBoxVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors#asListBoxVisitor(HierarchicalComponentVisitor)}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors#asListBoxVisitor(HierarchicalComponentVisitor)
 */
@RunWith(JMock.class)
public class ComponentVisitorsAsListBoxVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void asListBoxVisitorVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<ComboBox<?>, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(comboBox, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visit(comboBox, "x"));
	}
	
	@Test
	public void asListBoxVisitorVisitWithListBoxVisitor()
	{
		final ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = createListBoxVisitor(mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(comboBox, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visit(comboBox, "x"));
	}
	
	@Test
	public void asListBoxVisitorVisitWithNull()
	{
		ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visit(new ComboBox<Object>(), "x"));
	}
	
	@Test
	public void asListBoxVisitorEndVisitWithHierarchicalComponentVisitor()
	{
		final HierarchicalComponentVisitor<ComboBox<?>, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(comboBox, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisit(comboBox, "x"));
	}
	
	@Test
	public void asListBoxVisitorEndVisitWithListBoxVisitor()
	{
		final ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = createListBoxVisitor(mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisit(comboBox, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisit(comboBox, "x"));
	}
	
	@Test
	public void asListBoxVisitorEndVisitWithNull()
	{
		ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisit(new ComboBox<Object>(), "x"));
	}
	
	@Test
	public void asListBoxVisitorVisitItemWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<ComboBox<?>, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visitItem(new ComboBox<Object>(), "x", 1));
	}
	
	@Test
	public void asListBoxVisitorVisitItemWithListBoxVisitor()
	{
		final ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = createListBoxVisitor(mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visitItem(comboBox, "x", 1); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visitItem(comboBox, "x", 1));
	}
	
	@Test
	public void asListBoxVisitorVisitItemWithNull()
	{
		ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_CHILDREN, asListBoxVisitor(visitor).visitItem(new ComboBox<Object>(), "x", 1));
	}
	
	@Test
	public void asListBoxVisitorEndVisitItemWithHierarchicalComponentVisitor()
	{
		HierarchicalComponentVisitor<ComboBox<?>, String, RuntimeException> visitor = createHierarchicalVisitor(
			mockery);
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisitItem(new ComboBox<Object>(), "x", 1));
	}
	
	@Test
	public void asListBoxVisitorEndVisitItemWithListBoxVisitor()
	{
		final ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = createListBoxVisitor(mockery);
		final ComboBox<?> comboBox = new ComboBox<Object>();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).endVisitItem(comboBox, "x", 1); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisitItem(comboBox, "x", 1));
	}
	
	@Test
	public void asListBoxVisitorEndVisitItemWithNull()
	{
		ListBoxVisitor<ComboBox<?>, String, RuntimeException> visitor = null;
		
		assertEquals(VISIT_SIBLINGS, asListBoxVisitor(visitor).endVisitItem(new ComboBox<Object>(), "x", 1));
	}
}
