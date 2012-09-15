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
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulTextBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulTextBoxDehydrator
 */
public class XulTextBoxDehydratorTest extends AbstractXmlRenderKitTest<TextBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TextBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(TextBox.class, new XulTextBoxDehydrator<TextBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTextBox() throws HydrationException
	{
		TextBox textBox = new TextBox();
		
		assertDehydrate("<textbox/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithText() throws HydrationException
	{
		TextBox textBox = new TextBox("text");
		
		assertDehydrate("<textbox value=\"text\"/>", textBox);
	}
}
