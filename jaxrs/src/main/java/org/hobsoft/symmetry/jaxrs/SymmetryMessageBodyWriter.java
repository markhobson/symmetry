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
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;

/**
 * JAX-RS message body writer for UI components.
 * 
 * @param <T>
 *            the base component type
 */
public class SymmetryMessageBodyWriter<T> implements MessageBodyWriter<T>
{
	// ----------------------------------------------------------------------------------------------------------------
	// constants
	// ----------------------------------------------------------------------------------------------------------------

	private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;
	
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final Reflector<T> reflector;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public SymmetryMessageBodyWriter(Reflector<T> reflector)
	{
		this.reflector = reflector;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// MessageBodyWriter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return reflector.getComponentType().isAssignableFrom(type)
			&& MediaType.valueOf(reflector.getContentType()).isCompatible(mediaType);
	}

	@Override
	public long getSize(T component, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return -1;
	}

	@Override
	public void writeTo(T component, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
		MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException
	{
		Writer entityWriter = new OutputStreamWriter(entityStream, DEFAULT_CHARSET);
		
		try
		{
			reflector.reflect(component, entityWriter);
		}
		catch (ReflectorException exception)
		{
			throw new InternalServerErrorException("Error writing component", exception);
		}
		
		entityWriter.flush();
	}
}
