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

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * JUnit test rule adapter for a JerseyTest.
 */
public class JerseyTestRule implements TestRule
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final JerseyTest test;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public JerseyTestRule(Application application)
	{
		test = new JerseyTest()
		{
			@Override
			protected Application configure()
			{
				return application;
			}
		};
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// TestRule methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public Statement apply(Statement base, Description description)
	{
		return new Statement()
		{
			@Override
			public void evaluate() throws Throwable
			{
				test.setUp();
				
				try
				{
					base.evaluate();
				}
				finally
				{
					test.tearDown();
				}
			}
		};
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public WebTarget target(String path)
	{
		return test.target(path);
	}
}
