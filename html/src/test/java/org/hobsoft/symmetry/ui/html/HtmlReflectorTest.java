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
package org.hobsoft.symmetry.ui.html;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code HtmlReflector}.
 */
public class HtmlReflectorTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private HtmlReflector reflector;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		reflector = new HtmlReflector();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void getComponentTypeReturnsComponent()
	{
		Class<?> actual = reflector.getComponentType();
		
		assertThat(actual, is((Object) Component.class));
	}
	
	@Test
	public void getContentTypeReturnsHtml()
	{
		String actual = reflector.getContentType();
		
		assertThat(actual, is("text/html"));
	}
	
	@Test
	public void reflectWithWindowWritesHtml() throws IOException
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		reflector.reflect(new Window(), outputStream);
		
		assertThat(outputStream.toString("UTF-8"), is("<html><body></body></html>"));
	}
}
