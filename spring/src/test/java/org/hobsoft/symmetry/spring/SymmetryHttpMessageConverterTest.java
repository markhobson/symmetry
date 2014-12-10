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
import java.io.Writer;
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

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
	// JUnit methods
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
	public void canReadWithComponentAndContentTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canRead(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithComponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithSubcomponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummySubcomponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithComponentAndCompatibleContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/*"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithDifferentClassReturnsFalse()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(Void.class, parseMediaType("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithDifferentMediaTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/z"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void getSupportedMediaTypesReturnsContentType()
	{
		Reflector<?> reflector = mockReflector(someComponentType(), "x/y");
		
		List<MediaType> actuals = newConverter(reflector).getSupportedMediaTypes();
		
		assertThat(actuals, contains(parseMediaType("x/y")));
	}
	
	@Test
	public void readWithComponentThrowsException() throws IOException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, someContentType());
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		
		thrown.expect(HttpMessageNotReadableException.class);
		thrown.expectMessage("SymmetryHttpMessageConverter cannot read components");
		
		newConverter(reflector).read(DummyComponent.class, inputMessage);
	}
	
	@Test
	public void writeWithComponentInvokesReflector() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		DummyComponent component = new DummyComponent();
		
		newConverter(reflector).write(component, parseMediaType("x/y"), new MockHttpOutputMessage());
		
		verify(reflector).reflect(eq(component), any(Writer.class));
	}
	
	@Test
	public void writeWithComponentWritesReflection() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		doAnswer(write(1, "z")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("z"));
	}
	
	@Test
	public void writeWithoutCharsetEncodesReflectionUsingIso88591() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		doAnswer(write(1, "\u20AC")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(ISO_8859_1), is("?"));
	}
	
	@Test
	public void writeWhenIOExceptionThrowsException() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		IOException exception = new IOException();
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		thrown.expect(is(exception));
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), new MockHttpOutputMessage());
	}
	
	@Test
	public void writeWhenReflectorExceptionThrowsSpringException() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		ReflectorException exception = new ReflectorException("z");
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		thrown.expect(HttpMessageNotWritableException.class);
		thrown.expectMessage("Error writing component");
		thrown.expectCause(is(exception));
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), new MockHttpOutputMessage());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static <T> Reflector<T> mockReflector(Class<T> componentType, String contentType)
	{
		Reflector<T> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(componentType);
		when(reflector.getContentType()).thenReturn(contentType);
		return reflector;
	}
	
	private static <T> SymmetryHttpMessageConverter<T> newConverter(Reflector<T> reflector)
	{
		return new SymmetryHttpMessageConverter<>(reflector);
	}
	
	private static Class<?> someComponentType()
	{
		return Object.class;
	}
	
	private static String someContentType()
	{
		return "_/_";
	}

	private static Answer<Object> write(final int writerIndex, final String string)
	{
		return new Answer<Object>()
		{
			@Override
			public Object answer(InvocationOnMock invocation) throws IOException
			{
				Writer writer = invocation.getArgumentAt(writerIndex, Writer.class);
				writer.write(string);
				return null;
			}
		};
	}
}
