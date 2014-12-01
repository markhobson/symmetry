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
import javax.servlet.jsp.JspTagException;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;

import static javax.servlet.jsp.PageContext.APPLICATION_SCOPE;
import static javax.servlet.jsp.PageContext.PAGE_SCOPE;
import static javax.servlet.jsp.PageContext.REQUEST_SCOPE;
import static javax.servlet.jsp.PageContext.SESSION_SCOPE;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

	private ExpectedException thrown = ExpectedException.none();

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

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void doTagWhenPageScopeInvokesReflector() throws JspException, IOException, ReflectorException
	{
		DummyComponent component = new DummyComponent();
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		doTag(component, reflector, PAGE_SCOPE);
		
		verify(reflector).reflect(component, context.getOut());
	}
	
	@Test
	public void doTagWhenRequestScopeInvokesReflector() throws JspException, IOException, ReflectorException
	{
		DummyComponent component = new DummyComponent();
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		doTag(component, reflector, REQUEST_SCOPE);
		
		verify(reflector).reflect(component, context.getOut());
	}
	
	@Test
	public void doTagWhenSessionScopeInvokesReflector() throws JspException, IOException, ReflectorException
	{
		DummyComponent component = new DummyComponent();
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		doTag(component, reflector, SESSION_SCOPE);
		
		verify(reflector).reflect(component, context.getOut());
	}
	
	@Test
	public void doTagWhenApplicationScopeInvokesReflector() throws JspException, IOException, ReflectorException
	{
		DummyComponent component = new DummyComponent();
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		doTag(component, reflector, APPLICATION_SCOPE);
		
		verify(reflector).reflect(component, context.getOut());
	}
	
	@Test
	public void doTagWritesHtml() throws JspException, IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		doAnswer(write(1, "x")).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		doTag(new DummyComponent(), reflector);
		
		assertThat(getResponse().getContentAsString(), is("x"));
	}
	
	@Test
	public void doTagWhenComponentNotFoundThrowsException() throws JspException, IOException
	{
		tag.setName("x");
		context.setAttribute("y", mock(Reflector.class));
		tag.setReflectorName("y");
		
		thrown.expect(JspTagException.class);
		thrown.expectMessage("Cannot find component: x");
		
		tag.doTag();
	}
	
	@Test
	public void doTagWhenReflectorNotFoundThrowsException() throws JspException, IOException
	{
		context.setAttribute("x", new DummyComponent());
		tag.setName("x");
		tag.setReflectorName("y");
		
		thrown.expect(JspTagException.class);
		thrown.expectMessage("Cannot find reflector: y");
		
		tag.doTag();
	}
	
	@Test
	public void doTagWhenIOExceptionThrowsException() throws JspException, IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		IOException exception = new IOException();
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));

		thrown.expect(is(exception));

		doTag(new DummyComponent(), reflector);
	}
	
	@Test
	public void doTagWhenReflectorExceptionThrowsJspTagException() throws JspException, IOException, ReflectorException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		ReflectorException exception = new ReflectorException("x");
		doThrow(exception).when(reflector).reflect(any(DummyComponent.class), any(Writer.class));
		
		thrown.expect(JspTagException.class);
		thrown.expectMessage("Error writing component");
		thrown.expectCause(is(exception));
		
		doTag(new DummyComponent(), reflector);
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

	private static <T> Reflector<T> mockReflector(Class<T> componentType)
	{
		Reflector<T> reflector = mock(Reflector.class);
		when(reflector.getComponentType()).thenReturn(componentType);
		return reflector;
	}
	
	private <T> void doTag(T component, Reflector<T> reflector) throws JspException, IOException
	{
		doTag(component, reflector, PAGE_SCOPE);
	}
	
	private <T> void doTag(T component, Reflector<T> reflector, int scope) throws JspException, IOException
	{
		context.setAttribute("_component", component, scope);
		tag.setName("_component");
		
		context.setAttribute("_reflector", reflector, scope);
		tag.setReflectorName("_reflector");
		
		tag.doTag();
	}
	
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
