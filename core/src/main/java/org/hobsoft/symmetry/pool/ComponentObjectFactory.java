/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/ComponentObjectFactory.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.pool;

import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ComponentObjectFactory extends InstantiatingPoolableObjectFactory
{
	// fields -----------------------------------------------------------------
	
	private final PeerManager peerManager;
	
	// constructors -----------------------------------------------------------
	
	public ComponentObjectFactory(PeerManager peerManager, Class<?> componentClass)
	{
		super(componentClass);
		
		this.peerManager = peerManager;
	}
	
	// PoolableObjectFactory methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object makeObject() throws Exception
	{
		Object component = super.makeObject();
		
		peerManager.registerComponent(component);
		
		return component;
	}
	
	// public methods ---------------------------------------------------------
	
	public PeerManager getPeerManager()
	{
		return peerManager;
	}
}
