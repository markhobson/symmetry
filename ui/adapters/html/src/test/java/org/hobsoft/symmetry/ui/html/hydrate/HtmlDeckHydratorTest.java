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
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.DummyComponentRehydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlDeckHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlDeckHydrator
 */
public class HtmlDeckHydratorTest extends AbstractXmlRenderKitTest<Deck>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Deck> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Deck.class, new HtmlDeckHydrator<Deck>());
		
		PhasedHierarchicalComponentHydrator<DummyComponent> dummyHydrator =
			new PhasedHierarchicalComponentHydrator<DummyComponent>();
		dummyHydrator.setDelegate(DEHYDRATE, stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy"));
		dummyHydrator.setDelegate(REHYDRATE_PARAMETERS, new DummyComponentRehydrator());
		hydrator.setDelegate(DummyComponent.class, dummyHydrator);
		
		return StubPhasedUiComponentRenderKit.get(Deck.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateDeckWhenEmpty() throws HydrationException
	{
		Deck deck = new Deck();
		
		assertDehydrate("<div class=\"deck\"></div>", deck);
	}
	
	@Test
	public void dehydrateDeckWithComponent() throws HydrationException
	{
		Deck deck = new Deck(new DummyComponent());
		
		assertDehydrate("<div class=\"deck\">[dummy][/dummy]</div>", deck);
	}

	@Test
	public void dehydrateDeckWithComponents() throws HydrationException
	{
		// TODO: dehydrate tab components differently to differentiate in assertion
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		assertDehydrate("<div class=\"deck\">[dummy][/dummy]</div>", deck);
	}

	@Test
	public void rehydrateWithSelectedComponentParameter() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		Deck deck = new Deck(component);
		
		rehydrate(deck);
		
		assertTrue(component.getState());
	}
	
	@Test
	public void rehydrateWithUnselectedComponentParameter() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		Deck deck = new Deck(new DummyComponent(), component);
		
		rehydrate(deck);
		
		assertFalse(component.getState());
	}
}
