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
package org.hobsoft.symmetry;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingPeerManager extends AbstractPeerManager
{
	// TODO: rename class
	
	// fields -----------------------------------------------------------------
	
	private final Map<Class<?>, PeerHandler> handlersByComponentClass;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingPeerManager()
	{
		handlersByComponentClass = new HashMap<Class<?>, PeerHandler>();
	}
	
	// AbstractPeerManager methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		PeerHandler handler = getNearestPeerHandler(component.getClass());
		
		Object peer = null;
		
		if (handler != null)
		{
			peer = handler.createPeer(component);
		}
		
		return peer;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Object component = event.getSource();
		
		PeerHandler handler = getNearestPeerHandler(component.getClass());
		
		if (handler != null)
		{
			handler.propertyChange(event);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public PeerHandler getPeerHandler(Class<?> componentClass)
	{
		return handlersByComponentClass.get(componentClass);
	}
	
	public void setPeerHandler(Class<?> componentClass, PeerHandler handler)
	{
		handlersByComponentClass.put(componentClass, handler);
	}
	
	// private methods --------------------------------------------------------
	
	private PeerHandler getNearestPeerHandler(Class<?> componentClass)
	{
		PeerHandler handler = null;
		
		while (componentClass != null && handler == null)
		{
			handler = getPeerHandler(componentClass);
			
			componentClass = componentClass.getSuperclass();
		}
		
		return handler;
	}
}
