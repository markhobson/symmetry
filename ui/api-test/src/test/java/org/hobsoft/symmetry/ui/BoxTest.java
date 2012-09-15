/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/BoxTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipChildren;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.layout.BoxLayoutData;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code Box}.
 * 
 * @author Mark Hobson
 * @see Box
 */
public class BoxTest extends AbstractComponentTest<Box>
{
	// fields -----------------------------------------------------------------

	private Box box;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		box = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newBox()
	{
		assertBox(box, Orientation.VERTICAL);
	}
	
	@Test
	public void newBoxWithOrientation()
	{
		box = new Box(Orientation.HORIZONTAL);
		
		assertBox(box, Orientation.HORIZONTAL);
	}
	
	@Test
	public void newBoxWithChildren()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		box = new Box(child1, child2);
		
		assertBox(box, Orientation.VERTICAL, child1, child2);
	}
	
	@Test
	public void newBoxWithOrientationAndChildren()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		box = new Box(Orientation.HORIZONTAL, child1, child2);
		
		assertBox(box, Orientation.HORIZONTAL, child1, child2);
	}
	
	@Test
	public void acceptWithSubvisitorReturningVisitChildren()
	{
		final DummyComponent child = new DummyComponent();
		box.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(Box.class), box, parameter);
				will(returnValue(nullHierarchicalVisitor()));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), child, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, box.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningSkipChildren()
	{
		final DummyComponent child = new DummyComponent();
		box.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(Box.class), box, parameter); will(returnValue(skipChildren()));
		} });
		
		assertEquals(VISIT_SIBLINGS, box.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithNullSubvisitorVisitsChildren()
	{
		final DummyComponent child = new DummyComponent();
		box.add(child);
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(getMockery());
		
		getMockery().checking(new Expectations() { {
			oneOf(visitor).visit(Generic.get(Box.class), box, parameter); will(returnValue(null));
			oneOf(visitor).visit(Generic.get(DummyComponent.class), child, parameter);
				will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, box.accept(visitor, parameter));
	}
	
	@Test
	public void setOrientation()
	{
		box.setOrientation(Orientation.HORIZONTAL);
		
		assertEquals("orientation", Orientation.HORIZONTAL, box.getOrientation());
	}
	
	@Test
	public void setOrientationFiresEvent()
	{
		box.addPropertyChangeListener(mockPropertyChangeListener(getMockery(), box, Box.ORIENTATION_PROPERTY,
			Orientation.VERTICAL, Orientation.HORIZONTAL));
		
		box.setOrientation(Orientation.HORIZONTAL);
	}
	
	@Test(expected = NullPointerException.class)
	public void setOrientationWithNull()
	{
		box.setOrientation(null);
	}
	
	@Test
	public void getLayoutDataAfterAdd()
	{
		Component child = new DummyComponent();
		box.add(child);
		
		assertEquals(new BoxLayoutData(), box.getLayoutData(child));
	}
	
	@Test(expected = NullPointerException.class)
	public void getLayoutDataWithNull()
	{
		box.getLayoutData(null);
	}
	
	@Test
	public void toStringTest()
	{
		box = new Box(Orientation.HORIZONTAL, new DummyComponent(), new DummyComponent());
		
		String expected = "org.hobsoft.symmetry.ui.Box[orientation=HORIZONTAL, "
			+ "components=[DummyComponent, DummyComponent]]";
		
		assertEquals(expected, box.toString());
	}

	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Box createComponent()
	{
		return new Box();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Box> getComponentType()
	{
		return Generic.get(Box.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertBox(Box actual, Orientation expectedOrientation, Component... expectedComponents)
	{
		assertNotNull("box", actual);
		assertEquals("orientation", expectedOrientation, actual.getOrientation());
		assertEquals("components", Arrays.asList(expectedComponents), actual.getComponents());
	}
}
