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

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.State;

/**
 * 
 * 
 * @author Mark Hobson
 */
@Provider
public class ComponentWriter implements MessageBodyWriter<Object>
{
	// fields -----------------------------------------------------------------
	
	private final ContextResolver<PeerManager> peerManagerResolver;
	
	private final ContextResolver<ComponentRenderKit> renderKitResolver;
	
	// constructors -----------------------------------------------------------
	
	public ComponentWriter(@Context Providers providers)
	{
		peerManagerResolver = providers.getContextResolver(PeerManager.class, null);
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
		PeerManager peerManager = getPeerManager(type);
		peerManager.registerComponent(component);
		
		ComponentRenderKit<?> renderKit = getRenderKit(type);
		State state = new State();
		Locale locale = null;
		
		DehydrationContext context = new DehydrationContext(state, locale, entityStream);
		context.set(PeerManager.class, peerManager);
		
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
	
	private PeerManager getPeerManager(Class<?> componentType)
	{
		return peerManagerResolver.getContext(componentType);
	}
	
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
