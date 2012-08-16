/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/event/SelectionEventTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.iizuka.kozo.ui.ComboBox;

/**
 * Tests {@code SelectionEvent}.
 * 
 * @author Mark Hobson
 * @version $Id: SelectionEventTest.java 88173 2011-05-18 09:24:49Z mark@IIZUKA.CO.UK $
 * @see SelectionEvent
 */
public class SelectionEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newSelectionEvent()
	{
		ComboBox<?> source = new ComboBox<Object>();
		SelectionEvent event = new SelectionEvent(source, 1);
		
		assertSame(source, event.getSource());
		assertEquals(1, event.getIndex());
	}

	@Test(expected = IllegalArgumentException.class)
	public void newSelectionEventWithNullSource()
	{
		new SelectionEvent(null, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newSelectionEventWithNegativeIndex()
	{
		new SelectionEvent(new ComboBox<Object>(), -1);
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		ComboBox<?> source = new ComboBox<Object>();
		SelectionEvent event1 = new SelectionEvent(source, 1);
		SelectionEvent event2 = new SelectionEvent(source, 1);
		
		assertEquals(event1.hashCode(), event2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		ComboBox<?> source = new ComboBox<Object>();
		SelectionEvent event1 = new SelectionEvent(source, 1);
		SelectionEvent event2 = new SelectionEvent(source, 1);

		assertTrue(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentSource()
	{
		SelectionEvent event1 = new SelectionEvent(new ComboBox<Object>(), 1);
		SelectionEvent event2 = new SelectionEvent(new ComboBox<Object>(), 1);
		
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentIndex()
	{
		ComboBox<?> source = new ComboBox<Object>();
		SelectionEvent event1 = new SelectionEvent(source, 1);
		SelectionEvent event2 = new SelectionEvent(source, 2);
		
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		SelectionEvent event = new SelectionEvent(new ComboBox<Object>(), 0);
		
		assertFalse(event.equals(new Object()));
	}
	
	@Test
	public void toStringTest()
	{
		ComboBox<?> source = new ComboBox<Object>();
		SelectionEvent event = new SelectionEvent(source, 1);
		
		assertEquals("uk.co.iizuka.kozo.ui.event.SelectionEvent[source=" + source + ", index=1]", event.toString());
	}
}
