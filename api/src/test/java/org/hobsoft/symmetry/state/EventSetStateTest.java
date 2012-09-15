/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/state/EventSetStateTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.EventSetDescriptor;
import java.util.EventObject;

import org.hobsoft.symmetry.support.bean.EventSets;
import org.junit.Test;

/**
 * Tests {@code EventSetState}.
 * 
 * @author Mark Hobson
 * @version $Id: EventSetStateTest.java 93651 2011-10-06 20:39:46Z mark@IIZUKA.CO.UK $
 * @see EventSetState
 */
public class EventSetStateTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newEventSetState()
	{
		EventSetDescriptor descriptor = getDescriptor();
		EventObject eventObject = createEventObject();
		
		EventSetState event = new EventSetState(descriptor, eventObject);
		
		assertEquals("Event set descriptor", descriptor, event.getDescriptor());
		assertEquals("Event object", eventObject, event.getEventObject());
	}
	
	@Test(expected = NullPointerException.class)
	public void newEventSetStateWithNullDescriptor()
	{
		new EventSetState(null, createEventObject());
	}
	
	@Test(expected = NullPointerException.class)
	public void newEventSetStateWithNullEventObject()
	{
		new EventSetState(getDescriptor(), null);
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		EventSetDescriptor descriptor = getDescriptor();
		EventObject eventObject = createEventObject();
		
		EventSetState event1 = new EventSetState(descriptor, eventObject);
		EventSetState event2 = new EventSetState(descriptor, eventObject);
		
		assertEquals(event1.hashCode(), event2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		EventSetDescriptor descriptor = getDescriptor();
		EventObject eventObject = createEventObject();
		
		EventSetState event1 = new EventSetState(descriptor, eventObject);
		EventSetState event2 = new EventSetState(descriptor, eventObject);
		
		assertTrue(event1.equals(event2));
	}
	
	@Test
	public void equalsWhenDescriptorNotEqual()
	{
		EventObject eventObject = createEventObject();
		
		EventSetState event1 = new EventSetState(getDescriptor("foo"), eventObject);
		EventSetState event2 = new EventSetState(getDescriptor("bar"), eventObject);
		
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void equalsWhenEventObjectNotEqual()
	{
		EventSetDescriptor descriptor = getDescriptor();
		
		EventSetState event1 = new EventSetState(descriptor, createEventObject());
		EventSetState event2 = new EventSetState(descriptor, createEventObject());
		
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		EventSetState event = createEventSetState();
		
		assertFalse(event.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		EventSetState event = createEventSetState();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(event.equals(null));
		// CHECKSTYLE:ON
	}
	
	@Test
	public void toStringTest()
	{
		EventObject eventObject = new EventObject(createBean());
		EventSetState event = new EventSetState(getDescriptor("foo"), eventObject);
		
		assertEquals("foo=" + eventObject, event.toString());
	}
	
	// private methods --------------------------------------------------------
	
	private static EventSetState createEventSetState()
	{
		return new EventSetState(getDescriptor(), createEventObject());
	}
	
	private static DummyBean createBean()
	{
		return new DummyBean();
	}
	
	private static EventSetDescriptor getDescriptor()
	{
		return getDescriptor("foo");
	}

	private static EventSetDescriptor getDescriptor(String eventSetName)
	{
		return EventSets.getDescriptor(DummyBean.class, eventSetName);
	}
	
	private static EventObject createEventObject()
	{
		return new EventObject(createBean());
	}
}
