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
import org.junit.Before;
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
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private SymmetryHttpMessageConverter<DummyComponent> converter;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		Reflector<DummyComponent> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(DummyComponent.class);
		when(reflector.getContentType()).thenReturn("text/plain");
		
		converter = new SymmetryHttpMessageConverter<>(reflector);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void canReadWithComponentAndContentTypeReturnsFalse()
	{
		boolean actual = converter.canRead(DummyComponent.class, parseMediaType("text/plain"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithComponentAndContentTypeReturnsTrue()
	{
		boolean actual = converter.canWrite(DummyComponent.class, parseMediaType("text/plain"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithSubcomponentAndContentTypeReturnsTrue()
	{
		boolean actual = converter.canWrite(DummySubcomponent.class, parseMediaType("text/plain"));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void getSupportedMediaTypesReturnsContentType()
	{
		List<MediaType> actuals = converter.getSupportedMediaTypes();
		
		assertThat(actuals, contains(parseMediaType("text/plain")));
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void readWithComponentThrowsException() throws IOException
	{
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		
		converter.read(DummyComponent.class, inputMessage);
	}
	
	@Test
	public void writeWithComponentWritesHtml() throws IOException
	{
		DummyComponent component = new DummyComponent();
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		converter.write(component, parseMediaType("text/plain"), outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("<html/>"));
	}
}
