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
