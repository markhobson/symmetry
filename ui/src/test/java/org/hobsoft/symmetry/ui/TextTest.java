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
package org.hobsoft.symmetry.ui;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests {@code Text}.
 */
public class TextTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void defaultConstructorSetsProperties()
	{
		Text actual = new Text();
		
		assertThat("text", actual.getText(), is(""));
	}

	@Test
	public void constructorWithTextSetsProperty()
	{
		Text text = new Text("x");
		
		assertThat(text.getText(), is("x"));
	}
	
	@Test
	public void acceptInvokesVisitText()
	{
		ComponentVisitor<String, RuntimeException> visitor = mock(ComponentVisitor.class);
		Text text = new Text();
		
		text.accept(visitor, "p");
		
		verify(visitor).visit(text, "p");
	}
}
