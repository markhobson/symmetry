/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/web/src/main/java/uk/co/iizuka/kozo/http/KozoServlet.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.http;

import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.DehydrationParameters;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StatePeerManager;
import org.hobsoft.symmetry.state.StateSession;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class KozoServlet extends HttpServlet
{
	// TODO: encode empty state
	
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private static final String COMPONENT_PARAM = "componentClassName";
	
	private static final String PEER_MANAGER_PARAM = "peerManagerClassName";
	
	private static final String RENDER_KIT_PARAM = "renderKitClassName";
	
	private static final String RESOLVE_STATE_PARAM = "resolveState";
	
	private static final String DEBUG_PARAM = "debug";
	
	private static final String THEME_PARAM = "theme";
	
	private static final String RESOURCE_PATH = "META-INF/resources";
	
	private static final int BUFFER_SIZE = 1024 * 8;
	
	private static final boolean LOG_TIMINGS = false;
	
	private static final String EMPTY_STATE = "-";
	
	// fields -----------------------------------------------------------------
	
	private KozoServletConfig config;
	
	// GenericServlet methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		// TODO: allow codec customisation in kozo.xml rather than servlet config
		Class<?> componentClass = HttpUtils.getInitParamClass(config, COMPONENT_PARAM, null);
		Class<?> peerManagerClass = HttpUtils.getInitParamClass(config, PEER_MANAGER_PARAM, null);
		Class<?> renderKitClass = HttpUtils.getInitParamClass(config, RENDER_KIT_PARAM, null);
		boolean resolveState = HttpUtils.getInitParamBoolean(config, RESOLVE_STATE_PARAM, false);
		boolean debug = HttpUtils.getInitParamBoolean(config, DEBUG_PARAM, false);
		String theme = HttpUtils.getInitParamString(config, THEME_PARAM, null);
		
		ComponentRenderKit<?> renderKit = (ComponentRenderKit<?>) newInstance(renderKitClass);
		
		PeerManager peerManager = (PeerManager) newInstance(peerManagerClass);
		
		// TODO: better
		((StatePeerManager) peerManager).setComponentClass(componentClass);
		
		this.config = new KozoServletConfig(peerManager, renderKit);
		this.config.setResolveState(resolveState);
		this.config.setDebug(debug);
		this.config.setTheme(theme);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy()
	{
		Introspector.flushCaches();
		
		super.destroy();
	}
	
	// HttpServlet methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		long time = System.currentTimeMillis();
		
		// redirect /context to /context/ for page urls
		String contextPath = request.getContextPath();
		if (request.getRequestURI().equals(contextPath))
		{
			response.sendRedirect(contextPath + "/");
			return;
		}
		
		// handle request types
		String pathInfo = request.getPathInfo();
		
		// TODO: better resource identification..
		boolean resource = (pathInfo != null) && (pathInfo.indexOf('.') != -1);
		
		if (resource)
		{
			doResource(request, response);
		}
		else
		{
			doApplication(request, response);
		}
		
		time = System.currentTimeMillis() - time;
		
		if (LOG_TIMINGS)
		{
			log(HttpUtils.getFullRequestUrl(request).append(' ').append(time).append("ms").toString());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException
	{
		doGet(request, response);
	}
	
	// private methods --------------------------------------------------------
	
	private static void doResource(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// send 404 if resource does not exist
		String pathInfo = request.getPathInfo();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(RESOURCE_PATH + pathInfo);
		if (url == null)
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// send 304 if resource is cached
		URLConnection connection = url.openConnection();
		// workaround for java bug #4823678
		connection.setUseCaches(false);
		long lastModified = connection.getLastModified();
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		InputStream in = connection.getInputStream();
		if (lastModified != 0 && ifModifiedSince != -1 && lastModified <= ifModifiedSince)
		{
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		}
		else
		{
			// TODO: set content type properly, currently keep w3c css validator happy..
			if (pathInfo.endsWith(".css"))
			{
				response.setContentType("text/css");
			}
			
			// send resource data
			response.setDateHeader("Last-Modified", lastModified);
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int n;
			while ((n = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, n);
			}
		}
		in.close();
	}
	
	private void doApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException
	{
		StateSession session = null;
		try
		{
			session = ((StatePeerManager) config.getPeerManager()).openSession();
			Object root = session.getRoot();
			
			// TODO: reinstate command concept
			
			rehydrate(root, request);
			
			// TODO: resolve state: response.sendRedirect(contextPath + "/" + session.getState(null));
			// TODO: fire end write transaction event, start read-only transaction?
			
			dehydrate(root, session.getState(), request.getLocale(), response);
		}
		catch (HydrationException exception)
		{
			throw new ServletException(exception);
		}
		finally
		{
			try
			{
				if (session != null)
				{
					session.close();
				}
			}
			catch (RuntimeException exception)
			{
				// invalidateComponent(root);
				throw exception;
			}
			catch (Error exception)
			{
				// invalidateComponent(root);
				throw exception;
			}
		}
	}
	
	private void rehydrate(Object component, HttpServletRequest request) throws ServletException, IOException,
		HydrationException
	{
		ComponentRenderKit<?> renderKit = config.getComponentRenderKit();
		
		RehydrationContext context = new RehydrationContext();
		context.setEncodedState(getEncodedState(request));
		
		rehydrateCapture(renderKit, component, context);
	}
	
	private static <T> void rehydrateCapture(ComponentRenderKit<T> renderKit, Object component,
		RehydrationContext context) throws HydrationException
	{
		T castComponent = renderKit.getComponentType().cast(component);
		
		renderKit.rehydrate(castComponent, context);
	}
	
	private void dehydrate(Object component, State state, Locale locale, HttpServletResponse response)
		throws IOException, HydrationException
	{
		ComponentRenderKit<?> renderKit = config.getComponentRenderKit();
		
		response.setLocale(locale);
		response.setContentType(renderKit.getContentType());
		
		DehydrationContext context = new DehydrationContext(state, locale, response.getOutputStream());
		
		DehydrationParameters parameters = new DehydrationParameters();
		parameters.setDebug(config.isDebug());
		parameters.setTheme(config.getTheme());
		context.set(DehydrationParameters.class, parameters);
		
		dehydrateCapture(renderKit, component, context);
	}
	
	private static <T> void dehydrateCapture(ComponentRenderKit<T> renderKit, Object component,
		DehydrationContext context) throws HydrationException
	{
		T castComponent = renderKit.getComponentType().cast(component);
		
		renderKit.dehydrate(castComponent, context);
	}
	
	private static EncodedState getEncodedState(HttpServletRequest request) throws ServletException, IOException
	{
		String state = getRequestState(request);
		Map<String, List<Object>> parameters = getRequestParameters(request);
		
		return new EncodedState(state, parameters);
	}
	
	private static String getRequestState(HttpServletRequest request)
	{
		String pathInfo = request.getPathInfo();
		String query = request.getQueryString();
		
		StringBuilder builder = new StringBuilder();
		
		if (pathInfo != null && pathInfo.length() > 1 && !pathInfo.substring(1).equals(EMPTY_STATE))
		{
			builder.append(pathInfo.substring(1));
		}
		
		if (query != null)
		{
			builder.append('?');
			builder.append(query);
		}

		return builder.toString();
	}
	
	private static Map<String, List<Object>> getRequestParameters(HttpServletRequest request) throws ServletException,
		IOException
	{
		if (!"POST".equals(request.getMethod()))
		{
			return Collections.emptyMap();
		}
		
		return HttpUtils.getPostParameters(request);
	}
	
	private static <T> T newInstance(Class<T> klass) throws ServletException
	{
		try
		{
			return klass.newInstance();
		}
		catch (InstantiationException exception)
		{
			throw new ServletException("Cannot instantiate class " + klass.getName(), exception);
		}
		catch (IllegalAccessException exception)
		{
			throw new ServletException("Cannot instantiate class " + klass.getName(), exception);
		}
	}
}
