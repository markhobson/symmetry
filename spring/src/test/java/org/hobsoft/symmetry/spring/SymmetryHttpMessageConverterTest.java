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
import java.util.List;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

import com.google.common.base.Charsets;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.parseMediaType;

/**
 * Tests {@code SymmetryHttpMessageConverter}.
 */
public class SymmetryHttpMessageConverterTest
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
	public void canReadWithComponentAndContentTypeReturnsFalse() throws ReflectorException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y", anyString());
		
		boolean actual = newConverter(reflector).canRead(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithComponentAndContentTypeReturnsTrue() throws ReflectorException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y", anyString());
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithSubcomponentAndContentTypeReturnsTrue() throws ReflectorException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y", anyString());
		
		boolean actual = newConverter(reflector).canWrite(DummySubcomponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void getSupportedMediaTypesReturnsContentType() throws ReflectorException
	{
		Reflector<?> reflector = newReflector(anyComponentType(), "x/y", anyString());
		
		List<MediaType> actuals = newConverter(reflector).getSupportedMediaTypes();
		
		assertThat(actuals, contains(parseMediaType("x/y")));
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void readWithComponentThrowsException() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, anyContentType(), anyString());
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		
		newConverter(reflector).read(DummyComponent.class, inputMessage);
	}
	
	@Test
	public void writeWithComponentWritesHtml() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y", "z");
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("z"));
	}
	
	@Test
	public void writeWhenExceptionThrowsException() throws ReflectorException, IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		ReflectorException exception = new ReflectorException("z");
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(OutputStream.class));
		
		thrown.expect(HttpMessageNotWritableException.class);
		thrown.expectMessage("Cannot write component");
		thrown.expectCause(is(exception));
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), new MockHttpOutputMessage());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static <T> SymmetryHttpMessageConverter<T> newConverter(Reflector<T> reflector)
	{
		return new SymmetryHttpMessageConverter<>(reflector);
	}
	
	private static <T> Reflector<T> newReflector(Class<T> componentType, String contentType)
	{
		Reflector<T> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(componentType);
		when(reflector.getContentType()).thenReturn(contentType);
		return reflector;
	}
	
	private static <T> Reflector<T> newReflector(Class<T> componentType, String contentType, final String reflection)
		throws ReflectorException
	{
		Reflector<T> reflector = newReflector(componentType, contentType);
		
		doAnswer(new Answer<Object>()
		{
			@Override
			public Object answer(InvocationOnMock invocation) throws ReflectorException
			{
				OutputStream outputStream = invocation.getArgumentAt(1, OutputStream.class);
				write(reflection, outputStream);
				return null;
			}
		}).when(reflector).reflect(any(componentType), any(OutputStream.class));
		
		return reflector;
	}
	
	private static void write(String reflection, OutputStream outputStream) throws ReflectorException
	{
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charsets.UTF_8);
		
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
	
	private static Class<?> anyComponentType()
	{
		return Object.class;
	}
	
	private static String anyContentType()
	{
		return "_/_";
	}
	
	private static String anyString()
	{
		return "_";
	}
}
