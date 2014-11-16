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
package org.hobsoft.symmetry;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code ReflectorException}.
 */
public class ReflectorExceptionTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void constructorWithMessageSetsProperties()
	{
		ReflectorException actual = new ReflectorException("x");
		
		assertThat("message", actual.getMessage(), is("x"));
		assertThat("cause", actual.getCause(), is(nullValue()));
	}
	
	@Test
	public void constructorWithMessageAndCauseSetsProperties()
	{
		Throwable cause = new Throwable();
		ReflectorException actual = new ReflectorException("x", cause);
		
		assertThat("message", actual.getMessage(), is("x"));
		assertThat("cause", actual.getCause(), is(cause));
	}
}
