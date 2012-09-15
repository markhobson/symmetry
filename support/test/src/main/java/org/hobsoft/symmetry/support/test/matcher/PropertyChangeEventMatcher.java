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
package org.hobsoft.symmetry.support.test.matcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * Matcher for {@code PropertyChangeEvent}s.
 * 
 * @author Mark Hobson
 * @see PropertyChangeEvent
 */
public class PropertyChangeEventMatcher extends TypeSafeMatcher<PropertyChangeEvent>
{
	// fields -----------------------------------------------------------------
	
	private final PropertyChangeEvent event;
	
	// constructors -----------------------------------------------------------
	
	public PropertyChangeEventMatcher(PropertyChangeEvent event)
	{
		if (event == null)
		{
			throw new NullPointerException("event cannot be null");
		}
		
		this.event = event;
	}
	
	// TypeSafeMatcher methods ------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matchesSafely(PropertyChangeEvent item)
	{
		return (nullEquals(event.getPropertyName(), item.getPropertyName())
			&& nullEquals(event.getSource(), item.getSource())
			&& nullEquals(event.getOldValue(), item.getOldValue())
			&& nullEquals(event.getNewValue(), item.getNewValue()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description)
	{
		description.appendText("a PropertyChangeEvent equal to ").appendValue(event);
	}
	
	// public methods ---------------------------------------------------------
	
	public static PropertyChangeEventMatcher propertyChangeEvent(Object source, String propertyName, Object oldValue,
		Object newValue)
	{
		return propertyChangeEvent(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
	}
	
	public static PropertyChangeEventMatcher propertyChangeEvent(PropertyChangeEvent event)
	{
		return new PropertyChangeEventMatcher(event);
	}
	
	public static PropertyChangeListener mockPropertyChangeListener(Mockery mockery, final Object source,
		final String propertyName, final Object oldValue, final Object newValue)
	{
		return mockPropertyChangeListener(mockery, new PropertyChangeEvent(source, propertyName, oldValue, newValue));
	}
	
	public static PropertyChangeListener mockPropertyChangeListener(Mockery mockery, final PropertyChangeEvent event)
	{
		final PropertyChangeListener listener = mockery.mock(PropertyChangeListener.class);
		
		mockery.checking(new Expectations() { {
			oneOf(listener).propertyChange(with(propertyChangeEvent(event)));
		} });
		
		return listener;
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean nullEquals(Object a, Object b)
	{
		return (a == null && b == null) || (a != null && b != null && a.equals(b));
	}
}
