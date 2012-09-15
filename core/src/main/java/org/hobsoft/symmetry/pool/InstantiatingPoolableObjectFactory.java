/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/InstantiatingPoolableObjectFactory.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: InstantiatingPoolableObjectFactory.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class InstantiatingPoolableObjectFactory extends BasePoolableObjectFactory
{
	// fields -----------------------------------------------------------------
	
	private final Class<?> klass;
	
	// constructors -----------------------------------------------------------
	
	public InstantiatingPoolableObjectFactory(Class<?> klass)
	{
		this.klass = klass;
	}
	
	// PoolableObjectFactory methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object makeObject() throws Exception
	{
		return klass.newInstance();
	}
	
	// public methods ---------------------------------------------------------
	
	public Class<?> getPoolableObjectClass()
	{
		return klass;
	}
}
