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
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.hobsoft.symmetry.ui.Window;

import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;

/**
 * JAX-RS message body writer for UI components.
 */
public class SymmetryMessageBodyWriter implements MessageBodyWriter<Window>
{
	// ----------------------------------------------------------------------------------------------------------------
	// MessageBodyWriter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return Window.class.equals(type) && TEXT_HTML_TYPE.equals(mediaType);
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
		OutputStreamWriter entityWriter = new OutputStreamWriter(entityStream, "UTF-8");
		
		entityWriter.write("<html><body></body></html>");
		entityWriter.flush();
	}
}
