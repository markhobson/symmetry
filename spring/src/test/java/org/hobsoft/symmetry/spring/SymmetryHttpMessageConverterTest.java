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
import java.util.Collections;
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
import static java.nio.charset.StandardCharsets.UTF_8;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasEntry;
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
	public void canWriteWithSubtypeComponentReturnsTrue()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummySubcomponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithDifferentComponentReturnsFalse()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(Void.class, parseMediaType("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithCompatibleContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/*"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithDifferentContentTypeReturnsFalse()
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
	public void writeSetsContentType() throws IOException
	{
		Reflector<Object> reflector = mockReflector(someComponentType(), "x/y");
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(someComponent(), null, outputMessage);
		
		assertThat(outputMessage.getHeaders(), hasEntry("Content-Type", Collections.singletonList("x/y")));
	}
	
	@Test
	public void writeWithComponentInvokesReflector() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, someContentType());
		DummyComponent component = new DummyComponent();
		
		newConverter(reflector).write(component, null, new MockHttpOutputMessage());
		
		verify(reflector).reflect(eq(component), any(Writer.class));
	}
	
	@Test
	public void writeWithComponentWritesReflection() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, someContentType());
		doAnswer(write(1, "x")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), null, outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("x"));
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
	public void writeWithCharsetEncodesReflectionUsingCharset() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, "x/y; charset=UTF-8");
		doAnswer(write(1, "\u20AC")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y; charset=UTF-8"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(UTF_8), is("\u20AC"));
	}
	
	@Test
	public void writeWhenIOExceptionThrowsException() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, someContentType());
		IOException exception = new IOException();
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		thrown.expect(is(exception));
		
		newConverter(reflector).write(new DummyComponent(), null, new MockHttpOutputMessage());
	}
	
	@Test
	public void writeWhenReflectorExceptionThrowsSpringException() throws IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class, someContentType());
		ReflectorException exception = new ReflectorException("x");
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		thrown.expect(HttpMessageNotWritableException.class);
		thrown.expectMessage("Error writing component");
		thrown.expectCause(is(exception));
		
		newConverter(reflector).write(new DummyComponent(), null, new MockHttpOutputMessage());
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
	
	private static Object someComponent()
	{
		return new Object();
	}
	
	private static Class<Object> someComponentType()
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
