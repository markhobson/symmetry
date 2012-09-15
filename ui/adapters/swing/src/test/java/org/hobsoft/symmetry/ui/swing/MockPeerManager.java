/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/MockPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.AbstractPeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: MockPeerManager.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class MockPeerManager extends AbstractPeerManager
{
	// TODO: centralise
	
	// fields -----------------------------------------------------------------
	
	private final Map<Object, Object> peersByComponent;
	
	// constructors -----------------------------------------------------------
	
	public MockPeerManager()
	{
		peersByComponent = new HashMap<Object, Object>();
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPeer(Object component)
	{
		return peersByComponent.get(component);
	}
	
	// AbstractPeerManager methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		return null;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		// no-op
	}
	
	// public methods ---------------------------------------------------------
	
	public void setPeer(Object component, Object peer)
	{
		peersByComponent.put(component, peer);
	}
}
