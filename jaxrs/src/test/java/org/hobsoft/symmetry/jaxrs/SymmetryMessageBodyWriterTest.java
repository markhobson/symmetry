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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@code SymmetryMessageBodyWriter}.
 */
public class SymmetryMessageBodyWriterTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void isWriteableWithWindowAndHtmlReturnsTrue()
	{
		Reflector<Component> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(Component.class);
		when(reflector.getContentType()).thenReturn("text/html");
		
		boolean actual = new SymmetryMessageBodyWriter<>(reflector)
			.isWriteable(Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void isWriteableWithDifferentMediaTypeReturnsFalse()
	{
		Reflector<Component> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(Component.class);
		when(reflector.getContentType()).thenReturn("text/html");
		
		boolean actual = new SymmetryMessageBodyWriter<>(reflector)
			.isWriteable(Window.class, Window.class, new Annotation[0], MediaType.valueOf("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void isWriteableWithDifferentTypeReturnsFalse()
	{
		Reflector<Component> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(Component.class);
		when(reflector.getContentType()).thenReturn("text/html");
		
		boolean actual = new SymmetryMessageBodyWriter<>(reflector)
			.isWriteable(Void.class, Void.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void getSizeReturnsUnknown()
	{
		long actual = new SymmetryMessageBodyWriter<>(mock(Reflector.class))
			.getSize(new Window(), Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(-1L));
	}
	
	@Test
	public void writeToWithWindowWritesHtml() throws ReflectorException, IOException
	{
		Window component = new Window();
		MultivaluedMap<String, Object> httpHeaders = new MultivaluedHashMap<>();
		ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
		
		Reflector<Component> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(Component.class);
		when(reflector.getContentType()).thenReturn("text/html");
		doAnswer(new Answer<Object>()
		{
			@Override
			public Object answer(InvocationOnMock invocation) throws IOException
			{
				OutputStream outputStream = invocation.getArgumentAt(1, OutputStream.class);
				OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
				outputWriter.write("<html><body></body></html>");
				outputWriter.flush();
				return null;
			}
		}).when(reflector).reflect(component, entityStream);
		
		new SymmetryMessageBodyWriter<>(reflector)
			.writeTo(component, Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE, httpHeaders,
				entityStream);
		
		assertThat(entityStream.toString("UTF-8"), is("<html><body></body></html>"));
	}
}
