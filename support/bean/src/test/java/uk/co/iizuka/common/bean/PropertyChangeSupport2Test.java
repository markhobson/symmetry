/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/PropertyChangeSupport2Test.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import static uk.co.iizuka.common.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code PropertyChangeSupport2}.
 * 
 * @author Mark Hobson
 * @version $Id: PropertyChangeSupport2Test.java 97518 2012-01-04 16:28:52Z mark@IIZUKA.CO.UK $
 * @see PropertyChangeSupport2
 */
@RunWith(JMock.class)
public class PropertyChangeSupport2Test
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private Object source;
	
	private PropertyChangeSupport2 support;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		source = new Object();
		support = new PropertyChangeSupport2(source);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void firePropertyChangeEvent()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", "a", "b"));
		
		support.firePropertyChange(new PropertyChangeEvent(source, "x", "a", "b"));
	}
	
	@Test
	public void firePropertyChangeEventWithNullOldValue()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", null, "b"));
		
		support.firePropertyChange(new PropertyChangeEvent(source, "x", null, "b"));
	}

	@Test
	public void firePropertyChangeEventWithNullNewValue()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", "a", null));
		
		support.firePropertyChange(new PropertyChangeEvent(source, "x", "a", null));
	}
	
	@Test
	public void firePropertyChangeEventWithNullOldAndNewValues()
	{
		support.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		support.firePropertyChange(new PropertyChangeEvent(source, "x", null, null));
	}

	@Test
	public void firePropertyChangeArguments()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", "a", "b"));
		
		support.firePropertyChange("x", "a", "b");
	}
	
	@Test
	public void firePropertyChangeArgumentsWithNullOldValue()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", null, "b"));
		
		support.firePropertyChange("x", null, "b");
	}
	
	@Test
	public void firePropertyChangeArgumentsWithNullNewValue()
	{
		support.addPropertyChangeListener(mockPropertyChangeListener(mockery, source, "x", "a", null));
		
		support.firePropertyChange("x", "a", null);
	}

	@Test
	public void firePropertyChangeArgumentsWithNullOldAndNewValues()
	{
		support.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		support.firePropertyChange("x", null, null);
	}
}
