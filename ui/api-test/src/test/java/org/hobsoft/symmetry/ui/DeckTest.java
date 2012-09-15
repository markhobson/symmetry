/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/DeckTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

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
