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
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;

/**
 * Tests {@code PropertyChangeSupport2}.
 * 
 * @author Mark Hobson
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
