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
package org.hobsoft.symmetry.spring.it;

import java.util.List;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.spring.SymmetryHttpMessageConverter;
import org.hobsoft.symmetry.spring.SymmetryMethodArgumentResolver;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.html.HtmlComponentReflector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Spring MVC configuration for integration tests.
 */
@Configuration
@ComponentScan
public class SpringConfig extends WebMvcConfigurationSupport
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Bean
	public Reflector<Component> reflector()
	{
		return new HtmlComponentReflector();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// WebMvcConfigurationSupport methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
	{
		argumentResolvers.add(new SymmetryMethodArgumentResolver<>(reflector()));
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		addDefaultHttpMessageConverters(converters);
		
		converters.add(new SymmetryHttpMessageConverter<>(reflector()));
	}
}
