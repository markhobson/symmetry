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

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

import static java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;
import static java.net.HttpURLConnection.HTTP_OK;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration test for JAX-RS integration.
 */
public class JaxrsTest extends JerseyTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@BeforeClass
	public static void setUpClass()
	{
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// JerseyTest methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected Application configure()
	{
		return new JaxrsApplication();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void getWindowReturnsHtml()
	{
		Response actual = target("/window").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithAcceptHtmlReturnsHtml()
	{
		Response actual = target("/window").request("text/html; charset=UTF-8").get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	@Ignore("TODO: Do not overwrite Accept media type with reflector's content type")
	public void getWindowWithAcceptCompatibleReturnsHtml()
	{
		Response actual = target("/window").request("text/html; x=y").get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; x=y")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body></body></html>"));
	}
	
	@Test
	@Ignore("TODO: Jersey returns 500 instead of 406")
	public void getWindowWithAcceptOtherReturnsNotAcceptable()
	{
		Response actual = target("/window").request("x/y").get();
		
		assertThat(actual.getStatus(), is(HTTP_NOT_ACCEPTABLE));
	}
	
	@Test
	public void getWindowAndTextReturnsHtml()
	{
		Response actual = target("/windowAndText").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body>x</body></html>"));
	}
	
	@Test
	public void getTextUnicodeEncodesText()
	{
		Response actual = target("/textUnicode").request().get();
		
		assertThat("status", actual.getStatus(), is(HTTP_OK));
		assertThat("content type", actual.getMediaType(), is(MediaType.valueOf("text/html; charset=UTF-8")));
		assertThat("entity", actual.readEntity(String.class), is("<html><body>\u20AC</body></html>"));
	}
}
