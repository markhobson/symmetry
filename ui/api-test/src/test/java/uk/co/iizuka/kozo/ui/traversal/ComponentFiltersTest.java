/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/ComponentFiltersTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.VBox;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code ComponentFilters}.
 * 
 * @author Mark Hobson
 * @version $Id: ComponentFiltersTest.java 100646 2012-04-23 10:00:25Z mark@IIZUKA.CO.UK $
 * @see ComponentFilters
 */
public class ComponentFiltersTest
{
	// accept tests -----------------------------------------------------------
	
	@Test
	public void accept()
	{
		assertTrue(ComponentFilters.accept().accept(component()));
	}
	
	// reject tests -----------------------------------------------------------
	
	@Test
	public void reject()
	{
		assertFalse(ComponentFilters.reject().accept(component()));
	}

	// visible tests ----------------------------------------------------------
	
	@Test
	public void acceptWhenVisible()
	{
		assertTrue(ComponentFilters.visible().accept(component()));
	}
	
	@Test
	public void acceptWhenInvisible()
	{
		assertFalse(ComponentFilters.visible().accept(invisibleComponent()));
	}
	
	// not tests --------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void notWithNull()
	{
		ComponentFilters.not(null);
	}
	
	@Test
	public void notWithAccept()
	{
		assertFalse(ComponentFilters.not(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void notWithReject()
	{
		assertTrue(ComponentFilters.not(ComponentFilters.reject()).accept(component()));
	}

	// and tests --------------------------------------------------------------
	
	@Test
	public void andWithAccept()
	{
		assertTrue(ComponentFilters.and(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject()).accept(component()));
	}

	@Test
	public void andWithAcceptAndAccept()
	{
		assertTrue(ComponentFilters.and(ComponentFilters.accept(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithAcceptAndReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.accept(), ComponentFilters.reject()).accept(component()));
	}
	
	@Test
	public void andWithRejectAndAccept()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithRejectAndReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject(), ComponentFilters.reject()).accept(component()));
	}

	// or tests ---------------------------------------------------------------
	
	@Test
	public void orWithAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithReject()
	{
		assertFalse(ComponentFilters.or(ComponentFilters.reject()).accept(component()));
	}

	@Test
	public void orWithAcceptAndAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithAcceptAndReject()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept(), ComponentFilters.reject()).accept(component()));
	}
	
	@Test
	public void orWithRejectAndAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.reject(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithRejectAndReject()
	{
		assertFalse(ComponentFilters.or(ComponentFilters.reject(), ComponentFilters.reject()).accept(component()));
	}
	
	// type tests -------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void typeWithNull()
	{
		ComponentFilters.type(null);
	}
	
	@Test
	public void typeWithType()
	{
		assertTrue(ComponentFilters.type(Box.class).accept(new Box()));
	}
	
	@Test
	public void typeWithSubtype()
	{
		assertTrue(ComponentFilters.type(Box.class).accept(new VBox()));
	}
	
	@Test
	public void typeWithSupertype()
	{
		assertFalse(ComponentFilters.type(VBox.class).accept(new Box()));
	}
	
	@Test
	public void typeWithNullComponent()
	{
		assertFalse(ComponentFilters.type(Box.class).accept(null));
	}
	
	// private methods --------------------------------------------------------
	
	private static Component component()
	{
		return new DummyComponent();
	}
	
	private static Component invisibleComponent()
	{
		Component component = component();
		component.setVisible(false);
		
		return component;
	}
}
