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
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulGroupBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulGroupBoxDehydrator
 */
public class XulGroupBoxDehydratorTest extends AbstractXmlRenderKitTest<GroupBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<GroupBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(GroupBox.class, new XulGroupBoxDehydrator<GroupBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateGroupBox() throws HydrationException
	{
		GroupBox groupBox = new GroupBox();
		
		assertDehydrate("<groupbox></groupbox>", groupBox);
	}
	
	@Test
	public void dehydrateGroupBoxWithTitle() throws HydrationException
	{
		GroupBox groupBox = new GroupBox("text");
		
		assertDehydrate("<groupbox><caption label=\"text\"/></groupbox>", groupBox);
	}
}
