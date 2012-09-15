/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/AbstractPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.bean.BeanUtils;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractPeerManager implements PeerManager, PropertyChangeListener
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, Object> peersByComponent;
	
	// constructors -----------------------------------------------------------
	
	public AbstractPeerManager()
	{
		peersByComponent = new HashMap<Object, Object>();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		BeanUtils.addPropertyChangeListener(component, this);
		
		Object peer = createPeer(component);
		peersByComponent.put(component, peer);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		return peersByComponent.get(component);
	}
	
	// public methods ---------------------------------------------------------
	
	public abstract Object createPeer(Object component);
}
