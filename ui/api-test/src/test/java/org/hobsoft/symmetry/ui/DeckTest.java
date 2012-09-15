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

import java.util.Arrays;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code Deck}.
 * 
 * @author Mark Hobson
 * @see Deck
 */
public class DeckTest extends AbstractComponentTest<Deck>
{
	// fields -----------------------------------------------------------------
	
	private Deck deck;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		deck = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newDeck()
	{
		assertDeck(deck, -1);
	}
	
	@Test
	public void newDeckWithComponent()
	{
		Component child = new DummyComponent();
		
		deck = new Deck(child);
		
		assertDeck(deck, 0, child);
	}
	
	@Test
	public void newDeckWithComponents()
	{
		Component child1 = new DummyComponent();
		Component child2 = new DummyComponent();
		
		deck = new Deck(child1, child2);
		
		assertDeck(deck, 0, child1, child2);
	}
	
	@Test
	public void addWithFirstComponentSetsSelectedIndex()
	{
		deck.add(new DummyComponent());
		
		assertEquals(0, deck.getSelectedIndex());
	}
	
	@Test
	public void removeWithSelectedComponentResetsSelectedIndex()
	{
		Component child = new DummyComponent();
		deck.add(new DummyComponent(), child);
		deck.setSelectedIndex(1);
		
		deck.remove(child);
		
		assertEquals(0, deck.getSelectedIndex());
	}
	
	@Test
	public void removeWithLastComponentUnsetsSelectedIndex()
	{
		Component child = new DummyComponent();
		deck.add(child);
		
		deck.remove(child);
		
		assertEquals(-1, deck.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndex()
	{
		deck = new Deck(new DummyComponent(), new DummyComponent());
		deck.setSelectedIndex(1);
		
		assertEquals(1, deck.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexWithUnselectedWhenEmpty()
	{
		deck.setSelectedIndex(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedIndexWithUnselectedWhenNotEmpty()
	{
		deck = new Deck(new DummyComponent());
		deck.setSelectedIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithNegative()
	{
		deck.setSelectedIndex(-2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithOutOfBounds()
	{
		deck = new Deck(new DummyComponent());
		deck.setSelectedIndex(1);
	}
	
	@Test
	public void setSelectedComponent()
	{
		DummyComponent child1 = new DummyComponent();
		DummyComponent child2 = new DummyComponent();
		deck = new Deck(child1, child2);
		
		deck.setSelectedComponent(child2);
		
		assertEquals("selectedIndex", 1, deck.getSelectedIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedComponentWhenNotFound()
	{
		deck = new Deck(new DummyComponent());
		
		deck.setSelectedComponent(new DummyComponent());
	}
	
	@Test(expected = NullPointerException.class)
	public void setSelectedComponentWithNull()
	{
		deck = new Deck(new DummyComponent());
		
		deck.setSelectedComponent(null);
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Deck createComponent()
	{
		return new Deck();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Deck> getComponentType()
	{
		return Generic.get(Deck.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertDeck(Deck actual, int expectedSelectedIndex, Component... expectedComponents)
	{
		assertNotNull("deck", actual);
		assertEquals("components", Arrays.asList(expectedComponents), actual.getComponents());
		assertEquals("selectedIndex", expectedSelectedIndex, actual.getSelectedIndex());
	}
}
