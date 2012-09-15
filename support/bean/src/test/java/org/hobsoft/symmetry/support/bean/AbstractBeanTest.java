/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/AbstractBeanTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertArrayEquals;

/**
 * Tests {@code AbstractBean}.
 * 
 * @author Mark Hobson
 * @see AbstractBean
 */
@RunWith(JMock.class)
public class AbstractBeanTest
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();
	
	private AbstractBean bean;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		bean = new DummyBean();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void addNamedPropertyChangeListenerReceivesEvent()
	{
		PropertyChangeListener listener = mockPropertyChangeListener(mockery, bean, "a", "x", "y");
		bean.addPropertyChangeListener("a", listener);
		
		bean.firePropertyChange("a", "x", "y");
	}
	
	@Test
	public void addNamedPropertyChangeListenerDoesNotReceiveEventForDifferentProperty()
	{
		PropertyChangeListener listener = mockery.mock(PropertyChangeListener.class);
		bean.addPropertyChangeListener("a", listener);
		
		bean.firePropertyChange("b", "x", "y");
	}
	
	@Test
	public void addListener()
	{
		DummyEventListener listener = new DummyEventListener();
		
		bean.addListener(DummyEventListener.class, listener);
		
		assertArrayEquals(new DummyEventListener[] {listener}, bean.getListeners(DummyEventListener.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void addListenerWithNullListenerType()
	{
		bean.addListener(null, new DummyEventListener());
	}
	
	@Test(expected = NullPointerException.class)
	public void addListenerWithNullListener()
	{
		bean.addListener(DummyEventListener.class, null);
	}
	
	@Test
	public void removeListener()
	{
		DummyEventListener listener = new DummyEventListener();
		bean.addListener(DummyEventListener.class, listener);
		
		bean.removeListener(DummyEventListener.class, listener);
		
		assertArrayEquals(new DummyEventListener[0], bean.getListeners(DummyEventListener.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void removeListenerWithNullListenerType()
	{
		bean.removeListener(null, new DummyEventListener());
	}
	
	@Test(expected = NullPointerException.class)
	public void removeListenerWithNullListener()
	{
		bean.removeListener(DummyEventListener.class, null);
	}
	
	@Test
	public void firePropertyChangeFiresEvent()
	{
		bean.addPropertyChangeListener(mockPropertyChangeListener(mockery, bean, "a", "x", "y"));
		bean.firePropertyChange("a", "x", "y");
	}
	
	@Test
	public void firePropertyChangeFiresEventWhenOldValueNull()
	{
		bean.addPropertyChangeListener(mockPropertyChangeListener(mockery, bean, "a", null, "x"));
		bean.firePropertyChange("a", null, "x");
	}
	
	@Test
	public void firePropertyChangeFiresEventWhenNewValueNull()
	{
		bean.addPropertyChangeListener(mockPropertyChangeListener(mockery, bean, "a", "x", null));
		bean.firePropertyChange("a", "x", null);
	}
	
	@Test
	public void firePropertyChangeDoesNotFireEventWhenValuesSame()
	{
		bean.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		Object value = new Object();
		bean.firePropertyChange("a", value, value);
	}
	
	@Test
	public void firePropertyChangeDoesNotFireEventWhenValuesEqual()
	{
		bean.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		bean.firePropertyChange("a", new String("x"), new String("x"));
	}
	
	@Test
	public void firePropertyChangeDoesNotFireEventWhenValuesNull()
	{
		bean.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		bean.firePropertyChange("a", null, null);
	}
}
