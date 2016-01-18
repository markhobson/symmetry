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

import org.hobsoft.symmetry.Reflector;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Spring handler method argument resolver for UI components.
 * 
 * @param <T>
 *            the base component type
 */
public class SymmetryMethodArgumentResolver<T> implements HandlerMethodArgumentResolver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final Reflector<T> reflector;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public SymmetryMethodArgumentResolver(Reflector<T> reflector)
	{
		this.reflector = reflector;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// HandlerMethodArgumentResolver methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean supportsParameter(MethodParameter parameter)
	{
		return reflector.getComponentType().isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
	{
		Object target = BeanUtils.instantiateClass(parameter.getParameterType());
		T component = reflector.getComponentType().cast(target);
		
		reflector.absorb(component, webRequest.getParameterMap());
		
		return component;
	}
}
