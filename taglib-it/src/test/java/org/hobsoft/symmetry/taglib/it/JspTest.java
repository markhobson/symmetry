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
package org.hobsoft.symmetry.taglib.it;

import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static com.google.common.io.Resources.getResource;

/**
 * Integration test for JSP tag library integration.
 */
public class JspTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ServerRule serverRule = new ServerRule();

	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ServerRule getServerRule()
	{
		return serverRule;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void getWindowWithPageScopeReturnsHtml() throws Exception
	{
		serverRule.startWebApp(getResource(getClass(), ""), "/");
		
		Response actual = serverRule.target("/windowWithPageScope.jsp").request().get();
		
		assertThat(actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithRequestScopeReturnsHtml() throws Exception
	{
		serverRule.startWebApp(getResource(getClass(), ""), "/");
		
		Response actual = serverRule.target("/windowWithRequestScope.jsp").request().get();
		
		assertThat(actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithSessionScopeReturnsHtml() throws Exception
	{
		serverRule.startWebApp(getResource(getClass(), ""), "/");
		
		Response actual = serverRule.target("/windowWithSessionScope.jsp").request().get();
		
		assertThat(actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithApplicationScopeReturnsHtml() throws Exception
	{
		serverRule.startWebApp(getResource(getClass(), ""), "/");
		
		Response actual = serverRule.target("/windowWithApplicationScope.jsp").request().get();
		
		assertThat(actual.readEntity(String.class), is("<html><body></body></html>"));
	}
}
