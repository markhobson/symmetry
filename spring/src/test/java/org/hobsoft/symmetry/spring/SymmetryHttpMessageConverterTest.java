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
import java.util.List;

import org.hobsoft.symmetry.Reflector;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
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
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void canReadWithComponentAndContentTypeReturnsFalse()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canRead(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithComponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummyComponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithSubcomponentAndContentTypeReturnsTrue()
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		
		boolean actual = newConverter(reflector).canWrite(DummySubcomponent.class, parseMediaType("x/y"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void getSupportedMediaTypesReturnsContentType()
	{
		Reflector<?> reflector = newReflector(anyComponentType(), "x/y");
		
		List<MediaType> actuals = newConverter(reflector).getSupportedMediaTypes();
		
		assertThat(actuals, contains(parseMediaType("x/y")));
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void readWithComponentThrowsException() throws IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, anyContentType());
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		
		newConverter(reflector).read(DummyComponent.class, inputMessage);
	}
	
	@Test
	public void writeWithComponentWritesHtml() throws IOException
	{
		Reflector<DummyComponent> reflector = newReflector(DummyComponent.class, "x/y");
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		newConverter(reflector).write(new DummyComponent(), parseMediaType("x/y"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("<html/>"));
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
	
	private static Class<?> anyComponentType()
	{
		return Object.class;
	}
	
	private static String anyContentType()
	{
		return "_/_";
	}
}
