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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static com.google.common.io.Resources.getResource;

/**
 * Integration test for tag library integration.
 */
public class TaglibTest
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
	public void getWindowAndTextReturnsHtml() throws Exception
	{
		serverRule.startWebApp(getResource(getClass(), ""), "/");
		
		Response actual = serverRule.target("/windowAndText.jsp").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=ISO-8859-1")));
		assertThat("entity", actual.readEntity(String.class), is("\n<html><body>x</body></html>\n"));
	}
}
