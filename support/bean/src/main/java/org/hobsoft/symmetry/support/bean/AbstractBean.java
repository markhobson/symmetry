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

import java.beans.EventSetDescriptor;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

import static org.hobsoft.symmetry.support.bean.Utilities.checkNotNull;

/**
 * An abstract implementation of a JavaBean that provides property change support.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractBean
{
	// TODO: make all indexed properties fire IndexedPropertyChangeEvent's when
	// migrating to JDK5.0, and in the short-term, fire proper old and new
	// values rather than indexed old and new values
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The list of event listeners for this bean.
	 */
	private final EventListenerList listeners;
	
	/**
	 * The property change support for this bean.
	 */
	private final PropertyChangeSupport2 support;
	
	// constructors -----------------------------------------------------------
	
	public AbstractBean()
	{
		listeners = new EventListenerList();
		support = new PropertyChangeSupport2(this);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Adds the specified property change listener to this bean. The listener will be notified of changes to all bound
	 * properties.
	 * 
	 * @param listener
	 *            the property change listener to add to this bean
	 */
	public final void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	/**
	 * Adds the specified property change listener to this bean for the named property. The listener will only be
	 * notified of changes to the named bound property.
	 * 
	 * @param propertyName
	 *            the name of the property to listen to
	 * @param listener
	 *            the property change listener to add to this bean
	 */
	public final void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * Removes the specified property change listener from this bean.
	 * 
	 * @param listener
	 *            the property change listener to remove from this bean
	 */
	public final void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
	
	/**
	 * Removes the specified property change listener from this bean for the named property.
	 * 
	 * @param propertyName
	 *            the name of the property to stop listening to
	 * @param listener
	 *            the property change listener to remove from this bean
	 */
	public final void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * Gets all property change listeners added to this bean as an array.
	 * 
	 * @return the property change listeners added to this bean as an array, or an empty array if none have been added
	 */
	public final PropertyChangeListener[] getPropertyChangeListeners()
	{
		return support.getPropertyChangeListeners();
	}
	
	/**
	 * Gets the property change listeners added to this bean for the named property.
	 * 
	 * @param propertyName
	 *            the name of the property to get listeners for
	 * @return the property change listeners added to this bean for the named property as an array, or an empty array if
	 *         none have been added
	 */
	public final PropertyChangeListener[] getPropertyChangeListeners(String propertyName)
	{
		return support.getPropertyChangeListeners(propertyName);
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Adds the specified event listener to the listener list for this component.
	 * <p>
	 * This method is intended to be used by subclass <code>addXXXListener()</code> implementations.
	 * 
	 * @param <T>
	 *            the listener type
	 * @param listenerType
	 *            the class of the event listener to be added to this component
	 * @param listener
	 *            the event listener to be added to this component
	 */
	protected final <T extends EventListener> void addListener(Class<T> listenerType, T listener)
	{
		checkNotNull(listener, "listener");
		
		listeners.add(listenerType, listener);
	}
	
	/**
	 * Removes the specified event listener from the listener list for this component.
	 * <p>
	 * This method is intended to be used by subclass <code>addXXXListener()</code> implementations.
	 * 
	 * @param <T>
	 *            the listener type
	 * @param listenerType
	 *            the class of the event listener to be removed from this component
	 * @param listener
	 *            the event listener to be removed from this component
	 */
	protected final <T extends EventListener> void removeListener(Class<T> listenerType, T listener)
	{
		checkNotNull(listener, "listener");
		
		listeners.remove(listenerType, listener);
	}
	
	/**
	 * Gets all the listeners of this component as an array of class-listener pairs.
	 * <p>
	 * <b>Warning:</b> For performance reasons, the internal object array is returned, so under no circumstances should
	 * this be modified.
	 * 
	 * @return the listeners of this component as an non-null array of class-listener pairs
	 */
	protected final Object[] getListeners()
	{
		// TODO: remove this method in preference of an eventFire() method?
		return listeners.getListenerList();
	}
	
	/**
	 * Gets the listeners of this component of the specified type as an array.
	 * 
	 * @param <T>
	 *            the listener type
	 * @param listenerType
	 *            the class of the listeners to get
	 * @return the listeners of this component of the specified type as an array
	 */
	protected final <T extends EventListener> T[] getListeners(Class<T> listenerType)
	{
		return listeners.getListeners(listenerType);
	}
	
	/**
	 * Gets the number of listeners of this component of the specified type.
	 * 
	 * @param listenerType
	 *            the class of the listeners to count
	 * @return the number of listeners of this component of the specified type
	 */
	protected final int getListenerCount(Class<?> listenerType)
	{
		return listeners.getListenerCount(listenerType);
	}
	
	/**
	 * Fires a property change event for the named bound property to registered listeners. No event is fired if the old
	 * and new values are equal.
	 * 
	 * @param <T>
	 *            the value type
	 * @param propertyName
	 *            the name of the property that has changed
	 * @param oldValue
	 *            the previous value of the named property
	 * @param newValue
	 *            the current value of the named property
	 */
	protected final <T> void firePropertyChange(String propertyName, T oldValue, T newValue)
	{
		support.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	protected final <T> void fireIndexedPropertyChange(String propertyName, int index, T oldValue, T newValue)
	{
		support.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}
	
	protected final void fireEvent(EventSetDescriptor eventSet, EventObject event)
	{
		EventSets.fire(this, eventSet, event);
	}
}
