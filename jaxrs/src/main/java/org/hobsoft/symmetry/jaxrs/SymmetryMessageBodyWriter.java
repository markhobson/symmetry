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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.html.HtmlComponentVisitor;
import org.hobsoft.symmetry.ui.html.XmlReflector;

/**
 * JAX-RS message body writer for UI components.
 */
public class SymmetryMessageBodyWriter implements MessageBodyWriter<Window>
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private Reflector<Component> reflector;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public SymmetryMessageBodyWriter()
	{
		reflector = new XmlReflector(new HtmlComponentVisitor(), "text/html");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// MessageBodyWriter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return reflector.getComponentType().isAssignableFrom(type)
			&& MediaType.valueOf(reflector.getContentType()).equals(mediaType);
	}

	@Override
	public long getSize(Window object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return -1;
	}

	@Override
	public void writeTo(Window object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
		MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException
	{
		try
		{
			reflector.reflect(object, entityStream);
		}
		catch (ReflectorException exception)
		{
			// TODO: handle
			throw new AssertionError();
		}
	}
}
