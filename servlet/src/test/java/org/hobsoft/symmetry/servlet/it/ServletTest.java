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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;

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
		
		Response actual = serverRule.target("/").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowAndTextReturnsHtml() throws Exception
	{
		serverRule.startServlet(WindowAndTextServlet.class, "/");
		
		Response actual = serverRule.target("/").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body>x</body></html>"));
	}

	@Test
	public void getWindowAndTextWithTextReturnsHtml() throws Exception
	{
		serverRule.startServlet(WindowAndTextServlet.class, "/");
		
		Response actual = serverRule.target("/").queryParam("text", "y").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body>y</body></html>"));
	}
	
	@Test
	public void getTextUnicodeEncodesText() throws Exception
	{
		serverRule.startServlet(TextUnicodeServlet.class, "/");
		
		Response actual = serverRule.target("/").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body>\u20AC</body></html>"));
	}
}
