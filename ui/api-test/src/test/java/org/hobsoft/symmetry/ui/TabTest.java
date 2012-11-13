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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipChildren;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code Tab}.
 * 
 * @author Mark Hobson
 * @see Tab
 */
public class TabTest extends AbstractComponentTest<Tab>
{
	// fields -----------------------------------------------------------------
	
	private Tab tab;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		tab = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTab()
	{
		assertNull("Component", tab.getComponent());
	}
	
	@Test
	public void newTabWithComponent()
	{
		DummyComponent component = new DummyComponent();
		tab = new Tab(component);
		
		assertEquals("Component", component, tab.getComponent());
	}
	
	@Test
	public void newTabWithTextAndComponent()
	{
		DummyComponent component = new DummyComponent();
		tab = new Tab("a", component);
		
		assertEquals("Text", "a", tab.getText());
		assertEquals("Component", component, tab.getComponent());
	}
	
	@Test
	public void acceptWithSubvisitorReturningVisitChildren()
	{
		final DummyComponent component = new DummyComponent();
		tab = new Tab(component);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter);
				will(returnValue(nullHierarchicalVisitor()));
			oneOf(visitor).visit(TypeToken.of(DummyComponent.class), component, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tab.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningVisitChildrenAndNullComponent()
	{
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		final Object parameter = createVisitorParameter();
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter); will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tab.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningSkipChildren()
	{
		final DummyComponent component = new DummyComponent();
		tab = new Tab(component);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter); will(returnValue(skipChildren()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tab.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithNullSubvisitorVisitsChildren()
	{
		final DummyComponent component = new DummyComponent();
		tab = new Tab(component);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter);
				will(returnValue(null));
			oneOf(visitor).visit(TypeToken.of(DummyComponent.class), component, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tab.accept(visitor, parameter));
	}
	
	@Test
	public void setComponentWithComponent()
	{
		DummyComponent component = new DummyComponent();
		tab.setComponent(component);
		assertEquals(component, tab.getComponent());
	}
	
	@Test
	public void setComponentWithNull()
	{
		tab.setComponent(null);
		assertNull(tab.getComponent());
	}
	
	@Test
	public void setComponentSetsParent()
	{
		DummyComponent component = new DummyComponent();
		tab.setComponent(component);
		assertEquals(tab, component.getParent());
	}
	
	@Test
	public void setComponentUnsetsParent()
	{
		DummyComponent component = new DummyComponent();
		tab.setComponent(component);
		
		tab.setComponent(new DummyComponent());
		assertNull(component.getParent());
	}
	
	@Test
	public void setComponentWithNullUnsetsParent()
	{
		DummyComponent component = new DummyComponent();
		tab.setComponent(component);
		
		tab.setComponent(null);
		assertNull(component.getParent());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Tab createComponent()
	{
		return new Tab();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<Tab> getComponentType()
	{
		return TypeToken.of(Tab.class);
	}
}
