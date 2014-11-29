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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for Spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
public class SpringTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		mvc = MockMvcBuilders.webAppContextSetup(context)
			.build();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void getWindowReturnsHtml() throws Exception
	{
		mvc.perform(get("/window"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html"))
			.andExpect(content().string("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithAcceptHtmlReturnsHtml() throws Exception
	{
		mvc.perform(get("/window").accept("text/html"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html"))
			.andExpect(content().string("<html><body></body></html>"));
	}
	
	@Test
	public void getWindowWithAcceptOtherReturnsNotAcceptable() throws Exception
	{
		mvc.perform(get("/window").accept("x/y"))
			.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void getWindowAndTextReturnsHtml() throws Exception
	{
		mvc.perform(get("/windowAndText").accept("text/html"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html"))
			.andExpect(content().string("<html><body>x</body></html>"));
	}
}
