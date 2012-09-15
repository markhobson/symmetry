/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/CompositeComponentVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.junit.Assert.assertNotNull;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.test.DummySubComponent;
import org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code CompositeComponentVisitor}.
 * 
 * @author Mark Hobson
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
