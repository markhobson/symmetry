/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/AbstractComponentTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.nullEndVisit;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.nullVisit;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipSiblings;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.junit.Assert.assertEquals;

/**
 * Shared tests for {@code Component} implementations.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the type of component that this test tests
 * @see Component
 */
@RunWith(JMock.class)
public abstract class AbstractComponentTest<T extends Component>
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();
	
	private Generic<T> componentType;
	
	private T component;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpAbstractComponentTest()
	{
		componentType = getComponentType();
		component = createComponent();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithSubvisitorReturningVisitSiblings()
	{
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(componentType, component, parameter); will(returnValue(nullHierarchicalVisitor()));
		} });
		
		assertEquals(VISIT_SIBLINGS, component.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningSkipSiblings()
	{
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(componentType, component, parameter); will(returnValue(skipSiblings()));
		} });
		
		assertEquals(SKIP_SIBLINGS, component.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningNullVisit()
	{
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(componentType, component, parameter); will(returnValue(nullVisit()));
		} });
		
		assertEquals(VISIT_SIBLINGS, component.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithSubvisitorReturningNullEndVisit()
	{
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(componentType, component, parameter); will(returnValue(nullEndVisit()));
		} });
		
		assertEquals(VISIT_SIBLINGS, component.accept(visitor, parameter));
	}
	
	@Test
	public void acceptWithNullSubvisitor()
	{
		final Object parameter = createVisitorParameter();
		
		final ComponentVisitor<Object, RuntimeException> visitor = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(componentType, component, parameter); will(returnValue(null));
		} });
		
		assertEquals(VISIT_SIBLINGS, component.accept(visitor, parameter));
	}
	
	@Test(expected = NullPointerException.class)
	public void acceptWithNullVisitor()
	{
		component.accept((ComponentVisitor<Object, RuntimeException>) null, createVisitorParameter());
	}
	
	// protected methods ------------------------------------------------------
	
	protected final Mockery getMockery()
	{
		return mockery;
	}
	
	protected final T getComponent()
	{
		return component;
	}
	
	protected abstract T createComponent();
	
	protected abstract Generic<T> getComponentType();
}
