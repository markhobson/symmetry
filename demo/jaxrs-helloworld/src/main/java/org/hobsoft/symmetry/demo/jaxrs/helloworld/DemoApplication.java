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
package org.hobsoft.symmetry.demo.jaxrs.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.hobsoft.symmetry.jaxrs.SymmetryMessageBodyWriter;
import org.hobsoft.symmetry.ui.html.HtmlComponentReflector;

/**
 * JAX-RS application for this demo.
 */
@ApplicationPath("/")
public class DemoApplication extends Application
{
	// ----------------------------------------------------------------------------------------------------------------
	// Application methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> classes = new HashSet<>();
		classes.add(HelloResource.class);
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons()
	{
		Set<Object> singletons = new HashSet<>();
		singletons.add(new SymmetryMessageBodyWriter<>(new HtmlComponentReflector()));
		return singletons;
	}
}
