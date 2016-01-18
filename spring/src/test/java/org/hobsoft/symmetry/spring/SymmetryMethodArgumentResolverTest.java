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

import java.lang.reflect.Method;
import java.util.Map;

import org.hobsoft.symmetry.Reflector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@code SymmetryMethodArgumentResolver}.
 */
@RunWith(MockitoJUnitRunner.class)
public class SymmetryMethodArgumentResolverTest
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

	@Captor
	private ArgumentCaptor<Map<String, String[]>> stateCaptor;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void supportsParameterWithComponentTypeReturnsTrue() throws NoSuchMethodException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		boolean actual = newResolver(reflector).supportsParameter(dummyComponentParameter());
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void supportsParameterWithComponentSubtypeReturnsTrue() throws NoSuchMethodException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		boolean actual = newResolver(reflector).supportsParameter(dummySubcomponentParameter());
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void supportsParameterWithDifferentComponentTypeReturnsFalse() throws NoSuchMethodException
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		boolean actual = newResolver(reflector).supportsParameter(voidParameter());
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void resolveArgumentReturnsComponent() throws Exception
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		
		Object actual = newResolver(reflector).resolveArgument(dummyComponentParameter(), new ModelAndViewContainer(),
			mock(NativeWebRequest.class), mock(WebDataBinderFactory.class));
		
		assertThat(actual, is(instanceOf(DummyComponent.class)));
	}
	
	@Test
	public void resolveArgumentAbsorbsState() throws Exception
	{
		Reflector<DummyComponent> reflector = mockReflector(DummyComponent.class);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("x", "y");
		
		newResolver(reflector).resolveArgument(dummyComponentParameter(), new ModelAndViewContainer(),
			new ServletWebRequest(request), mock(WebDataBinderFactory.class));

		verify(reflector).absorb(any(DummyComponent.class), stateCaptor.capture());
		assertThat(stateCaptor.getValue().get("x"), arrayContaining("y"));
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

	private static <T> SymmetryMethodArgumentResolver<T> newResolver(Reflector<T> reflector)
	{
		return new SymmetryMethodArgumentResolver<>(reflector);
	}

	private MethodParameter dummyComponentParameter() throws NoSuchMethodException
	{
		return new MethodParameter(getDummyMethod(), 0);
	}
	
	private MethodParameter dummySubcomponentParameter() throws NoSuchMethodException
	{
		return new MethodParameter(getDummyMethod(), 1);
	}
	
	private MethodParameter voidParameter() throws NoSuchMethodException
	{
		return new MethodParameter(getDummyMethod(), 2);
	}

	private Method getDummyMethod() throws NoSuchMethodException
	{
		return getClass().getDeclaredMethod("dummyMethod", DummyComponent.class, DummySubcomponent.class, Void.class);
	}
	
	@SuppressWarnings("unused")
	private static void dummyMethod(DummyComponent component, DummySubcomponent subcomponent, Void voidArg)
	{
		// dummy method to construct MethodParameters
	}
}
