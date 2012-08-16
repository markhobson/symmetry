/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/CompositeComponentVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertNotNull;
import static uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors.createVisitorParameter;

import com.googlecode.jtype.Generic;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.test.DummySubComponent;
import uk.co.iizuka.kozo.ui.test.traversal.MockComponentVisitors;

/**
 * Tests {@code CompositeComponentVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: CompositeComponentVisitorTest.java 95942 2011-12-05 18:08:39Z mark@IIZUKA.CO.UK $
 * @see CompositeComponentVisitor
 */
@RunWith(JMock.class)
public class CompositeComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void visitWithComponentWhenNoDelegates()
	{
		CompositeComponentVisitor<Void, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.visit(Generic.get(Component.class), createComponent(), null);
	}
	
	@Test
	public void visitWithComponentWhenDelegate() throws Exception
	{
		CompositeComponentVisitor<Object, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.setDelegate(DummyComponent.class, MockComponentVisitors
			.<DummyComponent, Object, RuntimeException>createHierarchicalVisitor(mockery));
		
		assertNotNull(visitor.visit(Generic.get(DummyComponent.class), createComponent(), createVisitorParameter()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void visitWithSubtypeComponentWhenSupertypeDelegate() throws Exception
	{
		CompositeComponentVisitor<Object, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.setDelegate(DummyComponent.class, MockComponentVisitors
			.<DummyComponent, Object, RuntimeException>createHierarchicalVisitor(mockery));
		
		visitor.visit(Generic.get(DummySubComponent.class), createSubtypeComponent(), createVisitorParameter());
	}
	
	// private methods --------------------------------------------------------
	
	private static <P, E extends Exception> CompositeComponentVisitor<P, E> createCompositeVisitor()
	{
		return new CompositeComponentVisitor<P, E>();
	}
	
	private static DummyComponent createComponent()
	{
		return new DummyComponent();
	}
	
	private static DummySubComponent createSubtypeComponent()
	{
		return new DummySubComponent();
	}
}
