/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/TabTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitor;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.skipChildren;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

import com.googlecode.jtype.Generic;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;

/**
 * Tests {@code Tab}.
 * 
 * @author Mark Hobson
 * @version $Id: TabTest.java 95624 2011-11-29 11:53:24Z mark@IIZUKA.CO.UK $
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
			oneOf(visitor).visit(Generic.get(Tab.class), tab, parameter);
				will(returnValue(nullHierarchicalVisitor()));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), component, parameter);
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
			oneOf(visitor).visit(Generic.get(Tab.class), tab, parameter); will(returnValue(nullHierarchicalVisitor()));
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
			oneOf(visitor).visit(Generic.get(Tab.class), tab, parameter); will(returnValue(skipChildren()));
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
			oneOf(visitor).visit(Generic.get(Tab.class), tab, parameter);
				will(returnValue(null));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), component, parameter);
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
	protected Generic<Tab> getComponentType()
	{
		return Generic.get(Tab.class);
	}
}
