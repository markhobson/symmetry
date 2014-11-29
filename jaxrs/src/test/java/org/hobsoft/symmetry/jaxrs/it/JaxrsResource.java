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
package org.hobsoft.symmetry.jaxrs.it;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.hobsoft.symmetry.ui.Text;
import org.hobsoft.symmetry.ui.Window;

/**
 * JAX-RS resource for integration tests.
 */
@Path("/")
public class JaxrsResource
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@GET
	@Path("window")
	public Window window()
	{
		return new Window();
	}
	
	@GET
	@Path("windowAndText")
	public Window windowAndText()
	{
		Window window = new Window();
		window.add(new Text("x"));
		return window;
	}
}
