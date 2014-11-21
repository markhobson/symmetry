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
import java.lang.annotation.Annotation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.hobsoft.symmetry.ui.Window;
import org.junit.Before;
import org.junit.Test;

import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code SymmetryMessageBodyWriter}.
 */
public class SymmetryMessageBodyWriterTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private SymmetryMessageBodyWriter writer;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		writer = new SymmetryMessageBodyWriter();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void isWriteableWithWindowAndHtmlReturnsTrue()
	{
		boolean actual = writer.isWriteable(Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void isWriteableWithDifferentMediaTypeReturnsFalse()
	{
		boolean actual = writer.isWriteable(Window.class, Window.class, new Annotation[0], MediaType.valueOf("x/y"));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void isWriteableWithDifferentTypeReturnsFalse()
	{
		boolean actual = writer.isWriteable(Void.class, Void.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void getSizeReturnsUnknown()
	{
		long actual = writer.getSize(new Window(), Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE);
		
		assertThat(actual, is(-1L));
	}
	
	@Test
	public void writeToWithWindowWritesHtml() throws IOException
	{
		MultivaluedMap<String, Object> httpHeaders = new MultivaluedHashMap<>();
		ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
		
		writer.writeTo(new Window(), Window.class, Window.class, new Annotation[0], TEXT_HTML_TYPE, httpHeaders,
			entityStream);
		
		assertThat(entityStream.toString("UTF-8"), is("<html><body></body></html>"));
	}
}
