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
package org.hobsoft.symmetry.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests {@code ComponentTag}.
 */
public class ComponentTagTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// types
	// ----------------------------------------------------------------------------------------------------------------

	private static class DummyComponent
	{
		// dummy type
	}

	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MockPageContext context;
	
	private ComponentTag tag;
	
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		context = new MockPageContext();
		
		tag = new ComponentTag();
		tag.setJspContext(context);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void doTagInvokesReflector() throws JspException, IOException, ReflectorException
	{
		DummyComponent component = new DummyComponent();
		context.setAttribute("x", component);
		tag.setName("x");
		
		Reflector<DummyComponent> reflector = mock(Reflector.class);
		context.setAttribute("y", reflector);
		tag.setReflectorName("y");
		
		tag.doTag();
		
		verify(reflector).reflect(component, context.getOut());
	}
	
	@Test
	public void doTagWritesHtml() throws JspException, IOException, ReflectorException
	{
		context.setAttribute("x", new DummyComponent());
		tag.setName("x");

		Reflector<DummyComponent> reflector = mock(Reflector.class);
		doAnswer(write(1, "z")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		context.setAttribute("y", reflector);
		tag.setReflectorName("y");
		
		tag.doTag();
		
		assertThat(getResponse().getContentAsString(), is("z"));
	}
	
	@Test
	public void setNameSetsProperty()
	{
		tag.setName("x");
		
		assertThat(tag.getName(), is("x"));
	}
	
	@Test
	public void setReflectorNameSetsProperty()
	{
		tag.setReflectorName("x");
		
		assertThat(tag.getReflectorName(), is("x"));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private MockHttpServletResponse getResponse()
	{
		return (MockHttpServletResponse) context.getResponse();
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
