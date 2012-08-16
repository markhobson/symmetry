/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/state/PropertyChangeEventListTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyChangeEventListTest.java 98789 2012-02-27 12:01:42Z mark@IIZUKA.CO.UK $
 */
public class PropertyChangeEventListTest
{
	// fields -----------------------------------------------------------------
	
	private PropertyChangeEventList list;
	
	private Object source;
	
	private Object source2;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		list = new PropertyChangeEventList();
		source = new Object();
		source2 = new Object();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void eventConcatenation()
	{
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "a", "b"));
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "b", "c"));
		
		PropertyChangeEvent[] events = list.getPropertyChangeEvents();
		assertEquals(events.length, 1);
		assertPropertyChangeEventEquals(events[0], source, "p", "a", "c");
	}
	
	@Test
	public void eventCancelation()
	{
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "a", "b"));
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "b", "a"));
		
		PropertyChangeEvent[] events = list.getPropertyChangeEvents();
		assertEquals(events.length, 0);
	}
	
	@Test
	public void differentSourceEvents()
	{
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "a", "b"));
		list.addPropertyChangeEvent(new PropertyChangeEvent(source2, "p", "b", "c"));
		
		PropertyChangeEvent[] events = list.getPropertyChangeEvents();
		assertEquals(events.length, 2);
		assertPropertyChangeEventEquals(events[0], source, "p", "a", "b");
		assertPropertyChangeEventEquals(events[1], source2, "p", "b", "c");
	}
	
	@Test
	public void differentPropertyEvents()
	{
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p1", "a", "b"));
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p2", "b", "c"));
		
		PropertyChangeEvent[] events = list.getPropertyChangeEvents();
		assertEquals(events.length, 2);
		assertPropertyChangeEventEquals(events[0], source, "p1", "a", "b");
		assertPropertyChangeEventEquals(events[1], source, "p2", "b", "c");
	}
	
	@Test(expected = IllegalStateException.class)
	public void inconsistentEvents()
	{
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "a", "b"));
		list.addPropertyChangeEvent(new PropertyChangeEvent(source, "p", "c", "d"));
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertPropertyChangeEventEquals(PropertyChangeEvent expected, Object actualSource,
		String actualPropertyName, Object actualOldValue, Object actualNewValue)
	{
		assertEquals(expected.getSource(), actualSource);
		assertEquals(expected.getPropertyName(), actualPropertyName);
		assertEquals(expected.getOldValue(), actualOldValue);
		assertEquals(expected.getNewValue(), actualNewValue);
	}
}
