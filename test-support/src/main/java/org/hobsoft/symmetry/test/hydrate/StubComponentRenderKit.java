/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/test-support/src/main/java/uk/co/iizuka/kozo/test/hydrate/StubComponentRenderKit.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.test.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the base component type this kit can render
 */
public class StubComponentRenderKit<T> implements ComponentRenderKit<T>
{
	// fields -----------------------------------------------------------------
	
	private final Class<T> componentType;
	
	private final String contentType;
	
	// constructors -----------------------------------------------------------
	
	public StubComponentRenderKit(Class<T> componentType, String contentType)
	{
		this.componentType = componentType;
		this.contentType = contentType;
	}

	// ComponentRenderKit methods ---------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<T> getComponentType()
	{
		return componentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		// no-op
	}
}
