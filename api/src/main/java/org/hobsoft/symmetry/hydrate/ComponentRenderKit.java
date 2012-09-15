/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/ComponentRenderKit.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentRenderKit.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the base component type this kit can render
 */
public interface ComponentRenderKit<T>
{
	Class<T> getComponentType();
	
	String getContentType();
	
	void dehydrate(T component, DehydrationContext context) throws HydrationException;
	
	void rehydrate(T component, RehydrationContext context) throws HydrationException;
}
