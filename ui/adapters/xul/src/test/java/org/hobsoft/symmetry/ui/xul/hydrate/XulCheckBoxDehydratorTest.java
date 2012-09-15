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
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulCheckBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulCheckBoxDehydrator
 */
public class XulCheckBoxDehydratorTest extends AbstractXmlRenderKitTest<CheckBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<CheckBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(CheckBox.class, new XulCheckBoxDehydrator<CheckBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateCheckBox() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		
		assertDehydrate("<checkbox/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithText() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		
		assertDehydrate("<checkbox label=\"text\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWhenSelected() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(true);
		
		assertDehydrate("<checkbox checked=\"true\"/>", checkBox);
	}
}
