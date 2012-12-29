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
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class StatePeerManager implements PeerManager, PropertyChangeListener
{
	// fields -----------------------------------------------------------------
	
	private final PropertyChangeEventList eventList;
	
	private State state;
	
	private Object component;
	
	// constructors -----------------------------------------------------------
	
	public StatePeerManager()
	{
		eventList = new PropertyChangeEventList();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		if (this.component == null)
		{
			this.component = component;
		}
		
		BeanUtils.addPropertyChangeListener(component, this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		// TODO: can we use the codec here?
		
		return null;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		eventList.addPropertyChangeEvent(event);
		invalidate();
	}
	
	// public methods ---------------------------------------------------------
	
	public Object getComponent()
	{
		return component;
	}
	
	public State getState()
	{
		if (state == null)
		{
			state = new State();
			PropertyChangeEvent[] events = eventList.getPropertyChangeEvents();
			
			for (int i = 0; i < events.length; i++)
			{
				PropertyChangeEvent propertyEvent = events[i];
				Object component = propertyEvent.getSource();
				String propertyName = propertyEvent.getPropertyName();
				PropertyDescriptor property = Properties.getDescriptor(component.getClass(),
					propertyName);
				state.addProperty(new PropertyState(component, property, propertyEvent.getNewValue()));
			}
		}
		
		return new State(state);
	}

	public void rollback()
	{
		PropertyChangeEvent[] events = eventList.getPropertyChangeEvents();
		
		for (int i = events.length - 1; i >= 0; i--)
		{
			PropertyChangeEvent event = events[i];
			Object component = event.getSource();
			PropertyDescriptor property = Properties.getDescriptor(component.getClass(), event.getPropertyName());
			Properties.set(component, property, event.getOldValue());
		}
		
		eventList.clear();
	}
	
	// private methods --------------------------------------------------------
	
	private void invalidate()
	{
		state = null;
	}
}
