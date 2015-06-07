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
package org.hobsoft.symmetry.ui.html;

import org.hobsoft.symmetry.ui.Text;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.singletonMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code HtmlAbsorbingVisitor}.
 */
public class HtmlAbsorbingVisitorTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private HtmlAbsorbingVisitor visitor;
	
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		visitor = new HtmlAbsorbingVisitor();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void visitWithTextSetsProperty()
	{
		Text text = new Text("x");
		
		visitor.visit(text, singletonMap("text", new String[] {"y"}));
		
		assertThat(text.getText(), is("y"));
	}
}
