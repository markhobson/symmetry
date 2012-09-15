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
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulButtonDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulButtonDehydrator
 */
public class XulButtonDehydratorTest extends AbstractXmlRenderKitTest<Button>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Button> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Button.class, new XulButtonDehydrator<Button>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateButton() throws HydrationException
	{
		Button button = new Button();
		
		assertDehydrate("<button/>", button);
	}
	
	@Test
	public void dehydrateButtonWithText() throws HydrationException
	{
		Button button = new Button("text");
		
		assertDehydrate("<button label=\"text\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithToolTip() throws HydrationException
	{
		Button button = new Button();
		button.setToolTip("a");
		
		assertDehydrate("<button tooltiptext=\"a\"/>", button);
	}
}
