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
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

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
	// types
	// ----------------------------------------------------------------------------------------------------------------

	private static class DummyComponent
	{
		// dummy type
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void isWriteableWithComponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(DummyComponent.class, DummyComponent.class, new Annotation[0],
			MediaType.valueOf("x/y"));
		
		assertThat(actual, is(true));
	}

	@Test
	public void isWriteableWithDifferentMediaTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(DummyComponent.class, DummyComponent.class, new Annotation[0],
			MediaType.valueOf("x/z"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void isWriteableWithDifferentTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(Void.class, Void.class, new Annotation[0],
			MediaType.valueOf("x/y"));
		
		assertThat(actual, is(false));
	}

	@Test
	public void getSizeReturnsUnknown()
	{
		Reflector<Object> reflector = mock(Reflector.class);
		
		long actual = newWriter(reflector).getSize(anyComponent(), anyComponentType(), anyComponentType(),
			new Annotation[0], anyMediaType());
		
		assertThat(actual, is(-1L));
	}
	
	@Test
	public void writeToWithComponentWritesHtml() throws ReflectorException, IOException
	{
		DummyComponent component = new DummyComponent();
		MultivaluedMap<String, Object> httpHeaders = new MultivaluedHashMap<>();
		ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
		
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		doWrite("z").when(reflector).reflect(component, entityStream);
		
		newWriter(reflector).writeTo(component, DummyComponent.class, DummyComponent.class, new Annotation[0],
			MediaType.valueOf("x/y"), httpHeaders, entityStream);
		
		assertThat(entityStream.toString("UTF-8"), is("z"));
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static <T> Reflector<T> newReflector(Class<T> componentType, String contentType)
	{
		Reflector<T> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(componentType);
		when(reflector.getContentType()).thenReturn(contentType);
		return reflector;
	}
	
	private static Stubber doWrite(final String reflection)
	{
		return doAnswer(new Answer<Object>()
		{
			@Override
			public Object answer(InvocationOnMock invocation) throws ReflectorException
			{
				OutputStream outputStream = invocation.getArgumentAt(1, OutputStream.class);
				write(reflection, outputStream);
				return null;
			}
		});
	}
	
	private static void write(String reflection, OutputStream outputStream) throws ReflectorException
	{
		OutputStreamWriter writer = new OutputStreamWriter(outputStream);
		
		try
		{
			writer.write(reflection);
			writer.flush();
		}
		catch (IOException exception)
		{
			throw new ReflectorException("Error reflecting component", exception);
		}
	}
	
	private static <T> SymmetryMessageBodyWriter<T> newWriter(Reflector<T> reflector)
	{
		return new SymmetryMessageBodyWriter<>(reflector);
	}
	
	private static Object anyComponent()
	{
		return new Object();
	}

	private static Class<?> anyComponentType()
	{
		return Object.class;
	}

	private static MediaType anyMediaType()
	{
		return MediaType.valueOf("_/_");
	}
}
