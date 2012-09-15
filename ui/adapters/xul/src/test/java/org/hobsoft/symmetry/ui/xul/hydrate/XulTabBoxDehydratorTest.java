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
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

/**
 * Tests {@code XulTabBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulTabBoxDehydrator
 */
public class XulTabBoxDehydratorTest extends AbstractXmlRenderKitTest<TabBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TabBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(TabBox.class, new XulTabBoxDehydrator<TabBox>());
		
		hydrator.setDelegate(Tab.class, stubXmlHierarchicalComponentHydrator(Tab.class, "tab"));
		
		return StubUiComponentRenderKit.get(TabBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTabBoxEmpty() throws HydrationException
	{
		TabBox tabBox = new TabBox();
		
		assertDehydrate("<tabbox></tabbox>", tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTab() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab());
		
		String expected = "<tabbox>"
			+ "<tabs><tab/></tabs>"
			+ "<tabpanels>[tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTabAndText() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab("text", null));
		
		String expected = "<tabbox>"
			+ "<tabs><tab label=\"text\"/></tabs>"
			+ "<tabpanels>[tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}

	@Test
	public void dehydrateTabBoxWithTabs() throws HydrationException
	{
		// TODO: dehydrate tabs differently to differentiate in assertion
		TabBox tabBox = new TabBox(new Tab("a", null), new Tab("b", null));
		
		String expected = "<tabbox>"
			+ "<tabs><tab label=\"a\"/><tab label=\"b\"/></tabs>"
			+ "<tabpanels>[tab][/tab][tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}
}
