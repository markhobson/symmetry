/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ContainerTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static uk.co.iizuka.common.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitor;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.skipChildren;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

import java.util.Arrays;
import java.util.Collection;

import com.googlecode.jtype.Generic;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.test.FakeContainer;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;

/**
 * Tests {@code Container}.
 * 
 * @author Mark Hobson
 * @version $Id: ContainerTest.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see Container
 */
public class ContainerTest extends AbstractComponentTest<FakeContainer>
{
	// TODO: refactor to not reference FakeContainer everywhere
	
	// fields -----------------------------------------------------------------

	private FakeContainer container;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		container = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newContainer()
	{
		assertEmptyContainer(container);
	}
	
	@Test
	public void newContainerWithChildren()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		container = createContainer(child1, child2);
		
		assertContainer(container, child1, child2);
	}
	
	@Test
	public void acceptWithSubvisitorReturningVisitChildren()
	{
		final DummyComponent child = new DummyComponent();
		container.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(FakeContainer.class), container, parameter);
				will(returnValue(nullHierarchicalVisitor()));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), child, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, container.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningSkipChildren()
	{
		final DummyComponent child = new DummyComponent();
		container.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(FakeContainer.class), container, parameter);
				will(returnValue(skipChildren()));
		} });
		
		assertEquals(VISIT_SIBLINGS, container.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithNullSubvisitorVisitsChildren()
	{
		final DummyComponent child = new DummyComponent();
		container.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(FakeContainer.class), container, parameter);
				will(returnValue(null));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), child, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, container.accept(visitor, parameter));
	}
	
	@Test
	public void add()
	{
		Component child = new DummyComponent();
		
		container.add(child);
		
		assertContainer(container, child);
	}
	
	@Test
	public void addSetsParent()
	{
		Component child = new DummyComponent();
		
		container.add(child);
		
		assertEquals("parent", container, child.getParent());
	}
	
	@Test
	public void addFiresEvent()
	{
		Component child = new DummyComponent();
		container.addPropertyChangeListener(mockPropertyChangeListener(getMockery(), container,
			Container.COMPONENTS_PROPERTY, null, child));
		
		container.add(child);
	}
	
	@Test(expected = NullPointerException.class)
	public void addWithNull()
	{
		container.add((Component) null);
	}
	
	@Test
	public void addIndexAtStart()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		container.add(child2);
		
		container.add(0, child1);
		
		assertContainer(container, child1, child2);
	}
	
	@Test
	public void addIndexAtEnd()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		container.add(child1);
		
		container.add(1, child2);
		
		assertContainer(container, child1, child2);
	}
	
	@Test
	public void addIndexAtMiddle()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		Component child3 = new DummyComponent();
		container.add(child1);
		container.add(child3);
		
		container.add(1, child2);
		
		assertContainer(container, child1, child2, child3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void addIndexWithNegativeIndex()
	{
		container.add(-1, new DummyComponent());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void addIndexWithOutOfBoundsIndex()
	{
		container.add(1, new DummyComponent());
	}
	
	@Test(expected = NullPointerException.class)
	public void addIndexWithNullComponent()
	{
		container.add(0, null);
	}
	
	@Test
	public void addArray()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		container.add(child1, child2);
		
		assertContainer(container, child1, child2);
	}
	
	@Test(expected = NullPointerException.class)
	public void addArrayWithNull()
	{
		container.add((Component[]) null);
	}
	
	@Test
	public void addCollection()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		container.add(Arrays.asList(child1, child2));
		
		assertContainer(container, child1, child2);
	}
	
	@Test(expected = NullPointerException.class)
	public void addCollectionWithNull()
	{
		container.add((Collection<Component>) null);
	}
	
	@Test
	public void remove()
	{
		Component child = new DummyComponent();
		container.add(child);
		
		container.remove(child);
		
		assertEmptyContainer(container);
	}
	
	@Test
	public void removeFiresEvent()
	{
		Component child = new DummyComponent();
		container.add(child);
		container.addPropertyChangeListener(mockPropertyChangeListener(getMockery(), container,
			Container.COMPONENTS_PROPERTY, child, null));
		
		container.remove(child);
	}
	
	@Test(expected = NullPointerException.class)
	public void removeWithNull()
	{
		container.remove((Component) null);
	}

	@Test
	public void removeCollection()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		container.add(child1, child2);
		
		container.remove(Arrays.asList(child1, child2));
		
		assertEmptyContainer(container);
	}
	
	@Test
	public void get()
	{
		Component child = new DummyComponent();
		container.add(child);
		
		assertEquals("component", child, container.get(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getWithNegativeIndex()
	{
		container.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getWithOutOfBoundsIndex()
	{
		container.get(1);
	}

	@Test
	public void indexOf()
	{
		Component child = new DummyComponent();
		container.add(child);
		
		assertEquals(0, container.indexOf(child));
	}
	
	@Test
	public void indexOfWithUnknownComponent()
	{
		assertEquals(-1, container.indexOf(new DummyComponent()));
	}
	
	@Test(expected = NullPointerException.class)
	public void indexOfWithNull()
	{
		container.indexOf(null);
	}
	
	@Test
	public void getComponents()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		container.add(child1, child2);
		
		assertContainer(container, child1, child2);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void getComponentsIsUnmodifiable()
	{
		container.add(new DummyComponent());
		
		container.getComponents().remove(0);
	}
	
	@Test
	public void getComponentCount()
	{
		container.add(new DummyComponent());
		
		assertEquals(1, container.getComponentCount());
	}
	
	@Test
	public void toStringTest()
	{
		container = createContainer(new DummyComponent(), new DummyComponent());
		
		String expected = "uk.co.iizuka.kozo.ui.test.FakeContainer[components=[DummyComponent, DummyComponent]]";
		
		assertEquals(expected, container.toString());
	}

	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FakeContainer createComponent()
	{
		return createContainer();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<FakeContainer> getComponentType()
	{
		return Generic.get(FakeContainer.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static FakeContainer createContainer(Component... children)
	{
		return new FakeContainer(children);
	}
	
	private static void assertContainer(Container actual, Component... expectedComponents)
	{
		assertNotNull("container", actual);
		assertEquals("components", Arrays.asList(expectedComponents), actual.getComponents());
	}
	
	private static void assertEmptyContainer(Container actual)
	{
		assertContainer(actual);
	}
}
