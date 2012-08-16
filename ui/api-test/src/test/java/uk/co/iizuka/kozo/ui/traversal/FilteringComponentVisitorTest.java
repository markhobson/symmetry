/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/FilteringComponentVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitor;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static uk.co.iizuka.kozo.ui.traversal.ComponentFilters.accept;
import static uk.co.iizuka.kozo.ui.traversal.ComponentFilters.reject;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import com.googlecode.jtype.Generic;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code FilteringComponentVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: FilteringComponentVisitorTest.java 95609 2011-11-28 15:23:29Z mark@IIZUKA.CO.UK $
 * @see FilteringComponentVisitor
 */
@RunWith(JMock.class)
public class FilteringComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitWhenAccept() throws Exception
	{
		final DummyComponent component = createComponent();
		final Object parameter = createVisitorParameter();
		
		final HierarchicalComponentVisitor<DummyComponent, Object, RuntimeException> subvisitor
			= nullHierarchicalVisitor();
		
		final ComponentVisitor<Object, RuntimeException> delegate = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).visit(Generic.get(DummyComponent.class), component, parameter);
				will(returnValue(subvisitor));
		} });
		
		FilteringComponentVisitor<Object, RuntimeException> visitor = createFilteringVisitor(delegate, accept());
		
		assertSame(subvisitor, visitor.visit(Generic.get(DummyComponent.class), component, parameter));
	}
	
	@Test
	public void visitWhenReject() throws Exception
	{
		DummyComponent component = createComponent();
		Object parameter = createVisitorParameter();
		
		FilteringComponentVisitor<Object, RuntimeException> visitor = createFilteringVisitor(reject());
		
		HierarchicalComponentVisitor<DummyComponent, Object, RuntimeException> actual = visitor.visit(
			Generic.get(DummyComponent.class), component, parameter);
		
		assertEquals(SKIP_CHILDREN, actual.visit(component, parameter));
	}
	
	// private methods --------------------------------------------------------
	
	private static <P, E extends Exception> FilteringComponentVisitor<P, E> createFilteringVisitor(
		ComponentVisitor<P, E> delegate, ComponentFilter filter)
	{
		return new FilteringComponentVisitor<P, E>(delegate, filter);
	}
	
	private <P, E extends Exception> FilteringComponentVisitor<P, E> createFilteringVisitor(ComponentFilter filter)
	{
		ComponentVisitor<P, E> delegate = createVisitor(mockery);
		
		return createFilteringVisitor(delegate, filter);
	}

	private static DummyComponent createComponent()
	{
		return new DummyComponent();
	}
}
