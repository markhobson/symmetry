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
package org.hobsoft.symmetry.servlet.it;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.html.HtmlComponentReflector;

/**
 * Servlet for integration tests.
 */
@SuppressWarnings("serial")
public class WindowServlet extends HttpServlet
{
	// ----------------------------------------------------------------------------------------------------------------
	// HttpServlet methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Reflector<Component> reflector = new HtmlComponentReflector();
		response.setContentType(reflector.getContentType());
		
		try
		{
			reflector.reflect(new Window(), response.getOutputStream());
		}
		catch (ReflectorException exception)
		{
			throw new ServletException("Error writing component", exception);
		}
	}
}
