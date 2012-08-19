/*
 * $HeadURL: https://svn.iizuka.co.uk/common/test/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/test/matcher/PropertyChangeEventMatcher.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.test.matcher;

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
 * @version $Id: PropertyChangeEventMatcher.java 85710 2011-03-10 17:12:04Z mark@IIZUKA.CO.UK $
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
