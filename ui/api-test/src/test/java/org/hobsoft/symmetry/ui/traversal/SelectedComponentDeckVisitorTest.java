/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/SelectedComponentDeckVisitorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code SelectedComponentDeckVisitor}.
 * 
 * @author Mark Hobson
 * @see SelectedComponentDeckVisitor
 */
public class SelectedComponentDeckVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private SelectedComponentDeckVisitor<Deck, Void, RuntimeException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new SelectedComponentDeckVisitor<Deck, Void, RuntimeException>(
			new NullContainerVisitor<Deck, Void, RuntimeException>());
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		assertEquals(VISIT_CHILDREN, visitor.visitChild(deck, null, 0));
	}
	
	@Test
	public void visitChildWhenUnselected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		assertEquals(SKIP_CHILDREN, visitor.visitChild(deck, null, 1));
	}
	
	@Test
	public void endVisitChildWhenSelected()
	{
		Deck deck = new Deck(new DummyComponent());
		
		assertEquals(SKIP_SIBLINGS, visitor.endVisitChild(deck, null, 0));
	}
	
	@Test
	public void endVisitChildWhenBeforeSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		deck.setSelectedIndex(1);
		
		assertEquals(VISIT_SIBLINGS, visitor.endVisitChild(deck, null, 0));
	}
	
	@Test
	public void endVisitChildWhenAfterSelected()
	{
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		assertEquals(SKIP_SIBLINGS, visitor.endVisitChild(deck, null, 1));
	}
}
