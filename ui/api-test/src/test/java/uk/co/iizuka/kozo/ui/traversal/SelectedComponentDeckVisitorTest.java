/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/SelectedComponentDeckVisitorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static org.junit.Assert.assertEquals;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code SelectedComponentDeckVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: SelectedComponentDeckVisitorTest.java 100650 2012-04-23 10:07:01Z mark@IIZUKA.CO.UK $
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
