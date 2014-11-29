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

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration test for Servlet integration.
 */
public class ServletTest
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
	public void getWindowReturnsHtml() throws Exception
	{
		serverRule.startServlet(WindowServlet.class, "/");
		
		String actual = serverRule.get("/");
		
		assertThat(actual, is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowAndTextReturnsHtml() throws Exception
	{
		serverRule.startServlet(WindowAndTextServlet.class, "/");
		
		String actual = serverRule.get("/");
		
		assertThat(actual, is("<html><body>x</body></html>"));
	}
}
