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

import javax.servlet.Servlet;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.rules.ExternalResource;

/**
 * JUnit rule that manages a Jetty server.
 */
public class ServerRule extends ExternalResource
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private Server server;

	// ----------------------------------------------------------------------------------------------------------------
	// ExternalResource methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected void before()
	{
		server = new Server(0);
	}

	@Override
	protected void after()
	{
		if (server.isRunning())
		{
			stopQuietly();
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public Server getServer()
	{
		return server;
	}
	
	public void startServlet(Class<? extends Servlet> servlet, String pathSpec) throws Exception
	{
		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(servlet, pathSpec);
		server.setHandler(handler);
		
		server.start();
	}

	public WebTarget target(String path)
	{
		return ClientBuilder.newClient()
			.target(server.getURI())
			.path(path);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private void stopQuietly()
	{
		try
		{
			server.stop();
		}
		catch (Exception exception)
		{
			// ignore
		}
	}
}
