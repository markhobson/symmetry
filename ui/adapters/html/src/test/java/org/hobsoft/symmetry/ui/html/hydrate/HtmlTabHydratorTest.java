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
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

/**
 * Tests {@code HtmlTabHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlTabHydrator
 */
public class HtmlTabHydratorTest extends AbstractXmlRenderKitTest<Tab>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Tab> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Tab.class, new HtmlTabHydrator<Tab>());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(Tab.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTab() throws HydrationException
	{
		Tab tab = new Tab("", null);
		
		String expected = "<div class=\"tabpanel\"></div>";
		
		assertDehydrate(expected, tab);
	}
	
	@Test
	public void dehydrateTabWithComponent() throws HydrationException
	{
		Tab tab = new Tab("", new DummyComponent());
		
		String expected = "<div class=\"tabpanel\">[dummy][/dummy]</div>";
		
		assertDehydrate(expected, tab);
	}
}
