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
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.base.Charsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
	
	private static class DummySubcomponent extends DummyComponent
	{
		// dummy type
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedException thrown = ExpectedException.none();
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void isWriteableWithComponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void isWriteableWithSubcomponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(DummySubcomponent.class, DummySubcomponent.class,
			anyAnnotations(), MediaType.valueOf("x/y"));
		
		assertThat(actual, is(true));
	}

	@Test
	public void isWriteableWithDifferentMediaTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/z"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void isWriteableWithDifferentTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newWriter(reflector).isWriteable(Void.class, Void.class, anyAnnotations(),
			MediaType.valueOf("x/y"));
		
		assertThat(actual, is(false));
	}

	@Test
	public void getSizeReturnsUnknown()
	{
		Reflector<Object> reflector = mock(Reflector.class);
		
		long actual = newWriter(reflector).getSize(anyComponent(), anyComponentType(), anyComponentType(),
			anyAnnotations(), anyMediaType());
		
		assertThat(actual, is(-1L));
	}
	
	@Test
	public void writeToWithComponentInvokesReflector() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		DummyComponent component = new DummyComponent();
		ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
		
		newWriter(reflector).writeTo(component, DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/y"), anyHttpHeaders(), entityStream);
		
		verify(reflector).reflect(component, entityStream);
	}
	
	@Test
	public void writeToWithComponentWritesHtml() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		doAnswer(write(1, "z")).when(reflector).reflect(any(DummyComponent.class), any(OutputStream.class));
		ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
		
		newWriter(reflector).writeTo(new DummyComponent(), DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/y"), anyHttpHeaders(), entityStream);
		
		assertThat(toString(entityStream), is("z"));
	}
	
	@Test
	public void writeToWhenIOExceptionThrowsException() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		IOException exception = new IOException();
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(OutputStream.class));
		
		thrown.expect(is(exception));
		
		newWriter(reflector).writeTo(new DummyComponent(), DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/y"), anyHttpHeaders(), new ByteArrayOutputStream());
	}
	
	@Test
	public void writeToWhenReflectorExceptionThrowsJaxrsException() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		ReflectorException exception = new ReflectorException("z");
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(OutputStream.class));
		
		thrown.expect(InternalServerErrorException.class);
		thrown.expectMessage("Cannot write component");
		thrown.expectCause(is(exception));
		
		newWriter(reflector).writeTo(new DummyComponent(), DummyComponent.class, DummyComponent.class, anyAnnotations(),
			MediaType.valueOf("x/y"), anyHttpHeaders(), new ByteArrayOutputStream());
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
	
	private static Answer<Object> write(final int outputStreamIndex, final String string)
	{
		return new Answer<Object>()
		{
			@Override
			public Object answer(InvocationOnMock invocation) throws IOException
			{
				OutputStream outputStream = invocation.getArgumentAt(outputStreamIndex, OutputStream.class);
				outputStream.write(string.getBytes(Charsets.UTF_8));
				return null;
			}
		};
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

	private static Annotation[] anyAnnotations()
	{
		return new Annotation[0];
	}
	
	private static MediaType anyMediaType()
	{
		return MediaType.valueOf("_/_");
	}

	private static MultivaluedMap<String, Object> anyHttpHeaders()
	{
		return new MultivaluedHashMap<>();
	}

	private static String toString(ByteArrayOutputStream entityStream) throws UnsupportedEncodingException
	{
		return entityStream.toString("UTF-8");
	}
}
