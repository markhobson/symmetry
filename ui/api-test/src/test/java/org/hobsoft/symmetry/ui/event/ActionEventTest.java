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
