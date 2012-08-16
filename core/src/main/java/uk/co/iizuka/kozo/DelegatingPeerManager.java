/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/DelegatingPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingPeerManager.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
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
