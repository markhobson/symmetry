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

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code SymmetryHttpMessageConverter}.
 */
public class SymmetryHttpMessageConverterTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private SymmetryHttpMessageConverter converter;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		converter = new SymmetryHttpMessageConverter();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void canReadWithComponentAndHtmlReturnsFalse()
	{
		boolean actual = converter.canRead(Component.class, MediaType.TEXT_HTML);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void canWriteWithComponentAndHtmlReturnsTrue()
	{
		boolean actual = converter.canWrite(Component.class, MediaType.TEXT_HTML);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void canWriteWithSubcomponentAndHtmlReturnsTrue()
	{
		boolean actual = converter.canWrite(Window.class, MediaType.TEXT_HTML);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void getSupportedMediaTypesReturnsHtml()
	{
		List<MediaType> actuals = converter.getSupportedMediaTypes();
		
		assertThat(actuals, contains(MediaType.TEXT_HTML));
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void readWithComponentThrowsException() throws IOException
	{
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		
		converter.read(Component.class, inputMessage);
	}
	
	@Test
	public void writeWithComponentWritesHtml() throws IOException
	{
		Component component = new Window();
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		
		converter.write(component, MediaType.TEXT_HTML, outputMessage);
		
		assertThat(outputMessage.getBodyAsString(), is("<html/>"));
	}
}
