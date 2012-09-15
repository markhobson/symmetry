/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/CollectingComponentVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code CollectingComponentVisitor}.
 * 
 * @author Mark Hobson
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
