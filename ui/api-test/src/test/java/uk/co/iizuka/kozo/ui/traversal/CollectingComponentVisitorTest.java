/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/CollectingComponentVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;

import com.googlecode.jtype.Generic;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code CollectingComponentVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: CollectingComponentVisitorTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see CollectingComponentVisitor
 */
public class CollectingComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private CollectingComponentVisitor visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new CollectingComponentVisitor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitNone()
	{
		assertEquals(Collections.emptyList(), visitor.getComponents());
	}
	
	@Test
	public void visitSingle()
	{
		Component component = createComponent();
		
		assertNotNull(visitor.visit(Generic.get(Component.class), component, null));
		
		assertEquals(Collections.singletonList(component), visitor.getComponents());
	}
	
	@Test
	public void visitMultiple()
	{
		Component[] components = new Component[] {
			createComponent(),
			createComponent(),
			createComponent()
		};
		
		visitor.visit(Generic.get(Component.class), components[0], null);
		visitor.visit(Generic.get(Component.class), components[1], null);
		visitor.visit(Generic.get(Component.class), components[2], null);
		
		assertEquals(Arrays.asList(components), visitor.getComponents());
	}
	
	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
}
