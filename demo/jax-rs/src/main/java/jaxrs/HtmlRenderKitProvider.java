/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/jax-rs/src/main/java/jaxrs/HtmlRenderKitProvider.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package jaxrs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.ui.html.HtmlRenderKit;


/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlRenderKitProvider.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 */
@Provider
public class HtmlRenderKitProvider implements ContextResolver<ComponentRenderKit>
{
	// ContextResolver methods ------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComponentRenderKit getContext(Class<?> type)
	{
		return new HtmlRenderKit();
	}
}
