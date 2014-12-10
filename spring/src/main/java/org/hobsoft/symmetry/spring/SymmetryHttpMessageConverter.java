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
package org.hobsoft.symmetry.spring;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * Spring HTTP message converter for UI components.
 * 
 * @param <T>
 *            the base component type
 */
public class SymmetryHttpMessageConverter<T> extends AbstractHttpMessageConverter<T>
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

	public SymmetryHttpMessageConverter(Reflector<T> reflector)
	{
		super(MediaType.parseMediaType(reflector.getContentType()));

		this.reflector = reflector;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// HttpMessageConverter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType)
	{
		return false;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// AbstractHttpMessageConverter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected boolean supports(Class<?> clazz)
	{
		return reflector.getComponentType().isAssignableFrom(clazz);
	}

	@Override
	protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException
	{
		throw new HttpMessageNotReadableException("SymmetryHttpMessageConverter cannot read components");
	}

	@Override
	protected void writeInternal(T component, HttpOutputMessage outputMessage) throws IOException
	{
		OutputStream bodyStream = outputMessage.getBody();
		Writer bodyWriter = new OutputStreamWriter(bodyStream, DEFAULT_CHARSET);
		
		try
		{
			reflector.reflect(component, bodyWriter);
		}
		catch (ReflectorException exception)
		{
			throw new HttpMessageNotWritableException("Error writing component", exception);
		}
		
		bodyWriter.flush();
	}
}
