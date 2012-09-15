/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/event/ActionEventTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code ActionEvent}.
 * 
 * @author Mark Hobson
 * @see ActionEvent
 */
public class ActionEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void hashCodeWhenEquals()
	{
		Component source = createComponent();
		ActionEvent event1 = new ActionEvent(source);
		ActionEvent event2 = new ActionEvent(source);
		
		assertEquals(event1.hashCode(), event2.hashCode());
	}
	
	@Test
	public void equalsWhenEquals()
	{
		Component source = createComponent();
		ActionEvent event1 = new ActionEvent(source);
		ActionEvent event2 = new ActionEvent(source);
		
		assertTrue(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentSource()
	{
		ActionEvent event1 = createActionEvent();
		ActionEvent event2 = createActionEvent();
		
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		ActionEvent event = createActionEvent();
		
		assertFalse(event.equals(new Object()));
	}
	
	@Test
	public void equalsWithComponentEvent()
	{
		Component source = createComponent();
		ActionEvent event = new ActionEvent(source);
		
		assertFalse(event.equals(new DummyEvent(source)));
	}
	
	@Test
	public void equalsWithNull()
	{
		ActionEvent event = createActionEvent();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(event.equals(null));
		// CHECKSTYLE:ON
	}
	
	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
	
	private static ActionEvent createActionEvent()
	{
		return new ActionEvent(createComponent());
	}
}
