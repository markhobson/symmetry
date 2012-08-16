/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ComponentUtilsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static uk.co.iizuka.kozo.ui.traversal.ComponentFilters.accept;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.traversal.ComponentFilter;

/**
 * Tests {@code ComponentUtils}.
 * 
 * @author Mark Hobson
 * @version $Id: ComponentUtilsTest.java 95330 2011-11-18 12:00:44Z mark@IIZUKA.CO.UK $
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
