/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/integration/jax-rs/src/main/java/uk/co/iizuka/kozo/jaxrs/ComponentWriter.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.jaxrs;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Locale;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.State;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentWriter.java 95640 2011-11-29 12:28:54Z mark@IIZUKA.CO.UK $
 */
@Provider
public class ComponentWriter implements MessageBodyWriter<Object>
{
	// fields -----------------------------------------------------------------
	
	private final ContextResolver<ComponentRenderKit> renderKitResolver;
	
	// constructors -----------------------------------------------------------
	
	public ComponentWriter(@Context Providers providers)
	{
		renderKitResolver = providers.getContextResolver(ComponentRenderKit.class, null);
	}
	
	// MessageBodyWriter methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		ComponentRenderKit<?> renderKit = getRenderKit(type);
		Class<?> componentType = renderKit.getComponentType();
		MediaType contentType = MediaType.valueOf(renderKit.getContentType());
		
		return componentType.isAssignableFrom(type) && contentType.isCompatible(mediaType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getSize(Object component, Class<?> type, Type genericType, Annotation[] annotations,
		MediaType mediaType)
	{
		return -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeTo(Object component, Class<?> type, Type genericType, Annotation[] annotations,
		MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException
	{
		ComponentRenderKit<?> renderKit = getRenderKit(type);
		State state = new State();
		Locale locale = null;
		
		DehydrationContext context = new DehydrationContext(state, locale, entityStream);
		
		try
		{
			dehydrate(renderKit, component, context);
		}
		catch (HydrationException exception)
		{
			throw new WebApplicationException(exception);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private ComponentRenderKit<?> getRenderKit(Class<?> componentType)
	{
		return renderKitResolver.getContext(componentType);
	}
	
	private static void dehydrate(ComponentRenderKit<?> renderKit, Object component, DehydrationContext context)
		throws HydrationException
	{
		dehydrateCapture(renderKit, component, context);
	}
	
	private static <T> void dehydrateCapture(ComponentRenderKit<T> renderKit, Object component,
		DehydrationContext context) throws HydrationException
	{
		T castComponent = renderKit.getComponentType().cast(component);
		
		renderKit.dehydrate(castComponent, context);
	}
}
