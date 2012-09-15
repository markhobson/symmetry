/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ComponentUtilsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.traversal.ComponentFilter;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.accept;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code ComponentUtils}.
 * 
 * @author Mark Hobson
 * @see ComponentUtils
 */
@RunWith(JMock.class)
public class ComponentUtilsTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void getAncestorWithComponentWithNullParent()
	{
		Component component = createComponent();
		
		assertNull(ComponentUtils.getAncestor(component, accept()));
	}

	@Test
	public void getAncestorWithComponentWithNullParentDoesNotInvokeFilter()
	{
		Component component = createComponent();
		ComponentFilter filter = mockery.mock(ComponentFilter.class);
		
		ComponentUtils.getAncestor(component, filter);
	}
	
	@Test
	public void getAncestorWithComponentMatchesParent()
	{
		Component component = createNestedComponent();
		
		assertSame(component.getParent(), ComponentUtils.getAncestor(component, accept()));
	}

	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
	
	private static Component createNestedComponent()
	{
		return new Box(new DummyComponent());
	}
}
