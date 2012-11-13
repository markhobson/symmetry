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

import java.util.Arrays;

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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code TabBox}.
 * 
 * @author Mark Hobson
 * @see TabBox
 */
public class TabBoxTest extends AbstractComponentTest<TabBox>
{
	// fields -----------------------------------------------------------------
	
	private TabBox tabBox;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		tabBox = new TabBox();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTabBox()
	{
		assertTabBox(tabBox, -1);
	}
	
	@Test
	public void newTabBoxWithTab()
	{
		Tab tab = new Tab();
		tabBox = new TabBox(tab);
		
		assertTabBox(tabBox, 0, tab);
	}
	
	@Test
	public void newTabBoxWithTabSelectsTabWhenClicked()
	{
		Tab tab = new Tab();
		tabBox = new TabBox(new Tab(), tab);
		
		tab.doClick();
		
		assertEquals(1, tabBox.getSelectedIndex());
	}
	
	@Test
	public void newTabBoxWithTabs()
	{
		Tab tab1 = new Tab();
		Tab tab2 = new Tab();
		tabBox = new TabBox(tab1, tab2);
		
		assertTabBox(tabBox, 0, tab1, tab2);
	}
	
	@Test
	public void acceptWithSubvisitorReturningVisitChildren()
	{
		final Tab tab = new Tab();
		tabBox.add(tab);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(TabBox.class), tabBox, parameter);
				will(returnValue(nullHierarchicalVisitor()));
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tabBox.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningSkipChildren()
	{
		final Tab tab = new Tab();
		tabBox.add(tab);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(TabBox.class), tabBox, parameter); will(returnValue(skipChildren()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tabBox.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithNullSubvisitorVisitsChildren()
	{
		final Tab tab = new Tab();
		tabBox.add(tab);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(TypeToken.of(TabBox.class), tabBox, parameter);
				will(returnValue(null));
			oneOf(visitor).visit(TypeToken.of(Tab.class), tab, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, tabBox.accept(visitor, parameter));
	}
	
	@Test
	public void addWithTab()
	{
		Tab tab = new Tab();
		tabBox.add(tab);
		
		assertTabs(tabBox, tab);
	}
	
	@Test
	public void addWithFirstTabSetsSelectedIndex()
	{
		tabBox.add(new Tab());
		
		assertEquals(0, tabBox.getSelectedIndex());
	}
	
	@Test
	public void addSelectsTabWhenClicked()
	{
		Tab tab = new Tab();
		tabBox.add(new Tab());
		tabBox.add(tab);
		
		tab.doClick();
		
		assertEquals(1, tabBox.getSelectedIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addWithComponent()
	{
		tabBox.add(new DummyComponent());
	}
	
	@Test(expected = NullPointerException.class)
	public void addWithNull()
	{
		tabBox.add((Component) null);
	}

	@Test
	public void removeWithTab()
	{
		Tab tab = new Tab();
		tabBox = new TabBox(tab);
		
		tabBox.remove(tab);
		
		assertNoTabs(tabBox);
	}
	
	@Test
	public void removeWithLastTabUnsetsSelectedIndex()
	{
		Tab tab = new Tab();
		tabBox = new TabBox(tab);
		
		tabBox.remove(tab);
		
		assertEquals(-1, tabBox.getSelectedIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeWithComponent()
	{
		tabBox.remove(new DummyComponent());
	}
	
	@Test(expected = NullPointerException.class)
	public void removeWithNull()
	{
		tabBox.remove((Component) null);
	}

	@Test
	public void setSelectedIndex()
	{
		tabBox = new TabBox(new Tab(), new Tab());
		
		tabBox.setSelectedIndex(1);
		
		assertEquals(1, tabBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexWithUnselectedWhenEmpty()
	{
		tabBox.setSelectedIndex(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedIndexWithUnselectedWhenNotEmpty()
	{
		tabBox = new TabBox(new Tab());
		
		tabBox.setSelectedIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithNegative()
	{
		tabBox.setSelectedIndex(-2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithOutOfBounds()
	{
		tabBox = new TabBox(new Tab());
		
		tabBox.setSelectedIndex(1);
	}
	
	@Test
	public void getSelectedComponentWhenEmpty()
	{
		assertNull(tabBox.getSelectedComponent());
	}
	
	@Test
	public void getSelectedComponentWhenNotEmpty()
	{
		Tab tab = new Tab();
		tabBox = new TabBox(tab);
		
		assertSame(tab, tabBox.getSelectedComponent());
	}

	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TabBox createComponent()
	{
		return new TabBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<TabBox> getComponentType()
	{
		return TypeToken.of(TabBox.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertTabBox(TabBox actual, int expectedSelectedIndex, Tab... expectedTabs)
	{
		assertNotNull("tabBox", actual);
		assertTabs(actual, expectedTabs);
		assertEquals("tabs", Arrays.asList(expectedTabs), actual.getTabs());
		assertEquals("selectedIndex", expectedSelectedIndex, actual.getSelectedIndex());
	}
	
	private static void assertTabs(TabBox actual, Tab... expectedTabs)
	{
		assertEquals("tabs", Arrays.asList(expectedTabs), actual.getTabs());
	}
	
	private static void assertNoTabs(TabBox actual)
	{
		assertTabs(actual);
	}
}
