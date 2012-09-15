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
package org.hobsoft.symmetry.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class HttpUtils
{
	// TODO: use common-servlet instead
	
	// constructors -----------------------------------------------------------
	
	private HttpUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Class<?> getInitParamClass(ServletConfig config, String paramName, Class<?> defaultParamValue)
		throws ServletException
	{
		String paramValue = config.getInitParameter(paramName);
		
		if (paramValue == null)
		{
			return defaultParamValue;
		}
		
		try
		{
			return Class.forName(paramValue);
		}
		catch (ClassNotFoundException exception)
		{
			throw new ServletException("Cannot find initialisation parameter class: " + paramValue);
		}
	}
	
	public static String getInitParamString(ServletConfig config, String paramName, String defaultParamValue)
	{
		String paramValue = config.getInitParameter(paramName);
		return (paramValue != null) ? paramValue : defaultParamValue;
	}
	
	public static boolean getInitParamBoolean(ServletConfig config, String paramName, boolean defaultParamValue)
	{
		String paramValue = config.getInitParameter(paramName);
		return (paramValue != null) ? Boolean.parseBoolean(paramValue) : defaultParamValue;
	}
	
	public static Map<String, List<Object>> getPostParameters(HttpServletRequest request) throws ServletException,
		IOException
	{
		if (!"POST".equals(request.getMethod()))
		{
			return Collections.emptyMap();
		}
		
		if (ServletFileUpload.isMultipartContent(request))
		{
			return getMultipartPostParameters(request);
		}
		
		return getUrlPostParameters(request);
	}
	
	public static StringBuffer getFullRequestUrl(HttpServletRequest request)
	{
		StringBuffer buffer = request.getRequestURL();
		String query = request.getQueryString();
		
		if (query != null)
		{
			buffer.append('?').append(query);
		}
		
		return buffer;
	}
	
	// private methods --------------------------------------------------------
	
	private static Map<String, List<Object>> getMultipartPostParameters(HttpServletRequest request)
		throws ServletException, IOException
	{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try
		{
			Iterator<?> items = upload.parseRequest(request).iterator();
			
			Map<String, List<Object>> paramMap = new HashMap<String, List<Object>>();
			
			while (items.hasNext())
			{
				FileItem item = (FileItem) items.next();
				String name = item.getFieldName();
				Object value = getParameterValue(item);
				
				List<Object> values = paramMap.get(name);
				
				if (values == null)
				{
					values = new ArrayList<Object>();
					paramMap.put(name, values);
				}
				
				values.add(value);
			}
			return paramMap;
		}
		catch (FileUploadException exception)
		{
			throw new ServletException(exception);
		}
	}
	
	private static Object getParameterValue(FileItem item) throws IOException
	{
		Object value;
		
		if (item.isFormField())
		{
			InputStream in = item.getInputStream();
			
			// TODO: specify encoding?
			value = Streams.asString(in);
		}
		else
		{
			value = new FileItemDataSource(item);
		}
		
		return value;
	}
	
	private static Map<String, List<Object>> getUrlPostParameters(HttpServletRequest request)
	{
		Map<String, List<Object>> paramMap = new HashMap<String, List<Object>>();
		
		for (Enumeration<?> paramNames = request.getParameterNames(); paramNames.hasMoreElements(); )
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			
			paramMap.put(paramName, Arrays.asList((Object[]) paramValues));
		}
		
		return paramMap;
	}
}
