/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.event;

import org.hobsoft.symmetry.ui.ComboBox;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code SelectionEvent}.
 * 
 * @author Mark Hobson
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
		
		assertEquals("org.hobsoft.symmetry.ui.event.SelectionEvent[source=" + source + ", index=1]", event.toString());
	}
}
