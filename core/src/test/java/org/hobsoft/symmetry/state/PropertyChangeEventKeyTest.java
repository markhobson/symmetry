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
package org.hobsoft.symmetry.state;

import java.beans.PropertyChangeEvent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code PropertyChangeEventKey}.
 * 
 * @author Mark Hobson
 * @see PropertyChangeEventKey
 */
public class PropertyChangeEventKeyTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		new PropertyChangeEventKey(createEvent());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructWithNull()
	{
		new PropertyChangeEventKey(null);
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(this, "x"));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(this, "x"));
		
		assertEquals("hashCode", key1.hashCode(), key2.hashCode());
	}
	
	@Test
	public void hashCodeWhenEqualAndNullPropertyName()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(this, null));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(this, null));
		
		assertEquals("hashCode", key1.hashCode(), key2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(this, "x"));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(this, "x"));
		
		assertTrue("equals", key1.equals(key2));
	}
	
	@Test
	public void equalsWhenEqualAndNullPropertyName()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(this, null));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(this, null));
		
		assertTrue("equals", key1.equals(key2));
	}
	
	@Test
	public void equalsWhenSourceDifferent()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(new Object(), "x"));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(new Object(), "x"));
		
		assertFalse("equals", key1.equals(key2));
	}
	
	@Test
	public void equalsWhenPropertyNameDifferent()
	{
		PropertyChangeEventKey key1 = new PropertyChangeEventKey(createEvent(this, "x"));
		PropertyChangeEventKey key2 = new PropertyChangeEventKey(createEvent(this, "y"));
		
		assertFalse("equals", key1.equals(key2));
	}
	
	@Test
	public void toStringTest()
	{
		PropertyChangeEventKey key = new PropertyChangeEventKey(createEvent(this, "x"));
		
		assertEquals(String.format("PropertyChangeEventKey[source=%s, propertyName=x]", this), key.toString());
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyChangeEvent createEvent()
	{
		return createEvent(new Object(), "x");
	}
	
	private static PropertyChangeEvent createEvent(Object source, String propertyName)
	{
		return new PropertyChangeEvent(source, propertyName, null, null);
	}
}
